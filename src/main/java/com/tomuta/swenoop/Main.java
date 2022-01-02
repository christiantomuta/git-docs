package com.tomuta.swenoop;

import com.tomuta.swenoop.cards.Card;
import com.tomuta.swenoop.cards.Monster;
import com.tomuta.swenoop.cards.Spell;
import com.tomuta.swenoop.Fight.MonstervsMonster;
import java.lang.String;

public class Main {
    public static void main(String[] args) {

        Spell card3 = new Spell(47, Card.element_type.Fire);
        Monster card4 = new Monster(Monster.monster_type.Goblin, 456, Card.element_type.Water);
        String ja = card4.getElem().name() + card4.getMons().name();
        System.out.println(ja);
        String jo = card3.getElem().name() + card3.getCard_type();
        System.out.println(jo);
        Monster card5 = new Monster(Monster.monster_type.Dragon, 111, Card.element_type.Fire);
        String winner = MonstervsMonster.Monsterfight(card4, card5);
        System.out.println(winner);




    }
}