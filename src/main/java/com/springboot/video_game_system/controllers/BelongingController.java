package com.springboot.video_game_system.controllers;

import com.springboot.video_game_system.dtos.BelongingRequestDto;
import com.springboot.video_game_system.dtos.BelongingResponseDto;
import com.springboot.video_game_system.dtos.ReplacementRequestDto;
import com.springboot.video_game_system.services.BelongingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Belonging Controller", description = "Controller for managing belongings.")
@RestController
@RequestMapping(value = "/api/v1/belongings")
public class BelongingController {

    private final BelongingService belongingService;

    @Autowired
    public BelongingController(BelongingService belongingService) {
        this.belongingService = belongingService;
    }

    @Operation(summary = "Get belonging by ID", description = "Retrieves a specific belonging by its ID.")
    @GetMapping()
    public ResponseEntity<BelongingResponseDto> getBelongingById(@RequestParam @NotNull @Positive Long gameId,
                                                                 @RequestParam @NotNull @Positive Long gameListId) {
        return ResponseEntity.ok(belongingService.getBelongingById(gameId,gameListId));
    }

    @Operation(summary = "Add a new belonging", description = "Creates a new belonging.")
    @PostMapping()
    public ResponseEntity<Object> addBelonging(@RequestBody @Valid BelongingRequestDto belongingRequestDto) {
        belongingService.addBelonging(belongingRequestDto);
        return ResponseEntity.ok("Belonging added successfully!");
    }

    // Note: This endpoint uses POST because calling the same query multiple times will always yield different results.
    // Example: If you call it with parameters (sourceIndex=1, destinationIndex=1), you will get different results each time, as the list order will continuously change.
    @Operation(summary = "Move games within a game list", description = "Moves games within a game list based on a given source index (game's original position) and destination index (game's destination position). This process will automatically update the positions of all games required in the game list.")
    @PostMapping(value = "/by-game-list/{gameListId}/move")
    public ResponseEntity<Object> moveBelongings(@PathVariable Long gameListId,
                                                 @RequestBody @Valid ReplacementRequestDto replacementRequestDto) {
        belongingService.moveGameList(gameListId, replacementRequestDto);
        return ResponseEntity.ok("Belongings moved successfully!");
    }

    @Operation(summary = "Delete a belonging", description = "Removes a belonging by its ID.")
    @DeleteMapping()
    public ResponseEntity<Object> removeBelonging(@RequestParam @NotNull @Positive Long gameId,
                                                  @RequestParam @NotNull @Positive Long gameListId) {
        belongingService.removeBelonging(gameId,gameListId);
        return ResponseEntity.ok("Belonging deleted successfully!");
    }
}
