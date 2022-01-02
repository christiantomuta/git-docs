package com.tomuta.swenoop.ServerClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class TestServer1 {
    public static void main(String[] args) {
        int port = 50000;
        try (ServerSocket server = new ServerSocket(port)) {
            Socket client = server.accept();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(
                    client.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                    if (line.length() == 0)
                        break;
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
