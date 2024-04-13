package com.example.gamerise.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "shelf")
public class Shelf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shelf_id")
    private Long shelfId;

    @Column(name = "shelf_name")
    private String shelfName;

    @OneToMany(mappedBy = "shelf")
    private List<UserGame> userGames;

    public Shelf() {
    }

    public Shelf(Long shelfId, String shelfName) {
        this.shelfId = shelfId;
        this.shelfName = shelfName;
    }

    public Long getShelfId() {
        return shelfId;
    }

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }
}