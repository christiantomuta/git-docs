package com.tomuta.swenoop.cards;
import java.util.Random;
import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode

public class Card {

    public Card()
    {}

    private UUID Id;
    private String description;
    private UUID Owner;
    private boolean inDeck;

    public Card(UUID id, UUID owner, ECard_type card_type, int damage, element_type elem) {
        Id = UUID.randomUUID();
        this.description = ""; //Je nach card type -> switch
        Owner = owner;
        inDeck = false;
        this.card_type = card_type;
        this.damage = damage;
        this.elem = elem;
    }

    public Card(UUID id, String description, UUID owner, boolean inDeck, ECard_type card_type, int damage, element_type elem) {
        Id = id;
        this.description = description;
        Owner = owner;
        this.inDeck = inDeck;
        this.card_type = card_type;
        this.damage = damage;
        this.elem = elem;
    }

    public enum ECard_type{
        Goblin,
        Troll,
        Ork,
        Kraken,
        Knight,
        Dragon,
        Elf,
        Wizard,
        Spell
    }

    //Random rand = new Random();
    //int upperbound = 50;


    private ECard_type card_type;
    public int damage;


    public enum element_type{
        Fire,
        Water,
        Regular
    }

    //private Monster.monster_type card_type;
    private element_type elem;

    public ECard_type getCard_type() {
        return card_type;
    }



    public void printCard(element_type elem){
        System.out.println(elem);
    }

    public int getDamage(){
        return damage;
    }
}
