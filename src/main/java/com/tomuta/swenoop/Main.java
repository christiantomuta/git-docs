package com.tomuta.swenoop;
import java.io.IOException;

import java.lang.String;
import com.tomuta.swenoop.server.MCTGApp;
import com.tomuta.swenoop.server.Server;

public class Main {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {

        Server server = new Server(10001,MCTGApp.GetInstance());
        try{
            server.start();
        }catch (IOException e)
        {
            System.out.println("Encountered unexpected IOException");
            System.out.println("ABORT!");
        }
    }
}