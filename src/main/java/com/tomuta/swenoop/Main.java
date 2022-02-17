package com.tomuta.swenoop;
import java.util.Random;
import com.tomuta.swenoop.cards.Card;
import com.tomuta.swenoop.cards.Monster;
import com.tomuta.swenoop.cards.Spell;
import com.tomuta.swenoop.Fight.Fight;
import java.lang.String;
import com.tomuta.swenoop.Package.Package;

public class Main {
    public static void main(String[] args) {
        int uppermost_damage = 90;
        Random rand = new Random();
        Spell card3 = new Spell(rand.nextInt(uppermost_damage), Card.element_type.Fire);
        Monster card4 = new Monster(Monster.monster_type.Goblin, rand.nextInt(uppermost_damage), Card.element_type.Water);
        String ja = card4.getElem().name() + card4.getMons().name() + card4.getDamage();
        System.out.println(ja);
        String jo = card3.getElem().name() + card3.getCard_type();
        System.out.println(jo);
        Monster card5 = new Monster(Monster.monster_type.Dragon, 111, Card.element_type.Fire);
        String winner =  Fight.Specialitychecker(card4, card5);
        System.out.println(winner);
        Package pack = new Package();
        pack.five_cards_generator();



    }
}