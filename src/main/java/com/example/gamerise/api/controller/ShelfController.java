package com.example.gamerise.api.controller;

import com.example.gamerise.api.model.Shelf;
import com.example.gamerise.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/shelf")
public class ShelfController {
    private final ShelfService shelfService;

    @Autowired
    public ShelfController(ShelfService shelfService) {
        this.shelfService = shelfService;
    }

    @GetMapping("/{id}")
    public String getShelfName(@PathVariable int id) {
        Optional<Shelf> shelf = shelfService.getShelfById(id);
        return shelf.map(Shelf::getShelfName)
                .orElse(null);
    }

}