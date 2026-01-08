package com.shaunbag.ff_server.services;

import com.shaunbag.ff_server.model.Character;
import com.shaunbag.ff_server.model.dto.characterDto;
import com.shaunbag.ff_server.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {

    CharacterRepository characterRepository;

    @Autowired
    public CharacterService(CharacterRepository characterRepository){ this.characterRepository = characterRepository; }

    private List<Character> getAllCharacters() { return characterRepository.findAll(); }

    private Optional<Character> getCharacterById(Long id){ return characterRepository.findById(id); }

    public List<characterDto> getAllCharacterDto(){
        return characterRepository.findAll()
                .stream()
                .map(this::characterToDto)
                .toList();
    }

    public characterDto characterToDto(Character character) {
        characterDto characterDto = new characterDto(
                character.getId(),
                character.getName(),
                character.getSkill(),
                character.getLuck(),
                character.getStamina(),
                character.getGold() 
        );
        return characterDto;
    }

}
