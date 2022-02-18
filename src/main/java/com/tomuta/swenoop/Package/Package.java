package com.tomuta.swenoop.Package;

import com.tomuta.swenoop.cards.Card;
import com.tomuta.swenoop.cards.Monster;
import com.tomuta.swenoop.cards.Spell;

import java.lang.String;
import java.util.Random;
import java.sql.*;

public class Package {
    public Card one_card_generator(){
        Random cardfinder = new Random();
        int kinds = 2;
        int kind_decider = cardfinder.nextInt(kinds);
        if(kind_decider == 0) {
            return new Card(345, Card.element_type.Fire);
        }else{
            return new Card(123, Card.element_type.Fire);
        }
    }

    /*public Card five_cards_generator() {

        for (int i = 0; i < 5; i++) {
            Random cardfinder = new Random();
            Random damage_creator = new Random();
            Random mons_type = new Random();
            Random elem_type = new Random();
            int kinds = 2;
            int damage_upper = 90;
            int monster_types = 7;
            int element_types = 3;
            int element_decider = elem_type.nextInt(element_types);
            int monster_decider = mons_type.nextInt(monster_types);
            int kind_decider = cardfinder.nextInt(kinds);

            if (kind_decider == 1) {
                if (element_decider == 0) {
                    Spell card = new Spell(damage_creator.nextInt(damage_upper), Card.element_type.Water);
                    String temp = card.getElem().name() + card.getCard_type() + card.getDamage();

                    System.out.println(temp);
                    return card;
                }
                if (element_decider == 1) {
                    Spell card = new Spell(damage_creator.nextInt(damage_upper), Card.element_type.Fire);
                    String temp = card.getElem().name() + card.getCard_type() + card.getDamage();
                    System.out.println(temp);
                    return card;
                }
                if (element_decider == 2) {
                    Spell card = new Spell(damage_creator.nextInt(damage_upper), Card.element_type.Regular);
                    String temp = card.getElem().name() + card.getCard_type() + card.getDamage();
                    System.out.println(temp);
                    return card;
                }
            } else {
                if (monster_decider == 0) {
                    if (element_decider == 0) {
                        Monster card = new Monster(Monster.monster_type.Goblin, damage_creator.nextInt(damage_upper), Card.element_type.Fire);
                        String temp = card.getElem().name() + card.getMons().name() + card.getDamage();
                        System.out.println(temp);
                        return card;
                    }
                    if (element_decider == 1) {
                        Monster card = new Monster(Monster.monster_type.Goblin, damage_creator.nextInt(damage_upper), Card.element_type.Water);
                        String temp = card.getElem().name() + card.getMons().name() + card.getDamage();
                        System.out.println(temp);
                        return card;
                    }
                    if (element_decider == 2) {
                        Monster card = new Monster(Monster.monster_type.Goblin, damage_creator.nextInt(damage_upper), Card.element_type.Regular);
                        String temp = card.getElem().name() + card.getMons().name() + card.getDamage();
                        System.out.println(temp);
                        return card;
                    }
                }
                if (monster_decider == 1) {
                    if (element_decider == 0) {
                        Monster card = new Monster(Monster.monster_type.Elf, damage_creator.nextInt(damage_upper), Card.element_type.Fire);
                        String temp = card.getElem().name() + card.getMons().name() + card.getDamage();
                        System.out.println(temp);
                        return card;
                    }
                    if (element_decider == 1) {
                        Monster card = new Monster(Monster.monster_type.Elf, damage_creator.nextInt(damage_upper), Card.element_type.Water);
                        String temp = card.getElem().name() + card.getMons().name() + card.getDamage();
                        System.out.println(temp);
                        return card;

                    }
                    if (element_decider == 2) {
                        Monster card = new Monster(Monster.monster_type.Elf, damage_creator.nextInt(damage_upper), Card.element_type.Regular);
                        String temp = card.getElem().name() + card.getMons().name() + card.getDamage();
                        System.out.println(temp);
                        return card;
                    }
                }
                if (monster_decider == 2) {
                    if (element_decider == 0) {
                        Monster card = new Monster(Monster.monster_type.Troll, damage_creator.nextInt(damage_upper), Card.element_type.Fire);
                        String temp = card.getElem().name() + card.getMons().name() + card.getDamage();
                        System.out.println(temp);
                        return card;
                    }
                    if (element_decider == 1) {
                        Monster card = new Monster(Monster.monster_type.Troll, damage_creator.nextInt(damage_upper), Card.element_type.Water);
                        String temp = card.getElem().name() + card.getMons().name() + card.getDamage();
                        System.out.println(temp);
                        return card;
                    }
                    if (element_decider == 2) {
                        Monster card = new Monster(Monster.monster_type.Troll, damage_creator.nextInt(damage_upper), Card.element_type.Regular);
                        String temp = card.getElem().name() + card.getMons().name() + card.getDamage();
                        System.out.println(temp);
                        return card;
                    }
                }
                if (monster_decider == 3) {
                    if (element_decider == 0) {
                        Monster card = new Monster(Monster.monster_type.Ork, damage_creator.nextInt(damage_upper), Card.element_type.Fire);
                        String temp = card.getElem().name() + card.getMons().name() + card.getDamage();
                        System.out.println(temp);
                        return card;
                    }
                    if (element_decider == 1) {
                        Monster card = new Monster(Monster.monster_type.Ork, damage_creator.nextInt(damage_upper), Card.element_type.Water);
                        String temp = card.getElem().name() + card.getMons().name() + card.getDamage();
                        System.out.println(temp);
                    }
                    if (element_decider == 2) {
                        Monster card = new Monster(Monster.monster_type.Ork, damage_creator.nextInt(damage_upper), Card.element_type.Regular);
                        String temp = card.getElem().name() + card.getMons().name() + card.getDamage();
                        System.out.println(temp);
                        return card;
                    }
                }
                if (monster_decider == 4) {
                    if (element_decider == 0) {
                        Monster card = new Monster(Monster.monster_type.Kraken, damage_creator.nextInt(damage_upper), Card.element_type.Fire);
                        String temp = card.getElem().name() + card.getMons().name() + card.getDamage();
                        System.out.println(temp);
                        return card;
                    }
                    if (element_decider == 1) {
                        Monster card = new Monster(Monster.monster_type.Kraken, damage_creator.nextInt(damage_upper), Card.element_type.Water);
                        String temp = card.getElem().name() + card.getMons().name() + card.getDamage();
                        System.out.println(temp);
                        return card;
                    }
                    if (element_decider == 2) {
                        Monster card = new Monster(Monster.monster_type.Kraken, damage_creator.nextInt(damage_upper), Card.element_type.Regular);
                        String temp = card.getElem().name() + card.getMons().name() + card.getDamage();
                        System.out.println(temp);
                        return card;
                    }
                }
                if (monster_decider == 5) {
                    if (element_decider == 0) {
                        Monster card = new Monster(Monster.monster_type.Knight, damage_creator.nextInt(damage_upper), Card.element_type.Fire);
                        String temp = card.getElem().name() + card.getMons().name() + card.getDamage();
                        System.out.println(temp);
                        return card;
                    }
                    if (element_decider == 1) {
                        Monster card = new Monster(Monster.monster_type.Knight, damage_creator.nextInt(damage_upper), Card.element_type.Water);
                        String temp = card.getElem().name() + card.getMons().name() + card.getDamage();
                        System.out.println(temp);
                        return card;
                    }
                    if (element_decider == 2) {
                        Monster card = new Monster(Monster.monster_type.Knight, damage_creator.nextInt(damage_upper), Card.element_type.Regular);
                        String temp = card.getElem().name() + card.getMons().name() + card.getDamage();
                        System.out.println(temp);
                        return card;
                    }
                }
                if (monster_decider == 6) {
                    if (element_decider == 0) {
                        Monster card = new Monster(Monster.monster_type.Dragon, damage_creator.nextInt(damage_upper), Card.element_type.Fire);
                        String temp = card.getElem().name() + card.getMons().name() + card.getDamage();
                        System.out.println(temp);
                        return card;
                    }
                    if (element_decider == 1) {
                        Monster card = new Monster(Monster.monster_type.Dragon, damage_creator.nextInt(damage_upper), Card.element_type.Water);
                        String temp = card.getElem().name() + card.getMons().name() + card.getDamage();
                        System.out.println(temp);
                        return card;
                    }
                    if (element_decider == 2) {
                        Monster card = new Monster(Monster.monster_type.Dragon, damage_creator.nextInt(damage_upper), Card.element_type.Regular);
                        String temp = card.getElem().name() + card.getMons().name() + card.getDamage();
                        System.out.println(temp);
                        return card;
                    }
                }
            }
        }
    }*/
}
