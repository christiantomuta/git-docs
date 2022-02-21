package com.tomuta.swenoop.user;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter

public class User {
    private UUID ID;
    private String username;
    private String password;
    private int coins;
    private String description;
    private int elo;

    public User(String username, String password) {
        ID = UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.coins = 20;
        this.description = "";
        this.elo = 100;
    }

    public User(UUID id, String username, String password, int coins, String description, int elo) {
        ID = id;
        this.username = username;
        this.password = password;
        this.coins = coins;
        this.description = description;
        this.elo = elo;
    }

}
