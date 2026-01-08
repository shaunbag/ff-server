package com.shaunbag.ff_server.services;

import com.shaunbag.ff_server.model.Progress;
import com.shaunbag.ff_server.repository.ProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgressService {

    ProgressRepository progressRepository;

    @Autowired
    public ProgressService(ProgressRepository progressRepository){ this.progressRepository = progressRepository; }

    public List<Progress> getAllProgress(){ return progressRepository.findAll(); }

    public Optional<Progress> getAllProgressByPlayerId(Long id){ return progressRepository.findById(id);}
}
