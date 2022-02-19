package com.tomuta.swenoop.cards;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Spell extends Card{
    private static String card_type = "Spell";
    public static String getCard_type() {
        return card_type;
    }

    public void printCard(String card_type, element_type elem){
        System.out.println(elem);
        System.out.println(card_type);
    };
    public Spell(String card_type, int damage, element_type elem) {
        super(card_type, damage, elem);
    }
}
