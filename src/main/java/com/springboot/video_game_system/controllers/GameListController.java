package com.springboot.video_game_system.controllers;

import com.springboot.video_game_system.dtos.GameListRequestDto;
import com.springboot.video_game_system.dtos.GameListResponseDto;
import com.springboot.video_game_system.dtos.GameMinResponseDto;
import com.springboot.video_game_system.dtos.ReplacementRequestDto;
import com.springboot.video_game_system.services.GameListService;
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

@Tag(name = "Game List Controller", description = "Controller for managing game lists.")
@RestController
@RequestMapping(value = "/api/v1/game-lists")
public class GameListController {

    private final GameListService gameListService;

    private final GameService gameService;

    @Autowired
    public GameListController(GameListService gameListService, GameService gameService) {
        this.gameListService = gameListService;
        this.gameService = gameService;
    }

    @Operation(summary = "Get all game lists", description = "Retrieves a paginated list of all game lists.")
    @GetMapping()
    public ResponseEntity<Page<GameListResponseDto>> getAllGameLists(@PageableDefault(size = 10) Pageable pageable){
        return ResponseEntity.ok(gameListService.getAllGameLists(pageable));
    }

    @Operation(summary = "Get game list by ID", description = "Retrieves a specific game list by its ID.")
    @GetMapping(value = "/{gameListId}")
    public ResponseEntity<GameListResponseDto> getGameListById(@PathVariable Long gameListId){
        return ResponseEntity.ok(gameListService.getGameListById(gameListId));
    }

    @Operation(summary = "Get games from a game list", description = "Retrieves all games associated with a specific game list.")
    @GetMapping(value = "/{gameListId}/games")
    public ResponseEntity<List<GameMinResponseDto>> getGamesByGameListId(@PathVariable Long gameListId){
        return ResponseEntity.ok(gameService.getGamesByGameListId(gameListId));
    }

    //Note: Version 2 for the getGamesByGameListId method, avoiding the use of an advance SQL query method.
    @Operation(summary = "Get games from a game list (alternative method)",
            description = "Retrieves all games from a game list using an alternative method that avoids advanced SQL queries.")
    @GetMapping(value = "/{gameListId}/games-v2")
    public ResponseEntity<List<GameMinResponseDto>> getGamesByGameListIdV2(@PathVariable Long gameListId){
        return ResponseEntity.ok(gameService.getGamesByGameListIdV2(gameListId));
    }

    @Operation(summary = "Add a new game list", description = "Creates a new game list with the provided details.")
    @PostMapping()
    public ResponseEntity<Object> addGameList(@RequestBody @Valid GameListRequestDto gameListRequestDto){
        gameListService.addGameList(gameListRequestDto);
        return ResponseEntity.ok("Game List added successfully!");
    }

    // Note: This endpoint uses POST because calling the same query multiple times will always yield different results.
    // Example: If you call it with parameters (sourceIndex=1, destinationIndex=1), you will get different results each time, as the list order will continuously change.
    @Operation(summary = "Move games within a game list",
            description = "Moves games within a game list based on a given source index (game's original position) and destination index (game's destination position). This process will automatically update the positions of all games required in the game list.")
    @PostMapping(value = "/{gameListId}/move")
    public ResponseEntity<Object> moveBelongingsFromGameList(@PathVariable Long gameListId,
                                                             @RequestBody @Valid ReplacementRequestDto replacementRequestDto) {
        gameListService.moveGameList(gameListId,replacementRequestDto);
        return ResponseEntity.ok("Game List's Belongings moved successfully!");
    }

    @Operation(summary = "Update a game list", description = "Updates the details of an existing game list.")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateGameList(@PathVariable Long id,
                                                 @RequestBody @Valid GameListRequestDto gameListRequestDto){
        gameListService.updateGameList(id,gameListRequestDto);
        return ResponseEntity.ok("Game List updated successfully!");
    }

    @Operation(summary = "Delete a game list", description = "Removes a game list by its ID.")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> removeGameList(@PathVariable Long id){
        gameListService.removeGameList(id);
        return ResponseEntity.ok("Game List deleted successfully!");
    }

}
