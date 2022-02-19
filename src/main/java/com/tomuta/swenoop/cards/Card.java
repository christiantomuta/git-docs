package com.tomuta.swenoop.cards;
import java.util.Random;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Card {

    Random rand = new Random();
    int upperbound = 50;
    private Monster.monster_type mons;
    public int damage = rand.nextInt(upperbound);


    public enum element_type{
        Fire,
        Water,
        Regular
    }

    //private Monster.monster_type card_type;
    private element_type elem;
    private static String card_type;
    public static String getCard_type() {
        return card_type;
    }

    /*public Card(Monster.monster_type card_type, int damage, element_type elem){
        this.card_type = card_type;
        this.damage = damage;
        this.elem = elem;

    };
    public Card(String spell, int damage, element_type elem){

        this.damage = damage;
        this.elem = elem;
        this.card_type = spell;

    };*/
    public Card(String card_type, int damage, element_type elem){

        this.damage = damage;
        this.elem = elem;
        if(Monster. getMons_type() != null) {
            Card.card_type = Monster.getCard_type();
        }else{
            Card.card_type = Spell.getCard_type();}
    }


    public void printCard(element_type elem){
        System.out.println(elem);
    }

    public int getDamage(){
        return damage;
    }
}
