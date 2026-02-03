package com.shaunbag.ff_server.controller;

import com.shaunbag.ff_server.dto.TreasureDto;
import com.shaunbag.ff_server.services.TreasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TreasureController {

    @Autowired
    TreasureService treasureService;

    @GetMapping("/treasure/{id}")
    public ResponseEntity<List<TreasureDto>> getAllTreasureByCharacterId(@PathVariable Long id){
        return ResponseEntity.ok(treasureService.getAllTreasureByCharacter(id));
    }

    @PostMapping("/treasure")
    public ResponseEntity<TreasureDto> addTreasure(@RequestBody TreasureDto treasureDto){
        return ResponseEntity.ok(treasureService.save(treasureDto));
    }

    @DeleteMapping("/treasure/{id}")
    public ResponseEntity<String> deleteTreasureById(@PathVariable Long id){
        treasureService.deleteTreasure(id);
        return ResponseEntity.ok("Treasure Deleted");
    }

    @PostMapping("/treasure/{id}")
    public ResponseEntity<TreasureDto> updateTreasure(@PathVariable Long id, @RequestBody TreasureDto treasureDto){
        return ResponseEntity.ok(treasureService.updateTreasure(id, treasureDto));
    }
}
