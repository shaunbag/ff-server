package com.shaunbag.ff_server.services;

import com.shaunbag.ff_server.dto.TreasureDto;
import com.shaunbag.ff_server.model.Treasure;
import com.shaunbag.ff_server.repository.CharacterRepository;
import com.shaunbag.ff_server.repository.TreasureRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TreasureService {

    private final TreasureRepository treasureRepository;
    private final CharacterRepository characterRepository;

    @Autowired
    public TreasureService(TreasureRepository treasureRepository, CharacterRepository characterRepository) {
        this.treasureRepository = treasureRepository;
        this.characterRepository = characterRepository;
    }

    public TreasureDto treasureToDto(Treasure treasure){
        return new TreasureDto(
                treasure.getId(),
                treasure.getName(),
                treasure.getValue(),
                treasure.getCharacter().getId()
        );
    }

    public List<TreasureDto> getAllTreasureByCharacter(Long id){
        return treasureRepository.getAllTreasureByCharacterId(id).stream().map(this::treasureToDto).toList();
    }

    @Transactional
    public TreasureDto save(TreasureDto treasureDto){
        Treasure treasure = new Treasure();
        treasure.setName(treasureDto.name());
        treasure.setValue(treasureDto.value());
        treasure.setCharacter(characterRepository.getReferenceById(treasureDto.characterId()));

        return treasureToDto(treasureRepository.save(treasure));
    }

    @Transactional
    public void deleteTreasure(Long id){
        treasureRepository.deleteById(id);
    }

    @Transactional
    public TreasureDto updateTreasure(Long id, TreasureDto treasureDto){
        Treasure currentTreasure = treasureRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No treasure Found Matching Id " + id));

        currentTreasure.setName(treasureDto.name());
        currentTreasure.setValue(treasureDto.value());
        return treasureToDto(treasureRepository.save(currentTreasure));
    }
}
