package com.shaunbag.ff_server.repository;

import com.shaunbag.ff_server.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    List<Equipment> findByCharacterId(Long characterId);
}
