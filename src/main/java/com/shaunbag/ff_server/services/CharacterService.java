package com.shaunbag.ff_server.services;

import com.shaunbag.ff_server.model.Character;
import com.shaunbag.ff_server.model.dto.CharacterCreateDto;
import com.shaunbag.ff_server.model.dto.CharacterResponseDto;
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

    public List<CharacterResponseDto> getAllCharacterDto(){
        return characterRepository.findAll()
                .stream()
                .map(this::characterToDto)
                .toList();
    }

    public CharacterResponseDto characterToDto(Character character) {
        return new CharacterResponseDto(
                character.getId(),
                character.getName(),
                character.getSkill(),
                character.getLuck(),
                character.getStamina(),
                character.getGold()
        );
    }

    public void save(CharacterCreateDto characterCreateDto){
        Character character = new Character(
                characterCreateDto.name(),
                characterCreateDto.skill(),
                characterCreateDto.luck(),
                characterCreateDto.stamina(),
                characterCreateDto.gold()
        );
        characterRepository.save(character);
    }
}
