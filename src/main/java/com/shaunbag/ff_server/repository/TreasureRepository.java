package com.shaunbag.ff_server.repository;

import com.shaunbag.ff_server.model.Treasure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TreasureRepository extends JpaRepository<Treasure, Long> {

    List<Treasure> getAllTreasureByCharacterId(Long id);

}
