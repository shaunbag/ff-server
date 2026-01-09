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

import org.json.JSONObject;


@RestController
@RequestMapping("/api")
public class controller {

	@Autowired
	CharacterService characterService;

	public List<Character> arr = new ArrayList<Character>();
	
	@GetMapping("/all")
	public ResponseEntity<String> getAll(){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("all", arr);
		return ResponseEntity.ok(jsonObject.toString());
	}
	
	@PostMapping("/createcharacter")
	public ResponseEntity<String> createCharacter(@RequestBody final CharacterResponseDto characterdto){
		JSONObject jsonObject = new JSONObject();
		CharacterCreateDto character = new CharacterCreateDto(
				characterdto.name(),
				characterdto.skill(),
				characterdto.luck(),
				characterdto.stamina(),
				characterdto.gold());
		try {
			characterService.save(character);
			jsonObject.put("Success", character);
		} catch (Exception exception) {
			jsonObject.put("Error", exception);
		}
		return ResponseEntity.ok(jsonObject.toString());
	}
	
	@GetMapping("/character/{id}")
	public ResponseEntity<String> getCharacter(@PathVariable int id){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("Character_name", arr.get(id).getName());
		jsonObject.put("Character_luck", arr.get(id).getLuck());
		jsonObject.put("Character_skill", arr.get(id).getSkill());
		jsonObject.put("Character_stamina", arr.get(id).getStamina());
		return ResponseEntity.ok(jsonObject.toString());
	}
	
}
