package com.tomuta.swenoop.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserB extends User {
    public UserB(String username, String password, int coins) {
        super(username, password, coins);
    }
}
