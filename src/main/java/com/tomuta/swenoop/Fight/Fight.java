package com.tomuta.swenoop.Fight;

import com.tomuta.swenoop.cards.Card;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Fight {
    public static String Specialitychecker(Card card1, Card card2){

        Card.ECard_type type1 = card1.getCard_type();
        Card.ECard_type type2 = card2.getCard_type();


        if (type1.equals(Card.ECard_type.Dragon) && type2.equals("Dragon")) {
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

        return null;
    }

    public static void damage_calculator(Card card1, Card card2){


    }
}
