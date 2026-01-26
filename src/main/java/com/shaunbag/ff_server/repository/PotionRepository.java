package com.shaunbag.ff_server.repository;

import com.shaunbag.ff_server.model.Potion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PotionRepository extends JpaRepository<Potion, Long> {

    List<Potion> findByCharacterId(Long characterId);
}
