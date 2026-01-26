package com.shaunbag.ff_server.controller;

import com.shaunbag.ff_server.model.Equipment;
import com.shaunbag.ff_server.model.dto.EquipmentDto;
import com.shaunbag.ff_server.services.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping("/equipment/{id}")
    public ResponseEntity<List<EquipmentDto>> getAllEquipmentByCharacterId(@PathVariable Long id){
        return ResponseEntity.ok(equipmentService.getEquipmentByCharacterId(id));
    }

    @PostMapping("/equipment")
    public ResponseEntity<EquipmentDto> saveEquipment(@RequestBody EquipmentDto equipmentDto){
        return ResponseEntity.ok(equipmentService.save(equipmentDto));
    }

    @DeleteMapping("/equipment/{id}")
    public ResponseEntity<String> deleteEquipmentById(@PathVariable Long id){
        equipmentService.deleteById(id);
        return ResponseEntity.ok("Equipment Deleted");
    }
}
