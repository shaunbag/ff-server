package com.shaunbag.ff_server.services;

import com.shaunbag.ff_server.model.Character;
import com.shaunbag.ff_server.model.dto.CharacterCreateDto;
import com.shaunbag.ff_server.model.dto.CharacterResponseDto;
import com.shaunbag.ff_server.repository.CharacterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CharacterServiceTest {

    @Mock
    private CharacterRepository characterRepository;

    @InjectMocks
    private CharacterService characterService;

    private Character testCharacter;
    private CharacterCreateDto testCharacterCreateDto;

    @BeforeEach
    void setUp() {
        testCharacter = new Character("TestHero", 10, 8, 20, 15, 10);
        testCharacter.setId(1L);
        
        testCharacterCreateDto = new CharacterCreateDto(
            "TestHero",
            10,
            8,
            20,
            15,
                10
        );
    }

    @Test
    void testGetAllCharacterDto_ReturnsListOfDtos() {
        // Arrange
        Character character1 = new Character("Hero1", 10, 8, 20, 15, 10);
        character1.setId(1L);
        Character character2 = new Character("Hero2", 12, 9, 22, 20, 10);
        character2.setId(2L);
        List<Character> characters = Arrays.asList(character1, character2);

        when(characterRepository.findAll()).thenReturn(characters);

        // Act
        List<CharacterResponseDto> result = characterService.getAllCharacterDto();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Hero1", result.get(0).name());
        assertEquals(10, result.get(0).skill());
        assertEquals("Hero2", result.get(1).name());
        assertEquals(12, result.get(1).skill());
        verify(characterRepository, times(1)).findAll();
    }

    @Test
    void testGetAllCharacterDto_ReturnsEmptyList() {
        // Arrange
        when(characterRepository.findAll()).thenReturn(List.of());

        // Act
        List<CharacterResponseDto> result = characterService.getAllCharacterDto();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(characterRepository, times(1)).findAll();
    }

    @Test
    void testGetCharacterDtoById_ReturnsCharacterDto() {
        // Arrange
        when(characterRepository.getReferenceById(1L)).thenReturn(testCharacter);

        // Act
        CharacterResponseDto result = characterService.getCharacterDtoById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("TestHero", result.name());
        assertEquals(10, result.skill());
        assertEquals(8, result.luck());
        assertEquals(20, result.stamina());
        assertEquals(15, result.gold());
        verify(characterRepository, times(1)).getReferenceById(1L);
    }

    @Test
    void testGetCharacterDtoById_WithDifferentId() {
        // Arrange
        Character character = new Character("AnotherHero", 11, 7, 18, 12, 10);
        character.setId(2L);
        when(characterRepository.getReferenceById(2L)).thenReturn(character);

        // Act
        CharacterResponseDto result = characterService.getCharacterDtoById(2L);

        // Assert
        assertNotNull(result);
        assertEquals(2L, result.id());
        assertEquals("AnotherHero", result.name());
        assertEquals(11, result.skill());
        verify(characterRepository, times(1)).getReferenceById(2L);
    }

    @Test
    void testSave_CreatesAndReturnsCharacterDto() {
        // Arrange
        Character savedCharacter = new Character(
            testCharacterCreateDto.name(),
            testCharacterCreateDto.skill(),
            testCharacterCreateDto.luck(),
            testCharacterCreateDto.stamina(),
            testCharacterCreateDto.gold(),
                testCharacterCreateDto.provisions()
        );
        savedCharacter.setId(1L);
        
        when(characterRepository.save(any(Character.class))).thenReturn(savedCharacter);

        // Act
        CharacterResponseDto result = characterService.save(testCharacterCreateDto);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("TestHero", result.name());
        assertEquals(10, result.skill());
        assertEquals(8, result.luck());
        assertEquals(20, result.stamina());
        assertEquals(15, result.gold());
        verify(characterRepository, times(1)).save(any(Character.class));
    }

    @Test
    void testSave_WithDifferentCharacterData() {
        // Arrange
        CharacterCreateDto createDto = new CharacterCreateDto(
            "Warrior",
            15,
            5,
            25,
            50,
                10
        );
        Character savedCharacter = new Character(
            createDto.name(),
            createDto.skill(),
            createDto.luck(),
            createDto.stamina(),
            createDto.gold(),
                createDto.provisions()
        );
        savedCharacter.setId(3L);
        
        when(characterRepository.save(any(Character.class))).thenReturn(savedCharacter);

        // Act
        CharacterResponseDto result = characterService.save(createDto);

        // Assert
        assertNotNull(result);
        assertEquals(3L, result.id());
        assertEquals("Warrior", result.name());
        assertEquals(15, result.skill());
        assertEquals(5, result.luck());
        assertEquals(25, result.stamina());
        assertEquals(50, result.gold());
        verify(characterRepository, times(1)).save(any(Character.class));
    }

    @Test
    void testCharacterToDto_ConvertsCorrectly() {
        // Act
        CharacterResponseDto result = characterService.characterToDto(testCharacter);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("TestHero", result.name());
        assertEquals(10, result.skill());
        assertEquals(8, result.luck());
        assertEquals(20, result.stamina());
        assertEquals(15, result.gold());
    }

    @Test
    void testCharacterToDto_WithNullValues() {
        // Arrange
        Character characterWithNulls = new Character(null, null, null, null, null, null);
        characterWithNulls.setId(5L);

        // Act
        CharacterResponseDto result = characterService.characterToDto(characterWithNulls);

        // Assert
        assertNotNull(result);
        assertEquals(5L, result.id());
        assertNull(result.name());
        assertNull(result.skill());
        assertNull(result.luck());
        assertNull(result.stamina());
        assertNull(result.gold());
    }

    @Test
    void testDeleteCharacter(){
        // Arrange
        Long id = 1L;
        when(characterRepository.existsById(id)).thenReturn(true);

        // Act
        characterService.deleteCharacter(id);

        // Assert
        verify(characterRepository, times(1)).deleteById(id);
    }

    @Test
    void testUpdateCharacter() {
        // Arrange
        Long id = 1L;
        Character existing = new Character("Old", 5, 5, 5, 10, 10);
        existing.setId(id);

        when(characterRepository.findById(id)).thenReturn(Optional.of(existing));
        when(characterRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        // Act
        CharacterCreateDto dto = new CharacterCreateDto(
                "New",
                10,
                9,
                8,
                20,
                10
        );

        CharacterResponseDto updated = characterService.updateCharacter(dto, id);

        // Assert
        assertEquals("New", updated.name());
        assertEquals(10, updated.skill());
    }
}
