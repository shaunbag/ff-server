package com.shaunbag.ff_server.services;

import com.shaunbag.ff_server.model.Potion;
import com.shaunbag.ff_server.model.dto.PotionDto;
import com.shaunbag.ff_server.repository.CharacterRepository;
import com.shaunbag.ff_server.repository.PotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PotionService {


    private final PotionRepository potionRepository;
    private final CharacterRepository characterRepository;

    @Autowired
    public PotionService(PotionRepository potionRepository, CharacterRepository characterRepository){
        this.potionRepository = potionRepository;
        this.characterRepository = characterRepository;
    }

    public PotionDto potionToDto(Potion potion){
        return new PotionDto(
              potion.getName(), potion.getEffect(), potion.getCharacter().getId()
        );
    }

    public List<PotionDto> findPotionByCharacterId(Long characterId){
        return potionRepository.findByCharacterId(characterId)
                .stream()
                .map(this::potionToDto)
                .toList();
    }

    @Transactional
    public PotionDto save(PotionDto potionDto){
        Potion potion = new Potion();
        potion.setName(potionDto.name());
        potion.setEffect(potionDto.effect());
        potion.setCharacter(characterRepository.getReferenceById(potionDto.characterId()));
        return potionToDto(potionRepository.save(potion));
    }

    @Transactional
    public void deletePotionById(Long potionId){
        potionRepository.deleteById(potionId);
    }
}
