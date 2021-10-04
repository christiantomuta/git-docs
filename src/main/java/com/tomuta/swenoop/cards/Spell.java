package com.tomuta.swenoop.cards;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Spell extends Card{
    public Spell(int damage, element_type elem) {
        super(damage, elem);
    }
}
