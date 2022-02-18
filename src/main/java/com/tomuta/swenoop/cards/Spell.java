package com.tomuta.swenoop.cards;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Spell extends Card{
    public String card_type = "Spell";

    public void printCard(String card_type, element_type elem){
        System.out.println(elem);
        System.out.println(card_type);
    };
    public Spell(int damage, element_type elem) {
        super( damage, elem);
    }
}
