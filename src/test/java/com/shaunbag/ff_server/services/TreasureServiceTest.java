package com.shaunbag.ff_server.services;

import com.shaunbag.ff_server.dto.TreasureDto;
import com.shaunbag.ff_server.model.Character;
import com.shaunbag.ff_server.model.Treasure;
import com.shaunbag.ff_server.repository.CharacterRepository;
import com.shaunbag.ff_server.repository.TreasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TreasureServiceTest {

    @Mock
    private TreasureRepository treasureRepository;

    @Mock
    private CharacterRepository characterRepository;

    @InjectMocks
    private TreasureService treasureService;

    private Treasure testTreasure;
    private Character testCharacter;

    @BeforeEach
    void setUp() {
        testCharacter = new Character("TestHero", 10, 8, 20, 15, 10);
        testCharacter.setId(1L);

        testTreasure = new Treasure();
        testTreasure.setName("Gold Crown");
        testTreasure.setValue(1000);
        testTreasure.setCharacter(testCharacter);
        testTreasure.setId(1L);
    }

    @Test
    void treasureToDto() {
        //Arrange
        TreasureDto treasureDto = new TreasureDto(1L, "Gold Crown", 1000, 1L);

        //Act
        TreasureDto dto = treasureService.treasureToDto(testTreasure);

        //Assert
        assertNotNull(dto);
        assertEquals(treasureDto.id(), dto.id());
        assertEquals(treasureDto.name(), dto.name());
        assertEquals(treasureDto.value(), dto.value());
    }

    @Test
    void getAllTreasureByCharacter() {
        //Arrange
        when(treasureRepository.getAllTreasureByCharacterId(1L))
                .thenReturn(List.of(testTreasure));
        TreasureDto treasureDto = new TreasureDto(1L, "Gold Crown", 1000, 1L);

        //Act
        List<TreasureDto> dto = treasureService.getAllTreasureByCharacter(1L);

        //Assert
        assertNotNull(dto);
        assertFalse(dto.isEmpty());
        assertEquals(treasureDto, dto.get(0));
    }

    @Test
    void save() {
        //Arrange
        Treasure treasureSaved = new Treasure();
        treasureSaved.setName("Gold Crown");
        treasureSaved.setValue(1000);
        treasureSaved.setCharacter(testCharacter);
        treasureSaved.setId(1L);

        when(treasureRepository.save(any(Treasure.class))).thenReturn(treasureSaved);

        TreasureDto treasureDto = treasureService.treasureToDto(treasureSaved);

        //Act
        TreasureDto dto = treasureService.save(treasureDto);

        //Assert
        assertNotNull(dto);
        assertEquals(dto.name(), treasureSaved.getName());
        assertEquals(dto.value(), treasureSaved.getValue());
        assertEquals(dto.characterId(), testCharacter.getId());
        verify(treasureRepository, times(1)).save(any(Treasure.class));
    }

    @Test
    void deleteTreasure() {
        //Arrange
        Long id = 1L;

        //Act
        treasureService.deleteTreasure(id);

        //Assert
        verify(treasureRepository, times(1)).deleteById(id);

    }

    @Test
    void updateTreasure() {
        //Arrange
        Treasure updatedTreasure = new Treasure();
        updatedTreasure.setId(1L);
        updatedTreasure.setName("Gold Crown");
        updatedTreasure.setValue(2000);
        updatedTreasure.setCharacter(testCharacter);
        when(treasureRepository.save(any(Treasure.class))).thenReturn(updatedTreasure);
        when(treasureRepository.findById(1L)).thenReturn(Optional.of(updatedTreasure));

        TreasureDto treasureDto = new TreasureDto(1L, "Gold Crown", 2000, 1L);

        //Act
        TreasureDto dto = treasureService.updateTreasure(1L, treasureDto);

        //Assert
        assertNotNull(dto);
        assertEquals(dto.value(), updatedTreasure.getValue());
        assertEquals(dto.name(), updatedTreasure.getName());
    }
}