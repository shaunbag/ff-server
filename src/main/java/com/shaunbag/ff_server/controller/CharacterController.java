package com.shaunbag.ff_server.controller;

import com.shaunbag.ff_server.model.dto.CharacterCreateDto;
import com.shaunbag.ff_server.model.dto.CharacterResponseDto;
import com.shaunbag.ff_server.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shaunbag.ff_server.model.Character;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class CharacterController {

	@Autowired
	CharacterService characterService;

	public List<Character> arr = new ArrayList<Character>();
	
	@GetMapping("/all")
	public ResponseEntity<List<CharacterResponseDto>> getAll(){
		List<CharacterResponseDto> characterResponseDtos = characterService.getAllCharacterDto();
		return ResponseEntity.ok(characterResponseDtos);
	}
	
	@PostMapping("/createcharacter")
	public ResponseEntity<CharacterResponseDto> createCharacter(@RequestBody final CharacterCreateDto characterCreateDto){
		CharacterCreateDto character = new CharacterCreateDto(
				characterCreateDto.name(),
				characterCreateDto.skill(),
				characterCreateDto.luck(),
				characterCreateDto.stamina(),
				characterCreateDto.gold());
		CharacterResponseDto characterResponseDto = characterService.save(character);
		return ResponseEntity.ok(characterResponseDto);
	}
	
	@GetMapping("/character/{id}")
	public ResponseEntity<CharacterResponseDto> getCharacter(@PathVariable Long id){
		CharacterResponseDto characterResponseDto = characterService.getCharacterDtoById(id);
		return ResponseEntity.ok(characterResponseDto);
	}

	@DeleteMapping("/character/{id}")
	public ResponseEntity<Void> deleteCharacter(@PathVariable Long id){
		characterService.deleteCharacter((id));
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/character/{id}")
	public ResponseEntity<CharacterResponseDto> updateCharacter(@PathVariable Long id, @RequestBody CharacterCreateDto characterCreateDto){
		return ResponseEntity.ok(characterService.updateCharacter(characterCreateDto, id));
	}
	
}
