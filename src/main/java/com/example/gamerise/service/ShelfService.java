package com.example.gamerise.service;

import com.example.gamerise.api.model.Shelf;
import com.example.gamerise.repository.ShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShelfService {
    private final ShelfRepository shelfRepository;

    @Autowired
    public ShelfService(ShelfRepository shelfRepository) {
        this.shelfRepository = shelfRepository;
    }

    public Optional<Shelf> getShelfById(int id) {
        return shelfRepository.findById(id);
    }
}