package com.tomuta.swenoop.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class User {
    private String username;
    private String password;
    private int coins;

    public User(String username, String password, int coins) {
        this.username = username;
        this.password = password;
        this.coins = coins;
    }
}
