package com.shaunbag.ff_server.repository;

import com.shaunbag.ff_server.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Long> {

}
