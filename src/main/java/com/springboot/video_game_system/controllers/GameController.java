package com.springboot.video_game_system.controllers;

import com.springboot.video_game_system.dtos.GameMinResponseDto;
import com.springboot.video_game_system.dtos.GameRequestDto;
import com.springboot.video_game_system.dtos.GameResponseDto;
import com.springboot.video_game_system.dtos.GameWithPositionResponseDto;
import com.springboot.video_game_system.services.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Game Controller", description = "Controller for managing games.")
@RestController
@RequestMapping(value = "/api/v1/games")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    //Note: A minimum version of the game is required to display all games in a general list, that's why GameMinRequestDto is necessary.
    @Operation(summary = "Get all games", description = "Retrieves a paginated list of all games. This returns simplified game information with just a few attributes for each game.")
    @GetMapping
    public ResponseEntity<Page<GameMinResponseDto>> getAllGames(@PageableDefault(size = 10) Pageable pageable){
        return ResponseEntity.ok(gameService.getAllGames(pageable));
    }

    //Note: The full version of the game is required because when clicking on one, all its complete information must be displayed. That's why GameRequestDto is necessary here.
    @Operation(summary = "Get game by ID", description = "Retrieves a specific game by its ID. This returns full game information with all its attributes.")
    @GetMapping(value = "/{id}")
    public ResponseEntity<GameResponseDto> getGameById(@PathVariable Long id){
        return ResponseEntity.ok(gameService.getGameById(id));
    }

    @Operation(summary = "Get games from a game list", description = "Retrieves all games associated with a specific game list.")
    @GetMapping(value = "/by-game-list/{gameListId}")
    public ResponseEntity<List<GameMinResponseDto>> getGamesByGameListId(@PathVariable Long gameListId){
        return ResponseEntity.ok(gameService.getGamesByGameListId(gameListId));
    }

    @Operation(summary = "Get games with position from a game list", description = "Retrieves all games with their positions from a specific game list.")
    @GetMapping(value = "/by-game-list/{gameListId}/games-with-position")
    public ResponseEntity<List<GameWithPositionResponseDto>> getGameWithPositionListByGameListId(@PathVariable Long gameListId){
        return ResponseEntity.ok(gameService.getGameWithPositionListByGameListId(gameListId));
    }

    @Operation(summary = "Add a new game", description = "Creates a new game with the provided details.")
    @PostMapping()
    public ResponseEntity<Object> addGame(@RequestBody @Valid GameRequestDto gameRequestDto){
        gameService.addGame(gameRequestDto);
        return ResponseEntity.ok("Game added successfully!");
    }

    @Operation(summary = "Update a game", description = "Updates the details of an existing game.")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateGame(@PathVariable Long id,
                                             @RequestBody @Valid GameRequestDto gameRequestDto){
        gameService.updateGame(id,gameRequestDto);
        return ResponseEntity.ok("Game updated successfully!");
    }

    @Operation(summary = "Delete a game", description = "Removes a game by its ID.")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> removeGame(@PathVariable Long id){
        gameService.removeGame(id);
        return ResponseEntity.ok("Game deleted successfully!");
    }

}
