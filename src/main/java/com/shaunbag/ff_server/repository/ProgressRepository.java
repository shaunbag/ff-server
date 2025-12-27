package com.shaunbag.ff_server.repository;

import com.shaunbag.ff_server.model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressRepository extends JpaRepository<Progress, Long>{
}
