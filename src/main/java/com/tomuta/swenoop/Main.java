package com.tomuta.swenoop;
import java.io.IOException;
import java.util.Random;

import com.tomuta.swenoop.Packagehandler.Packagehandler;
import com.tomuta.swenoop.cards.Card;

import java.lang.String;
import com.tomuta.swenoop.Package.Package;
import com.tomuta.swenoop.server.MCTGApp;
import com.tomuta.swenoop.server.Server;

public class Main {
    public static void main(String[] args) {

        Server server = new Server(10001,MCTGApp.GetInstance());
        try{
            server.start();
        }catch (IOException e)
        {
            System.out.println("Encountered unexpected IOException");
            System.out.println("ABORT!");
        }


        /*Server s = new Server();

        int uppermost_damage = 90;
        Random rand = new Random();
        //Spell card3 = new Spell(rand.nextInt(uppermost_damage), Card.element_type.Fire);
        //Monster card4 = new Monster(Monster.monster_type.Goblin, rand.nextInt(uppermost_damage), Card.element_type.Water);
        //String ja = card4.getElem().name() + card4.getMons().name() + card4.getDamage();
        //System.out.println(ja);
        //String jo = card3.getElem().name() + card3.getCard_type();
        //System.out.println(jo);
        //Monster card5 = new Monster(Monster.monster_type.Dragon, 111, Card.element_type.Fire);
        //String winner =  Fight.Specialitychecker(card4, card5);
        //System.out.println(winner);
        Package pack = new Package();
        Card card10 = pack.one_card_generator();
        String test =  card10.getElem() + card10.getCard_type() + card10.getDamage();
        //String test2 = card10.getElem().name() + card10.getCard_type() + card10.getDamage();
        //System.out.println(card10.getMons());
        System.out.println(test);
        //System.out.println(test2);
        //pack.five_cards_generator();
        /*getConnection conn = new getConnection();
        try{
            Connection connection = conn.connect_to_database();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        /*
        Packagehandler packk = new Packagehandler();
        packk.handle_package_to_user();
        */



    }
}