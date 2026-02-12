package com.shaunbag.ff_server.repository;

import com.shaunbag.ff_server.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterRepository extends JpaRepository<Character, Long> {

    List<Character> findAllByUserId(Long id);
}
