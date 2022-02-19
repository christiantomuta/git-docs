package com.tomuta.swenoop.cards;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Monster extends Card{



    public enum monster_type{
        Goblin,
        Troll,
        Ork,
        Kraken,
        Knight,
        Dragon,
        Elf,
        Spell
    }
    private static Monster.monster_type mons;
    private static String card_type = "";
    public static void setMonster_type(Monster.monster_type mons){
        Monster.mons = mons;
    }

    public static String getMons_type() {
        return mons.toString();
    }
    public static String getCard_type() {
        return mons.toString();
    }





    public Monster(String card_type, int damage, element_type elem) {
        super(card_type, damage, elem);
        Monster.card_type = card_type;

    }



}
