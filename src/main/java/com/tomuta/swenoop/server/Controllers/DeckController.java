package com.tomuta.swenoop.server.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomuta.swenoop.DB.CardRepository;
import com.tomuta.swenoop.DB.UserRepository;
import com.tomuta.swenoop.DTOs.ConfigureDeckDTO;
import com.tomuta.swenoop.Main;
import com.tomuta.swenoop.http.ContentType;
import com.tomuta.swenoop.http.HttpStatus;
import com.tomuta.swenoop.server.Response;

public class DeckController {

    private UserRepository u_repo = UserRepository.GetInstance();
    private CardRepository c_repo = CardRepository.GetInstance();

    private ObjectMapper mapper = new ObjectMapper();

    private static DeckController instance;

    private DeckController() {
    }

    public static DeckController getInstance() {
        if (instance != null) return instance;
        instance = new DeckController();
        return instance;
    }

    public Response getUserDeck(String username) {
        try {
            var user = u_repo.GetByUsername(username);
            if (user != null) {
                var cards = c_repo.getDeckByUser(user.getID());
                System.out.println(Main.ANSI_GREEN + "Deck for username: " + username + " returned successfully" + Main.ANSI_RESET);
                return new Response(HttpStatus.OK, ContentType.JSON, mapper.writeValueAsString(cards));
            }
            System.out.println(Main.ANSI_RED + "Deck for " + username + " not found, bad request!" + Main.ANSI_RESET);
            return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, "");
        } catch (Exception e) {
            System.out.println(Main.ANSI_RED + "Internal Server Error" + Main.ANSI_RESET);
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, "");
        }
    }

    public Response setUserDeck(String username, String body) {
        try {
            var configureDeckDTO = mapper.readValue(body, ConfigureDeckDTO.class);

            if (configureDeckDTO.cardIds.size() != 4) {
                System.out.println(Main.ANSI_RED + "Deck for username: " + username + " does not have 4 cards" + Main.ANSI_RESET);
                return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, "");
            }

            var user = u_repo.GetByUsername(username);
            if (user != null) {
                var res = c_repo.setUserDeck(user.getID(), configureDeckDTO.cardIds);

                if (res)
                {
                    System.out.println(Main.ANSI_GREEN + "Deck for username: " + username + " set successfully" + Main.ANSI_RESET);
                    return new Response(HttpStatus.OK, ContentType.JSON, "");
                }
                System.out.println(Main.ANSI_RED + "Username: " + username + " does not own the given cards!" + Main.ANSI_RESET);
                return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, "");
            }
            return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, "");
        } catch (Exception e) {
            System.out.println(Main.ANSI_RED + "Internal Server error!" + Main.ANSI_RESET);
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, "");
        }
    }

}
