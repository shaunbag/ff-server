package com.shaunbag.ff_server.services;

import com.shaunbag.ff_server.model.Character;
import com.shaunbag.ff_server.dto.CharacterCreateDto;
import com.shaunbag.ff_server.dto.CharacterResponseDto;
import com.shaunbag.ff_server.model.MyUser;
import com.shaunbag.ff_server.repository.CharacterRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {

    CharacterRepository characterRepository;
    private final MyUserService myUserService;

    public CharacterService(CharacterRepository characterRepository, MyUserService myUserService){
        this.characterRepository = characterRepository;
        this.myUserService = myUserService;
    }

    private List<Character> getAllCharacters() { return characterRepository.findAll(); }

    private Optional<Character> getCharacterById(Long id){ return characterRepository.findById(id); }

    public List<CharacterResponseDto> getAllCharacterDto(UserDetails userDetails){
        MyUser user = myUserService.getMyUserDetails(userDetails);

        return characterRepository.findAllByUserId(user.getId())
                .stream()
                .map(this::characterToDto)
                .toList();
    }

    public CharacterResponseDto getCharacterDtoById(Long id){
        Character character = characterRepository.getReferenceById(id);
        return characterToDto(character);
    }

    public CharacterResponseDto characterToDto(Character character) {
        return new CharacterResponseDto(
                character.getId(),
                character.getName(),
                character.getSkill(),
                character.getLuck(),
                character.getStamina(),
                character.getGold(),
                character.getProvisions()
        );
    }


    public CharacterResponseDto save(CharacterCreateDto characterCreateDto, UserDetails userDetails){
        Character character = new Character(
                characterCreateDto.name(),
                characterCreateDto.skill(),
                characterCreateDto.luck(),
                characterCreateDto.stamina(),
                characterCreateDto.gold(),
                characterCreateDto.provisions()
        );
        MyUser user = myUserService.getMyUserDetails(userDetails);
        character.setUser(user);
        Character characterSaved = characterRepository.save(character);
        return characterToDto((characterSaved));
    }

    public void deleteCharacter(Long id){
        if(!characterRepository.existsById(id)){
            throw new EntityNotFoundException(("Character Not Found With Id: " + id));
        }
        characterRepository.deleteById(id);
    }

    public CharacterResponseDto updateCharacter(CharacterCreateDto characterCreateDto, Long id){
        Character character = characterRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("No Character Found With Id " + id)
                );

        character.setName(characterCreateDto.name());
        character.setStamina(characterCreateDto.stamina());
        character.setSkill(characterCreateDto.skill());
        character.setGold(characterCreateDto.gold());
        character.setLuck(characterCreateDto.luck());

        return characterToDto(characterRepository.save(character));

    }

}
