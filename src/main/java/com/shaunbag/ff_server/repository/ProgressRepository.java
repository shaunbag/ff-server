package com.shaunbag.ff_server.repository;

import com.shaunbag.ff_server.model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgressRepository extends JpaRepository<Progress, Long>{

    List<Progress> findByCharacterId(Long id);
}
