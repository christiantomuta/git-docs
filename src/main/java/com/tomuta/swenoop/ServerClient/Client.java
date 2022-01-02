package com.tomuta.swenoop.ServerClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 3141;
        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(
                     socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner sc = new Scanner(System.in)) {
            System.out.println(in.readLine());
            while (true) {
                System.out.print("> ");
                String line = sc.nextLine();
                if (line.length() == 0)
                    break;
                out.println(line);
                System.out.println(in.readLine());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

