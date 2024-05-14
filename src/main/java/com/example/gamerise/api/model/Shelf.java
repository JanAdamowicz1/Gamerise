package com.example.gamerise.api.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "shelf")
public class Shelf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shelf_id")
    private int shelfId;

    @Column(name = "shelf_name")
    private String shelfName;

    @OneToMany(mappedBy = "shelf")
    private List<UserGame> userGames;

    public Shelf() {
    }

    public Shelf(String shelfName) {
        this.shelfName = shelfName;
    }

    public int getShelfId() {
        return shelfId;
    }

    public void setShelfId(int shelfId) {
        this.shelfId = shelfId;
    }

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }
}