package com.tomuta.swenoop.ServerClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
public class TestServer2 {
    public static void main(String[] args) {
        int port = 50000;
        try (ServerSocket server = new ServerSocket(port)) {
            Socket client = server.accept();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(
                    client.getInputStream()))) {
                String line = in.readLine();
                if (line == null) {
                    return;
                }
                int x1 = line.indexOf('=');
                int x2 = line.indexOf(' ', x1);
                String query = line.substring(x1 + 1, x2);
                System.out.println(query);
                String decodedQuery = URLDecoder.decode(query, "UTF-8");
                System.out.println(decodedQuery);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}

