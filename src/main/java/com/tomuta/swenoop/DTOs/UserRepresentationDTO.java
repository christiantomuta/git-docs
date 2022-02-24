package com.tomuta.swenoop.DTOs;


import com.fasterxml.jackson.annotation.JsonCreator;

//return object without ID and password because these data shouldn't be displayed
public class UserRepresentationDTO {
    public String username;
    public int coins;
    public String description;
    public int elo;

    public UserRepresentationDTO(){}

    public UserRepresentationDTO(String username, int coins, String description, int elo) {
        this.username = username;
        this.coins = coins;
        this.description = description;
        this.elo = elo;
    }
}
