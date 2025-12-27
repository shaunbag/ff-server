package com.shaunbag.ff_server.controller;

import com.shaunbag.ff_server.model.dto.characterDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shaunbag.ff_server.model.Character;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class controller {

	public List<Character> arr = new ArrayList<Character>();
	
	@GetMapping("/api/all")
	public ResponseEntity<String> getAll(){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("all", arr);
		return ResponseEntity.ok(jsonObject.toString());
	}
	
	@PostMapping("/api/createcharacter")
	public ResponseEntity<String> createCharacter(@RequestBody final characterDto characterdto){
		JSONObject jsonObject = new JSONObject();
		arr.add(new Character(Long.valueOf(arr.size()),
				characterdto.name(),
				characterdto.skill(),
				characterdto.luck(),
				characterdto.stamina(),
				characterdto.gold()));
		
		jsonObject.put("Characters", arr);
		return ResponseEntity.ok(jsonObject.toString());
	}
	
	@GetMapping("/api/character/{id}")
	public ResponseEntity<String> getCharacter(@PathVariable int id){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("Character_name", arr.get(id).getName());
		jsonObject.put("Character_luck", arr.get(id).getLuck());
		jsonObject.put("Character_skill", arr.get(id).getSkill());
		jsonObject.put("Character_stamina", arr.get(id).getStamina());
		return ResponseEntity.ok(jsonObject.toString());
	}
	
}
