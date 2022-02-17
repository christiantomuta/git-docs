package com.tomuta.swenoop.Fight;

import com.tomuta.swenoop.cards.Card;
import com.tomuta.swenoop.cards.Monster;
import com.tomuta.swenoop.cards.Spell;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Fight {
    public static String Specialitychecker(Monster card1, Monster card2){

        String type1 = card1.getMons().name();
        String type2 = card2.getMons().name();
        String type3 = "weder noch";


        if (type1.equals("Goblin") && type2.equals("Dragon")) {
            return "PlayerA";
        }
        else if (type2.equals("Goblin") && type1.equals("Dragon")){
            return "PlayerB";
        }
        else if (type1.equals("Wizard") && type1.equals("Ork")){
            return "PlayerA";
        }
        else if (type2.equals("Wizard") && type1.equals("Ork")){
            return "PlayerB";
        }
        else if (type1.equals("Kraken")){
            return "PlayerA";
        }
        else if (type2.equals("Kraken")){
            return "PlayerB";
        }

        return type3;
    }

    public static void damage_calculator(Monster card1, Monster card2){


    }
}
