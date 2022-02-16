package com.tomuta.swenoop.cards;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Card {

    private static int damage;
    public enum element_type{
        Fire,
        Water,
        Regular
    }

    private String card_type;
    private element_type elem;
    public Card(int damage, element_type elem){

        this.damage = damage;
        this.elem = elem;
    }
    public void printCard(element_type elem){
        System.out.println(elem);
    }

    public static int getDamage(){
        return damage;
    }
}
