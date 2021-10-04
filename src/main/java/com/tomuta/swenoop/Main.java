package com.tomuta.swenoop;

import com.tomuta.swenoop.cards.Card;
import com.tomuta.swenoop.cards.Monster;
import com.tomuta.swenoop.cards.Spell;

public class Main {
    public static void main(String[] args) {

        Spell card3 = new Spell(47, Card.element_type.fire);
        Monster card4 = new Monster("troll", 456, Card.element_type.water);
        System.out.println(card4.getMonster_type());

    }
}