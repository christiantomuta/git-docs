package com.tomuta.swenoop.Fight;

import com.tomuta.swenoop.cards.Card;
import com.tomuta.swenoop.cards.Monster;
import com.tomuta.swenoop.cards.Spell;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MonstervsMonster {
    public static String Monsterfight(Monster card1, Monster card2){
        String type1 = card1.getMons().name();
        String type2 = card2.getMons().name();
        String type3 = "weder noch";
        if (type1.equals("Goblin") && type2.equals("Dragon")) {
            return type1;
        }
        else if (type2.equals("Goblin") && type1.equals("Dragon")){
            return type2;
        }
        else if (type1.equals("Wizard") && type1.equals("Ork")){
            return type1;
        }
        else if (type2.equals("Wizard") && type1.equals("Ork")){
            return type2;
        }
        else if (type1.equals("Kraken")){
            return type1;
        }
        else if (type2.equals("Kraken")){
            return type2;
        }
        return type3;
    }
}
