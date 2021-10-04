package com.tomuta.swenoop.cards;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Card {

    private static int damage;
    public enum element_type{
        fire,
        water,
        normal
    }
    private element_type elem;
    public Card(int damage, element_type elem){

        this.damage = damage;
        this.elem = elem;
    }

    public static int getDamage(){
        return damage;
    }
}
