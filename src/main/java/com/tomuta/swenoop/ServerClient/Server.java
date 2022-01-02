package com.tomuta.swenoop.ServerClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
public class Server implements Runnable {
    private Socket client;
    public Server(Socket client) {
        this.client = client;
    }
    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                client.getInputStream()));
             PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {
            out.println("Hallo, ich bin der EchoServer");
            String input;
            while ((input = in.readLine()) != null) {
                out.println("[" + LocalDateTime.now() + "] " + input);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        int port = 3141;
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("EchoServer auf " + port + " gestartet ...");
            while (true) {
                Socket client = server.accept();
                new Thread(new Server(client)).start();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

