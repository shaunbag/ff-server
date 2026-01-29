package com.shaunbag.ff_server.services;

import com.shaunbag.ff_server.model.Character;
import com.shaunbag.ff_server.model.Potion;
import com.shaunbag.ff_server.model.dto.PotionDto;
import com.shaunbag.ff_server.repository.CharacterRepository;
import com.shaunbag.ff_server.repository.PotionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PotionServiceTest {

    @Mock
    private PotionRepository potionRepository;

    @Mock
    private CharacterRepository characterRepository;

    @InjectMocks
    private PotionService potionService;

    private Potion testPotion;
    private Character testCharacter;


    @BeforeEach
    void setUp() {
        testCharacter = new Character("TestHero", 10, 8, 20, 15, 10);
        testCharacter.setId(1L);

        testPotion = new Potion();
        testPotion.setName("Potion of healing");
        testPotion.setEffect("Heals 1 Stamina");
        testPotion.setCharacter(testCharacter);
        testPotion.setId(1L);

    }

    @Test
    void potionToDto() {

        // Arrange
        PotionDto testDto = new PotionDto("Potion of healing", "Heals 1 Stamina", 1L);

        // Act
        PotionDto dto = potionService.potionToDto(testPotion);

        // Assert
        assertNotNull(dto);
        assertEquals(testDto, dto);
    }

    @Test
    void findPotionByCharacterId() {

        // Arrange
        when(potionRepository.findByCharacterId(1L))
                .thenReturn(List.of(testPotion));
        PotionDto testDto = new PotionDto("Potion of healing","Heals 1 Stamina", testCharacter.getId());

        // Act
        List<PotionDto> potionDtos = potionService.findPotionByCharacterId(1L);

        // Assert
        assertNotNull(potionDtos);
        assertFalse(potionDtos.isEmpty());
        assertEquals(testDto, potionDtos.get(0));
    }

    @Test
    void save() {

        // Arrange
        Potion potionSaved = new Potion();
        potionSaved.setCharacter(testCharacter);
        potionSaved.setId(1L);
        potionSaved.setName("Potion of healing");
        potionSaved.setEffect("Heals 1 Stamina");

        when(potionRepository.save(any(Potion.class))).thenReturn(potionSaved);

        PotionDto dto = potionService.potionToDto(potionSaved);

        // Act
        PotionDto result = potionService.save(dto);

        // Assert
        assertNotNull(result);
        assertEquals("Potion of healing", result.name());
        assertEquals("Heals 1 Stamina", result.effect());
        assertEquals(testCharacter.getId(), result.characterId());
        verify(potionRepository, times(1)).save(any(Potion.class));
    }

    @Test
    void deletePotionById() {

        // Arrange
        Long id = 1L;

        // Act
        potionService.deletePotionById(id);

        // Assert
        verify(potionRepository, times(1)).deleteById(id);
    }
}