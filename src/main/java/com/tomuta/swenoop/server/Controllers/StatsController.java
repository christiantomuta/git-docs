package com.tomuta.swenoop.server.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomuta.swenoop.DB.UserRepository;
import com.tomuta.swenoop.DTOs.StatsRepresentation;
import com.tomuta.swenoop.DTOs.UserRepresentationDTO;
import com.tomuta.swenoop.Main;
import com.tomuta.swenoop.http.ContentType;
import com.tomuta.swenoop.http.HttpStatus;
import com.tomuta.swenoop.server.Response;

public class StatsController {

    private UserRepository u_repo = UserRepository.GetInstance();
    private ObjectMapper mapper = new ObjectMapper();

    private static StatsController instance;

    private StatsController(){}

    public static StatsController getInstance(){
        if(instance != null) return instance;
        instance = new StatsController();
        return instance;
    }


    public Response getUserStats(String username)
    {
        try {
            var user = u_repo.GetByUsername(username);
            if(user != null)
            {
                return new Response(
                        HttpStatus.OK,
                        ContentType.JSON,
                        mapper.writeValueAsString(new StatsRepresentation(user.getUsername().replaceAll("\\s+",""), user.getElo()))
                );
            }
            System.out.println(Main.ANSI_RED + "Statistics for username: " + username + " cannot be displayed" + Main.ANSI_RESET);
            return new Response(HttpStatus.NOT_FOUND, ContentType.JSON, "");
        }catch(Exception e)
        {
            System.out.println(Main.ANSI_RED + "Exception " + e.getMessage() + " encountered! Statistics for username: " + username + " cannot be displayed, internal server error" + Main.ANSI_RESET);
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, "");
        }
    }

    public Response getScoreBoard()
    {
        try {
            var scoreboard = u_repo.GetScoreBoard();
            if(scoreboard == null) return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, "");
            return new Response(
                        HttpStatus.OK,
                        ContentType.JSON,
                        mapper.writeValueAsString(scoreboard)
            );
        }catch(Exception e)
        {
            System.out.println(Main.ANSI_RED + "Exception " + e.getMessage() + " encountered! Scoreboard cannot be shown, internal server error!" + Main.ANSI_RESET);
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, "");
        }
    }



}
