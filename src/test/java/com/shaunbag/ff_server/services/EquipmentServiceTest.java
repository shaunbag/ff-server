package com.shaunbag.ff_server.services;

import com.shaunbag.ff_server.model.Character;
import com.shaunbag.ff_server.model.Equipment;
import com.shaunbag.ff_server.model.dto.EquipmentDto;
import com.shaunbag.ff_server.repository.CharacterRepository;
import com.shaunbag.ff_server.repository.EquipmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EquipmentServiceTest {


    @Mock
    private EquipmentRepository equipmentRepository;

    @Mock
    private CharacterRepository characterRepository;

    @InjectMocks
    private EquipmentService equipmentService;

    private Equipment testEquipment;
    private Character testCharacter;

    @BeforeEach
    void setUp() {
        testCharacter = new Character("TestHero", 10, 8, 20, 15);
        testCharacter.setId(1L);

        testEquipment = new Equipment("Sword", "Makes you hit Harder +1", testCharacter);
        testEquipment.setId(1L);
    }

    @Test
    void equipmentToDto() {
        // Arrange
        EquipmentDto testDto = new EquipmentDto(1L, "Sword", "Makes you hit Harder +1", testCharacter.getId());
        // Act
        EquipmentDto dto = equipmentService.equipmentToDto(testEquipment);

        // Assert
        assertNotNull(dto);
        assertEquals(testDto, dto);

    }

    @Test
    void getEquipmentByCharacterId() {
        // Arrange
        when(equipmentRepository.findByCharacterId(1L))
                .thenReturn(List.of(testEquipment));
        EquipmentDto testDto = new EquipmentDto(1L,"Sword", "Makes you hit Harder +1", testCharacter.getId());

        // Act
        List<EquipmentDto> equipmentDtos = equipmentService.getEquipmentByCharacterId(1L);

        //Assert
        assertNotNull(equipmentDtos);
        assertFalse(equipmentDtos.isEmpty());
        assertEquals(testDto, equipmentDtos.get(0));
    }

    @Test
    void save() {
        // Arrange
        Equipment equipmentSaved = new Equipment();
        equipmentSaved.setId(1L);
        equipmentSaved.setName("Sword");
        equipmentSaved.setEffect("Makes you hit Harder +1");
        equipmentSaved.setCharacter(testCharacter);

        when(equipmentRepository.save(any(Equipment.class))).thenReturn(equipmentSaved);

        EquipmentDto dto = equipmentService.equipmentToDto(testEquipment);
        // Act
        EquipmentDto result = equipmentService.save(dto);

        // Assert
        assertNotNull(result);
        assertEquals("Sword", result.name());
        assertEquals("Makes you hit Harder +1", result.effect());
        assertEquals(testCharacter.getId(), result.characterId());
        verify(equipmentRepository, times(1)).save(any(Equipment.class));
    }

    @Test
    void deleteById() {

        // Arrange
        Long id = 1L;

        // Act
        equipmentService.deleteById(id);

        // Assert
        verify(equipmentRepository, times(1)).deleteById(id);
    }
}