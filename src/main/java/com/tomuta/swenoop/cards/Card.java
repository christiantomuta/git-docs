package com.tomuta.swenoop.cards;
import java.util.Random;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Card {

    Random rand = new Random();
    int upperbound = 50;
    public int damage = rand.nextInt(upperbound);


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

    public int getDamage(){
        return damage;
    }
}
