package com.tomuta.swenoop.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserA extends User {
    public UserA(String username, String password, int coins) {
        super(username, password, coins);
    }
}
