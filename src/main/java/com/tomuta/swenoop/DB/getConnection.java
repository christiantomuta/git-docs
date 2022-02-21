package com.tomuta.swenoop.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class getConnection {
    public static Connection connect_to_database(){
        String name,pass,url;
        Connection con = null;
        try {
            url="jdbc:postgresql://127.0.0.1:5432/MTCG";
            name="postgres";
            pass="test";
            con = DriverManager.getConnection(url,name,pass);

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    return con;
    }

}
