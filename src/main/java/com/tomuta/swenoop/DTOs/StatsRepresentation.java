package com.tomuta.swenoop.DTOs;

public class StatsRepresentation {

    public String Username;
    public int Elo;

    public StatsRepresentation(){}

    public StatsRepresentation(String username, int elo) {
        Username = username;
        this.Elo = elo;
    }
}
