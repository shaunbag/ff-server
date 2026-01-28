package com.shaunbag.ff_server.services;

import com.shaunbag.ff_server.model.Progress;
import com.shaunbag.ff_server.model.dto.ProgressDto;
import com.shaunbag.ff_server.repository.CharacterRepository;
import com.shaunbag.ff_server.repository.ProgressRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProgressService {

    private final ProgressRepository progressRepository;
    private final CharacterRepository characterRepository;

    @Autowired
    public ProgressService(ProgressRepository progressRepository, CharacterRepository characterRepository){
        this.progressRepository = progressRepository;
        this.characterRepository = characterRepository;
    }

    public ProgressDto progressToDto(Progress progress){
        return new ProgressDto(progress.getId(), progress.getBook(), progress.getSection(), progress.getCharacter().getId());
    }

    public List<Progress> getAllProgress(){ return progressRepository.findAll(); }

    public List<ProgressDto> getAllProgressByPlayerId(Long id){
        return progressRepository.findByCharacterId(id).stream().map(this::progressToDto).toList();
    }

    @Transactional
    public ProgressDto save(ProgressDto progressDto){
        Progress progress = new Progress();
        progress.setBook(progressDto.book());
        progress.setSection(progressDto.section());
        progress.setCharacter(characterRepository.getReferenceById(progressDto.characterId()));
        return progressToDto(progressRepository.save(progress));
    }

    @Transactional
    public void deleteProgressById(Long id){
        progressRepository.deleteById(id);
    }

    @Transactional
    public ProgressDto updateProgressById(Long id, ProgressDto progressDto){
        Progress currentProgress = progressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Progress Found Matching Id " + id)
                );
        currentProgress.setBook(progressDto.book());
        currentProgress.setSection(progressDto.section());

        progressRepository.save(currentProgress);
        return progressToDto(currentProgress);
    }
}
