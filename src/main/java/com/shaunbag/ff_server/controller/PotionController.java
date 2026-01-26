package com.shaunbag.ff_server.controller;


import com.shaunbag.ff_server.model.dto.PotionDto;
import com.shaunbag.ff_server.services.PotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PotionController {


    @Autowired
    PotionService potionService;

    @GetMapping("/potions/{id}")
    public ResponseEntity<List<PotionDto>> getPotionsByCharacterId(@PathVariable Long id){
        return ResponseEntity.ok(potionService.findPotionByCharacterId(id));
    }

    @DeleteMapping("/potions/{id}")
    public ResponseEntity<String> deletePotionById(@PathVariable Long id){
        potionService.deletePotionById(id);
        return ResponseEntity.ok("Potion Deleted");
    }

    @PostMapping("/potions")
    public ResponseEntity<PotionDto> savePotion(@RequestBody PotionDto potionDto){
        return ResponseEntity.ok(potionService.save(potionDto));
    }

}
