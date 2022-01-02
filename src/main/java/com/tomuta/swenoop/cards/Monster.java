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
        Elf
    }
    private Monster.monster_type mons;



    public Monster(monster_type mons, int damage, element_type elem) {
        super(damage, elem);
        this.mons = mons;

    }



}
