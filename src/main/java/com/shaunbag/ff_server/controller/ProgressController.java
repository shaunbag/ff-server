package com.shaunbag.ff_server.controller;

import com.shaunbag.ff_server.model.dto.ProgressDto;
import com.shaunbag.ff_server.services.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProgressController {


    @Autowired
    ProgressService progressService;


    @GetMapping("/progress")
    public ResponseEntity<List<ProgressDto>> getAllProgressByCharacterId(@PathVariable Long id){
        return ResponseEntity.ok(progressService.getAllProgressByPlayerId(id));
    }

    @PostMapping("/progress")
    public ResponseEntity<ProgressDto> saveProgress(@RequestBody ProgressDto progressDto){
        return ResponseEntity.ok(progressService.save(progressDto));
    }

    @DeleteMapping("progress")
    public ResponseEntity<String> deleteProgressById(@PathVariable Long id){
        progressService.deleteProgressById(id);
        return ResponseEntity.ok("Progress Deleted");
    }
}
