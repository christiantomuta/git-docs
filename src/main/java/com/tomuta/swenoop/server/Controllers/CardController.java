package com.tomuta.swenoop.server.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomuta.swenoop.DB.CardRepository;
import com.tomuta.swenoop.DB.UserRepository;
import com.tomuta.swenoop.Main;
import com.tomuta.swenoop.http.ContentType;
import com.tomuta.swenoop.http.HttpStatus;
import com.tomuta.swenoop.server.Response;

public class CardController {

    private UserRepository u_repo = UserRepository.GetInstance();
    private CardRepository c_repo = CardRepository.GetInstance();

    private ObjectMapper mapper = new ObjectMapper();

    private static CardController instance;

    private CardController(){}

    public static CardController getInstance(){
        if(instance != null) return instance;
        instance = new CardController();
        return instance;
    }

    public Response getCardsOfUser(String username)
    {
        try{
            var user = u_repo.GetByUsername(username);
            if(user != null)
            {
                var cards = c_repo.getCardsByUser(user.getID());
                System.out.println(Main.ANSI_GREEN + "The cards for username: " + username + " returned successfully" + Main.ANSI_RESET);
                return new Response(HttpStatus.OK, ContentType.JSON,mapper.writeValueAsString(cards));
            }
            System.out.println(Main.ANSI_RED + "User: " + username + " not found" + Main.ANSI_RESET);
            return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, "");
        }catch(Exception e)
        {
            System.out.println(Main.ANSI_RED + "Exception: Internal Server Error!" + Main.ANSI_RESET);

            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, "");
        }
    }
}
