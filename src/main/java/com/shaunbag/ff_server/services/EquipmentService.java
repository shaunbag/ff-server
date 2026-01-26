package com.shaunbag.ff_server.services;

import com.shaunbag.ff_server.model.Equipment;
import com.shaunbag.ff_server.model.dto.EquipmentDto;
import com.shaunbag.ff_server.repository.CharacterRepository;
import com.shaunbag.ff_server.repository.EquipmentRepository;
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
                equipment.getName(),
                equipment.getEffect(),
                equipment.getCharacter().getId()
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
        return equipmentToDto(equipmentRepository.save(equipment));
    }

    @Transactional
    public void deleteById(Long id){
        equipmentRepository.deleteById(id);
    }
}
