package com.tomuta.swenoop.DB;


//taken from https://mkyong.com/jdbc/how-do-connect-to-postgresql-with-jdbc-driver-java/

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;


public class database {

    public static void main(String[] args) throws SQLException {


        Properties props = new Properties();
        props.setProperty("user","postgres");
        props.setProperty("password","test");
        //props.setProperty("ssl","true");
        String url = "jdbc:postgresql://127.0.0.1:5432/MTCG";
        Connection conn = DriverManager.getConnection(url, props);
        if (conn != null) {
            System.out.println("Connected to the database!");
        } else {
            System.out.println("Failed to make connection!");
        }
        //Creating a Statement object
        Statement stmt = conn.createStatement();
        //Register user

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Username: ");
        String input1 = scanner.nextLine();

        System.out.println("Gib einen Integer-Wert ein: ");
        int input2 = scanner.nextInt();

        System.out.println("Deine Eingaben: " + input1 + ",  " + input2);
        PreparedStatement statement = conn.prepareStatement("INSERT INTO \"Package\" (\"type\", \"damage\") VALUES ( ?, ?)");
        statement.setString(1, input1);
        statement.setInt(2, input2);
        statement.execute();
        //Retrieving the data
        ResultSet rs = stmt.executeQuery("SELECT \"type\", \"damage\"\n" +
                "\tFROM public.\"Package\";");

        System.out.println("Contents of the table");
        while(rs.next()) {
            System.out.print("type: " + rs.getString("type") + ", ");
            System.out.print("damage: " + rs.getInt("damage"));
            System.out.println("");
        }

        





        // auto close connection
        /*try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/MTCG", "postgres", "test")) {

            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }
}