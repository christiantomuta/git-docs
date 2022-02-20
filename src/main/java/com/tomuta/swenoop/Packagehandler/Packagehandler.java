package com.tomuta.swenoop.Packagehandler;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

import com.tomuta.swenoop.Package.Package;
import com.tomuta.swenoop.cards.Card;
import com.tomuta.swenoop.DB.getConnection;

public class Packagehandler {
    public void handle_package_to_user() {
        System.out.println("Here are five randomly generated cards.");
        getConnection conn = new getConnection();
        try {
            Connection connection = conn.connect_to_database();
            Statement stmt = connection.createStatement();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO \"Package\" (\"type\", \"damage\") VALUES ( ?, ?)");
            Package pack = new Package();
            for (int i = 0; i < 5; i++) {
                Card card = pack.one_card_generator();
                String input1 = card.getElem() + card.getCard_type();
                int input2 = card.getDamage();
                statement.setString(1, input1);
                statement.setInt(2, input2);
                statement.execute();
            }
            //Retrieving the data
            ResultSet rs = stmt.executeQuery("SELECT \"type\", \"damage\", \"Cardid\"\n" +
                    "\tFROM public.\"Package\";");
            while (rs.next()) {
                System.out.print("Cardid: " + rs.getInt("Cardid") + ", ");
                System.out.print("type: " + rs.getString("type") + ", ");
                System.out.print("damage: " + rs.getInt("damage"));
                System.out.println("");
            }
            System.out.println("Please type in the Cardid of the first card you would like to have");
            PreparedStatement preStatement = connection.prepareStatement("SELECT * FROM \"Package\" WHERE \"Cardid\" = ?");
            Statement state = connection.createStatement();
            //for(int i = 0; i < 4; i++) {
                Scanner scan = new Scanner(System.in);
                int choice = scan.nextInt();
                String chosen = String.valueOf(choice);
                preStatement.setInt(1, choice);
                preStatement.execute();

                String query = String.format("SELECT \"type\", \"damage\", \"Cardid\"\n" +
                        "\tFROM public.\"Package\" WHERE \"Cardid\" = %d", choice);
            //}
                ResultSet set = state.executeQuery(query);
                while (set.next()) {
                    System.out.print("Cardid: " + set.getInt("Cardid") + ", ");
                    System.out.print("type: " + set.getString("type") + ", ");
                    System.out.print("damage: " + set.getInt("damage"));
                    System.out.println("");

                String card_type = set.getString("type");
                System.out.println(card_type);
                int chosen_card_damage = set.getInt("damage");
                int id = set.getInt("Cardid");
                Statement give_to_user = connection.createStatement();
                PreparedStatement insert_state = connection.prepareStatement("INSERT INTO \"PlayerA\" (\"Cardtype\", \"Damage\", \"Cardid\") VALUES ( ?, ?, ?)");
                insert_state.setString(1, card_type);
                insert_state.setInt(2, chosen_card_damage);
                insert_state.setInt(3, id);
                insert_state.execute();

                ResultSet set1 = give_to_user.executeQuery("SELECT \"Cardtype\", \"Damage\", \"Cardid\"\n" +
                            "\tFROM public.\"PlayerA\";");
                    while (set1.next()) {
                        System.out.print("Cardid: " + set1.getInt("Cardid") + ", ");
                        System.out.print("type: " + set1.getString("Cardtype") + ", ");
                        System.out.print("damage: " + set1.getInt("Damage"));
                        System.out.println("");
                    }

            }
            /*ResultSet set = state.executeQuery("SELECT \"type\", \"damage\", \"Cardid\"\n" +
                    "\tFROM public.\"Package\" WHERE \"Cardid\" = ");*/
            /*while (set.next()) {
                System.out.print("Cardid: " + set.getInt("Cardid") + ", ");
                System.out.print("type: " + set.getString("type") + ", ");
                System.out.print("damage: " + set.getInt("damage"));
                System.out.println("");
            }*/





        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
