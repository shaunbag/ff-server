package com.shaunbag.ff_server.services;

import com.shaunbag.ff_server.model.Equipment;
import com.shaunbag.ff_server.dto.EquipmentDto;
import com.shaunbag.ff_server.repository.CharacterRepository;
import com.shaunbag.ff_server.repository.EquipmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final CharacterRepository characterRepository;

    @Autowired
    public EquipmentService(EquipmentRepository equipmentRepository, CharacterRepository characterRepository){
        this.equipmentRepository = equipmentRepository;
        this.characterRepository = characterRepository;
    }

    public EquipmentDto equipmentToDto(Equipment equipment){
        return new EquipmentDto(
                equipment.getId(),
                equipment.getName(),
                equipment.getEffect(),
                equipment.getCharacter().getId(),
                equipment.getGivesBonus(),
                equipment.getBonus(),
                equipment.getSkill(),
                equipment.getInUse()
        );
    }

    public List<EquipmentDto> getEquipmentByCharacterId(Long id){
        return equipmentRepository.findByCharacterId(id)
                .stream()
                .map(this::equipmentToDto)
                .toList();
    }

    @Transactional
    public EquipmentDto save(EquipmentDto equipmentDto){
        Equipment equipment = new Equipment();
        equipment.setName(equipmentDto.name());
        equipment.setEffect(equipmentDto.effect());
        equipment.setCharacter(characterRepository.getReferenceById(equipmentDto.characterId()));
        equipment.setGivesBonus(equipmentDto.givesBonus());
        equipment.setBonus(equipmentDto.bonus());
        equipment.setSkill(equipmentDto.skill());
        equipment.setInUse(equipmentDto.inUse());
        return equipmentToDto(equipmentRepository.save(equipment));
    }

    @Transactional
    public void deleteById(Long id){
        equipmentRepository.deleteById(id);
    }

    @Transactional
    public EquipmentDto updateEquipmentById(Long id, EquipmentDto equipmentDto){
        Equipment currentEquipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Equipment Found For Id " + id));
        currentEquipment.setInUse(equipmentDto.inUse());
        equipmentRepository.save(currentEquipment);

        return equipmentToDto(currentEquipment);
    }
}
