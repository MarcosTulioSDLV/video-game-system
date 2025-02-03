package com.springboot.video_game_system.services;

import com.springboot.video_game_system.dtos.GameMinResponseDto;
import com.springboot.video_game_system.dtos.GameRequestDto;
import com.springboot.video_game_system.dtos.GameResponseDto;
import com.springboot.video_game_system.dtos.GameWithPositionResponseDto;
import com.springboot.video_game_system.exceptions.GameNotFoundException;
import com.springboot.video_game_system.exceptions.GameTitleExistsException;
import com.springboot.video_game_system.mappers.GameMapper;
import com.springboot.video_game_system.models.Belonging;
import com.springboot.video_game_system.models.Game;
import com.springboot.video_game_system.models.GameList;
import com.springboot.video_game_system.projections.GameProjection;
import com.springboot.video_game_system.repositories.GameRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class GameServiceImp implements GameService{

    private final GameRepository gameRepository;

    private final GameMapper gameMapper;

    private final GameListService gameListService;

    @Autowired
    public GameServiceImp(GameRepository gameRepository, GameMapper gameMapper, GameListService gameListService) {
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
        this.gameListService = gameListService;
    }

    @Override
    public Page<GameMinResponseDto> getAllGames(Pageable pageable) {
        return gameRepository.findAll(pageable).map(gameMapper::toGameMinResponseDto);
    }

    @Override
    public GameResponseDto getGameById(Long id) {
        Game game= findGameById(id);
        return gameMapper.toGameRequestDto(game);
    }

    public Game findGameById(Long id) {
        return gameRepository.findById(id).orElseThrow(() -> new GameNotFoundException("Game with id: " + id + " not found!"));
    }

    @Override
    public List<GameMinResponseDto> getGamesByGameListId(Long gameListId) {
        List<GameProjection> gameProjectionList = gameRepository.getGameWithPositionListByGameListId(gameListId);
        return gameProjectionList.stream().map(gameProjection -> GameMinResponseDto.builder()
                .id(gameProjection.getId())
                .title(gameProjection.getTitle())
                .releaseYear(gameProjection.getReleaseYear())
                .imgUrl(gameProjection.getImgUrl())
                .shortDescription(gameProjection.getShortDescription())
                .build())
                .toList();
    }

    //Note 1: Version 2 for the getGamesByGameListId method, avoiding the use of an advance SQL query method.
    //Note 2: @Transactional is required here because the relationship property "belongingList" is lazy by default.
    @Override
    @Transactional
    public List<GameMinResponseDto> getGamesByGameListIdV2(Long gameListId) {
        GameList gameList= gameListService.findGameListById(gameListId);
        List<Belonging> belongings= gameList.getBelongingList();
        return belongings.stream()
                .sorted(Comparator.comparingInt(Belonging::getPosition))
                .map(Belonging::getGame)
                .map(gameMapper::toGameMinResponseDto)
                .toList();
    }

    //Note: @Transactional is required here because the relationship property "belongingList" is lazy by default.
    @Override
    @Transactional
    public List<GameWithPositionResponseDto> getGameWithPositionListByGameListId(Long gameListId) {
        GameList gameList= gameListService.findGameListById(gameListId);
        List<Belonging> belongings= gameList.getBelongingList();

        return belongings.stream().map(belonging -> GameWithPositionResponseDto.builder()
                .id(belonging.getGame().getId())
                .title(belonging.getGame().getTitle())
                .releaseYear(belonging.getGame().getReleaseYear())
                .imgUrl(belonging.getGame().getImgUrl())
                .shortDescription(belonging.getGame().getShortDescription())
                .position(belonging.getPosition())
                .build())
                .sorted(Comparator.comparingInt(GameWithPositionResponseDto::getPosition))
                .toList();
    }

    @Override
    @Transactional
    public void addGame(GameRequestDto gameRequestDto) {
        Game game= gameMapper.toGame(gameRequestDto);
        validateUniqueFields(game);
        gameRepository.save(game);
    }

    private void validateUniqueFields(Game game) {
        if(gameRepository.existsByTitle(game.getTitle())){
            throw new GameTitleExistsException("Game with title: "+game.getTitle()+" already exists!");
        }
    }

    @Override
    @Transactional
    public void updateGame(Long id, GameRequestDto gameRequestDto) {
        Game game= gameMapper.toGame(gameRequestDto);
        game.setId(id);

        Game recoveredGame= findGameById(id);
        validateFieldsUpdateConflict(game, recoveredGame);

        BeanUtils.copyProperties(game,recoveredGame,"belongingList");//Note: Ignore relationship properties.
    }

    private void validateFieldsUpdateConflict(Game game, Game recoveredGame) {
        if(gameTitleExistsAndBelongsToAnotherInstance(game.getTitle(), recoveredGame)){
            throw new GameTitleExistsException("Game with title: "+ game.getTitle()+" already exists!");
        }
    }

    private boolean gameTitleExistsAndBelongsToAnotherInstance(String title, Game recoveredGame) {
        return gameRepository.existsByTitle(title) && !title.equals(recoveredGame.getTitle());
    }

    @Override
    @Transactional
    public void removeGame(Long id) {
        Game game= findGameById(id);
        updateBelongingPositionsInRelatedGameLists(game);
        gameRepository.delete(game);
    }

    private void updateBelongingPositionsInRelatedGameLists(Game game) {
        List<Belonging> belongings= game.getBelongingList();
        for(Belonging belonging: belongings){
            // Get the game list where the belonging resides.
            GameList currentGameList= belonging.getGameList();
            // Create a sorted copy of the belongings from the current game list by position.
            List<Belonging> belongingListFromCurrentGameList= new ArrayList<>(currentGameList.getBelongingList()
                    .stream()
                    .sorted(Comparator.comparingInt(Belonging::getPosition)).toList());
            // Find the index of the current belonging in the sorted list.
            int belongingIndexInCurrentGameList= belongingListFromCurrentGameList.indexOf(belonging);
            // Remove the belonging from the list.
            belongingListFromCurrentGameList.remove(belongingIndexInCurrentGameList);
            // Update the positions of the remaining belongings.
            for(int i=belongingIndexInCurrentGameList;i<belongingListFromCurrentGameList.size();i++){
                Belonging innerBelonging= belongingListFromCurrentGameList.get(i);
                innerBelonging.setPosition(i);
            }
        }
    }


}
