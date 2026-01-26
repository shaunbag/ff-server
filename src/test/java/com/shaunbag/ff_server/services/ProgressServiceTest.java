package com.shaunbag.ff_server.services;

import com.shaunbag.ff_server.model.Character;
import com.shaunbag.ff_server.model.Progress;
import com.shaunbag.ff_server.model.dto.ProgressDto;
import com.shaunbag.ff_server.repository.ProgressRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProgressServiceTest {

    @Mock
    private ProgressRepository progressRepository;

    @InjectMocks
    private ProgressService progressService;

    private Progress testProgress;
    private Character testCharacter;

    @BeforeEach
    void setUp() {
        testCharacter = new Character("TestHero", 10, 8, 20, 15);
        testCharacter.setId(1L);
        
        testProgress = new Progress("The Warlock of Firetop Mountain", 1);
        testProgress.setCharacter(testCharacter);
    }

    @Test
    void testGetAllProgress_ReturnsListOfProgress() {
        // Arrange
        Progress progress1 = new Progress("Book 1", 10);
        progress1.setCharacter(testCharacter);
        
        Progress progress2 = new Progress("Book 2", 25);
        progress2.setCharacter(testCharacter);
        
        List<Progress> progressList = Arrays.asList(progress1, progress2);
        
        when(progressRepository.findAll()).thenReturn(progressList);

        // Act
        List<Progress> result = progressService.getAllProgress();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Book 1", result.get(0).getBook());
        assertEquals(10, result.get(0).getSection());
        assertEquals("Book 2", result.get(1).getBook());
        assertEquals(25, result.get(1).getSection());
        verify(progressRepository, times(1)).findAll();
    }

    @Test
    void testGetAllProgress_ReturnsEmptyList() {
        // Arrange
        when(progressRepository.findAll()).thenReturn(List.of());

        // Act
        List<Progress> result = progressService.getAllProgress();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(progressRepository, times(1)).findAll();
    }

    @Test
    void testGetAllProgressByPlayerId_ReturnsProgress() {
        // Arrange
        when(progressRepository.findById(1L)).thenReturn(Optional.of(testProgress));

        // Act
        List<ProgressDto> result = progressService.getAllProgressByPlayerId(1L);

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());

        ProgressDto dto = result.get(0);
        assertEquals("The Warlock of Firetop Mountain", dto.book());
        assertEquals(1, dto.section());
        assertNotNull(dto.characterId());
        assertEquals(1L, dto.characterId());
        verify(progressRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllProgressByPlayerId_ReturnsEmptyWhenNotFound() {
        // Arrange
        when(progressRepository.findById(999L)).thenReturn(Optional.empty());

        // Act
        List<ProgressDto> result = progressService.getAllProgressByPlayerId(999L);

        // Assert
        assertTrue(result.isEmpty());
        verify(progressRepository, times(1)).findById(999L);
    }

    @Test
    void testGetAllProgressByPlayerId_WithDifferentId() {
        // Arrange
        Progress progress = new Progress("Citadel of Chaos", 50);
        Character character = new Character("AnotherHero", 12, 9, 22, 30);
        character.setId(2L);
        progress.setCharacter(character);
        
        when(progressRepository.findById(2L)).thenReturn(Optional.of(progress));

        // Act
        List<ProgressDto> result = progressService.getAllProgressByPlayerId(2L);

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        ProgressDto dto = result.get(0);
        assertEquals("Citadel of Chaos", dto.book());
        assertEquals(50, dto.section());
        assertEquals(2L, dto.characterId());
        verify(progressRepository, times(1)).findById(2L);
    }

    @Test
    void testGetAllProgress_WithMultipleProgressRecords() {
        // Arrange
        Progress progress1 = new Progress("Book 1", 1);
        Progress progress2 = new Progress("Book 1", 50);
        Progress progress3 = new Progress("Book 2", 100);
        
        List<Progress> progressList = Arrays.asList(progress1, progress2, progress3);
        
        when(progressRepository.findAll()).thenReturn(progressList);

        // Act
        List<Progress> result = progressService.getAllProgress();

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("Book 1", result.get(0).getBook());
        assertEquals(1, result.get(0).getSection());
        assertEquals("Book 1", result.get(1).getBook());
        assertEquals(50, result.get(1).getSection());
        assertEquals("Book 2", result.get(2).getBook());
        assertEquals(100, result.get(2).getSection());
        verify(progressRepository, times(1)).findAll();
    }
}
