package com.tomuta.swenoop.DTOs;

public class ScoreBoardEntryDTO {
    public String Username;
    public int Elo;

    public ScoreBoardEntryDTO(){}

    public ScoreBoardEntryDTO(String username, int elo) {
        Username = username;
        Elo = elo;
    }
}
