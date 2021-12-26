package com.tomuta.swenoop.DB;


//taken from https://mkyong.com/jdbc/how-do-connect-to-postgresql-with-jdbc-driver-java/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCExample {

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