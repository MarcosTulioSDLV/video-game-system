package com.springboot.video_game_system.services;

import com.springboot.video_game_system.dtos.BelongingRequestDto;
import com.springboot.video_game_system.dtos.BelongingResponseDto;
import com.springboot.video_game_system.dtos.ReplacementRequestDto;
import com.springboot.video_game_system.exceptions.*;
import com.springboot.video_game_system.mappers.BelongingMapper;
import com.springboot.video_game_system.models.Belonging;
import com.springboot.video_game_system.models.BelongingId;
import com.springboot.video_game_system.models.Game;
import com.springboot.video_game_system.models.GameList;
import com.springboot.video_game_system.repositories.BelongingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class BelongingServiceImp implements BelongingService{

    private final BelongingRepository belongingRepository;

    private final BelongingMapper belongingMapper;

    private final GameService gameService;

    private final GameListService gameListService;

    @Autowired
    public BelongingServiceImp(BelongingRepository belongingRepository, BelongingMapper belongingMapper, GameService gameService, GameListService gameListService) {
        this.belongingRepository = belongingRepository;
        this.belongingMapper = belongingMapper;
        this.gameService = gameService;
        this.gameListService = gameListService;
    }

    @Override
    public BelongingResponseDto getBelongingById(Long gameId,Long gameListId) {
        Belonging belonging= findBelongingById(gameId,gameListId);
        return belongingMapper.toBelongingResponseDto(belonging);
    }

    private Belonging findBelongingById(Long gameId,Long gameListId){
        BelongingId belongingId= new BelongingId(gameId,gameListId);
        return findBelongingById(belongingId);
    }

    private Belonging findBelongingById(BelongingId belongingId) {
        String message= String.format("BelongingId: (%d,%d) not found!", belongingId.getGameId(),belongingId.getGameListId());
        return belongingRepository.findById(belongingId).orElseThrow(() -> new BelongingNotFoundException(message));
    }

    @Override
    @Transactional
    public void addBelonging(BelongingRequestDto belongingRequestDto) {
        Long gameId= belongingRequestDto.getGameId();
        Long gameListId= belongingRequestDto.getGameListId();

        validateUniqueId(gameId,gameListId);

        Game game= gameService.findGameById(gameId);
        GameList gameList= gameListService.findGameListById(gameListId);
        Integer position= getPosition(gameList);

        Belonging belonging= new Belonging(game,gameList,position);

        belongingRepository.save(belonging);
    }

    private void validateUniqueId(Long gameId, Long gameListId) {
        BelongingId belongingId= new BelongingId(gameId,gameListId);
        if(belongingRepository.existsById(belongingId)){
            String message= String.format("BelongingId: (%d,%d) already exists!",gameId,gameListId);
            throw new BelongingExistsException(message);
        }
    }

    //Note: Get the new position (0 if there are no elements or the last current position +1 if there are elements).
    private Integer getPosition(GameList gameList) {
        return gameList.getBelongingList()
                .stream()
                .map(Belonging::getPosition)
                .max(Comparator.naturalOrder()).map(max -> max + 1)
                .orElse(0);
    }

    @Override
    @Transactional
    public void moveGameList(Long gameListId,ReplacementRequestDto replacementRequestDto) {
        Integer sourceIndex= replacementRequestDto.getSourceIndex();
        Integer destinationIndex= replacementRequestDto.getDestinationIndex();

        GameList gameList= gameListService.findGameListById(gameListId);
        List<Belonging> belongings= gameList.getBelongingList();

        validateIndexesOutOfBounds(belongings, sourceIndex, destinationIndex);

        int minIndex= Math.min(sourceIndex,destinationIndex);
        int maxIndex= Math.max(sourceIndex,destinationIndex);

        List<Integer> requiredBelongingPositions= new ArrayList<>();
        IntStream.range(minIndex,maxIndex+1)
                .forEach(requiredBelongingPositions::add);

        //Note: It is necessary sorting by position here.
        List<Belonging> requiredBelongings = new ArrayList<>(belongings.stream()
                .filter(b -> requiredBelongingPositions.contains(b.getPosition()))
                .sorted(Comparator.comparing(Belonging::getPosition))
                .toList());

        // Note: This step is necessary because the original source and destination indexes refer to different positions within the sublist.
        sourceIndex = sourceIndex < destinationIndex ? 0 : requiredBelongings.size() - 1;
        destinationIndex = sourceIndex < destinationIndex ? requiredBelongings.size() - 1 : 0;

        Belonging tempBelonging= requiredBelongings.remove((int)sourceIndex);
        requiredBelongings.add(destinationIndex,tempBelonging);
        /*
        //Old method
        if(destinationIndex<sourceIndex){
            Belonging tempBelonging= requiredBelongings.remove(requiredBelongings.size()-1);
            requiredBelongings.add(0,tempBelonging);
        }else {
            Belonging tempBelonging= requiredBelongings.remove(0);
            requiredBelongings.add(tempBelonging);
        }*/

        for(int i=0;i<requiredBelongings.size();i++){
            requiredBelongings.get(i).setPosition(i+minIndex);
        }
    }

    private static void validateIndexesOutOfBounds(List<Belonging> belongings,Integer sourceIndex, Integer destinationIndex) {
        if (sourceIndex >= belongings.size())
            throw new SourceIndexOutOfBoundsException("Invalid source index!");
        if (destinationIndex >= belongings.size())
            throw new DestinationIndexOutOfBoundsException("Invalid destination index!");
    }

    @Override
    @Transactional
    public void removeBelonging(Long gameId,Long gameListId) {
        Belonging belonging= findBelongingById(gameId,gameListId);
        updateBelongingPositionsInRelatedGameList(belonging);
        belongingRepository.delete(belonging);
    }

    private void updateBelongingPositionsInRelatedGameList(Belonging belonging) {
        // Get the game list where the belonging resides.
        GameList relatedGameList= belonging.getGameList();
        // Create a sorted copy of the belongings from the related game list by position.
        List<Belonging> belongingsFromRelatedGameList= new ArrayList<>(relatedGameList.getBelongingList()
                .stream().sorted(Comparator.comparingInt(Belonging::getPosition))
                .toList());
        // Find the index of the belonging in the sorted list.
        int belongingIndex= belongingsFromRelatedGameList.indexOf(belonging);
        // Remove the belonging from the list.
        belongingsFromRelatedGameList.remove(belongingIndex);
        // Update the positions of the remaining belongings.
        for(int i=belongingIndex;i<belongingsFromRelatedGameList.size();i++){
            Belonging innerBelonging= belongingsFromRelatedGameList.get(i);
            innerBelonging.setPosition(i);
        }
    }

}
