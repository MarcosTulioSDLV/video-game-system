package com.springboot.video_game_system.services;

import com.springboot.video_game_system.dtos.GameListRequestDto;
import com.springboot.video_game_system.dtos.GameListResponseDto;
import com.springboot.video_game_system.dtos.ReplacementRequestDto;
import com.springboot.video_game_system.exceptions.*;
import com.springboot.video_game_system.mappers.GameListMapper;
import com.springboot.video_game_system.models.GameList;
import com.springboot.video_game_system.projections.GameProjection;
import com.springboot.video_game_system.repositories.GameListRepository;
import com.springboot.video_game_system.repositories.GameRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GameListServiceImp implements GameListService{

    private final GameListRepository gameListRepository;

    private final GameListMapper gameListMapper;

    private final GameRepository gameRepository;

    @Autowired
    public GameListServiceImp(GameListRepository gameListRepository, GameListMapper gameListMapper, GameRepository gameRepository) {
        this.gameListRepository = gameListRepository;
        this.gameListMapper = gameListMapper;
        this.gameRepository = gameRepository;
    }

    @Override
    public Page<GameListResponseDto> getAllGameLists(Pageable pageable) {
        return gameListRepository.findAll(pageable).map(gameListMapper::toGameListResponseDto);
    }

    @Override
    public GameListResponseDto getGameListById(Long gameListId) {
        GameList gameList= findGameListById(gameListId);
        return gameListMapper.toGameListResponseDto(gameList);
    }

    public GameList findGameListById(Long gameListId) {
        return gameListRepository.findById(gameListId).orElseThrow(() -> new GameListNotFoundException("Game List with id: " + gameListId + " not found!"));
    }

    @Override
    @Transactional
    public void addGameList(GameListRequestDto gameListRequestDto) {
        GameList gameList= gameListMapper.toGameList(gameListRequestDto);
        validateUniqueFields(gameList);
        gameListRepository.save(gameList);
    }

    private void validateUniqueFields(GameList gameList) {
        if(gameListRepository.existsByName(gameList.getName())){
            throw new GameListNameExistsException("Game List with name: "+gameList.getName()+" already exists!");
        }
    }

    @Override
    @Transactional
    public void moveGameList(Long gameListId,ReplacementRequestDto replacementRequestDto) {
        Integer sourceIndex= replacementRequestDto.getSourceIndex();
        Integer destinationIndex= replacementRequestDto.getDestinationIndex();

        List<GameProjection> gameProjectionList= gameRepository.getGameWithPositionListByGameListId(gameListId);

        validateIndexesOutOfBounds(gameProjectionList, sourceIndex, destinationIndex);

        int minIndex= Math.min(sourceIndex,destinationIndex);
        int maxIndex= Math.max(sourceIndex,destinationIndex);

        GameProjection tempGameProjection= gameProjectionList.remove((int)sourceIndex);
        gameProjectionList.add(destinationIndex,tempGameProjection);

        for(int i=minIndex;i<=maxIndex;i++){
            GameProjection gameProjection= gameProjectionList.get(i);
            gameListRepository.updateBelongingPosition(gameListId,gameProjection.getId(),i);
        }
    }

    private static void validateIndexesOutOfBounds(List<GameProjection> list,Integer sourceIndex, Integer destinationIndex) {
        if (sourceIndex >= list.size())
            throw new SourceIndexOutOfBoundsException("Invalid source index!");
        if (destinationIndex >= list.size())
            throw new DestinationIndexOutOfBoundsException("Invalid destination index!");
    }

    @Override
    @Transactional
    public void updateGameList(Long id, GameListRequestDto gameListRequestDto) {
        GameList gameList= gameListMapper.toGameList(gameListRequestDto);
        gameList.setId(id);

        GameList recoveredGameList= findGameListById(id);
        validateFieldsUpdateConflict(gameList, recoveredGameList);

        BeanUtils.copyProperties(gameList,recoveredGameList,"belongingList");//Note: ignore relationship properties
    }

    private void validateFieldsUpdateConflict(GameList gameList, GameList recoveredGameList) {
        if(gameListNameExistsAndBelongsToAnotherInstance(gameList.getName(), recoveredGameList)){
            throw new GameListNameExistsException("Game List with name: "+ gameList.getName()+" already exists!");
        }
    }

    private boolean gameListNameExistsAndBelongsToAnotherInstance(String gameListName, GameList recoveredGameList) {
        return gameListRepository.existsByName(gameListName) && !gameListName.equals(recoveredGameList.getName());
    }

    @Override
    @Transactional
    public void removeGameList(Long id) {
        GameList gameList= findGameListById(id);
        gameListRepository.delete(gameList);
    }

}
