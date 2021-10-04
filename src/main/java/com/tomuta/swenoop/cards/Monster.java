package com.tomuta.swenoop.cards;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Monster extends Card{

    private String monster_type;

    public Monster(String monster_type, int damage, element_type elem) {
        super(damage, elem);
        this.monster_type = monster_type;
    }
}
