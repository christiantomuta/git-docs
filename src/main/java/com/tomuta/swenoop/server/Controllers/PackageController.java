package com.tomuta.swenoop.server.Controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomuta.swenoop.DB.CardRepository;
import com.tomuta.swenoop.DB.UserRepository;
import com.tomuta.swenoop.Main;
import com.tomuta.swenoop.Packagehandler.Packagehandler;
import com.tomuta.swenoop.http.ContentType;
import com.tomuta.swenoop.http.HttpStatus;
import com.tomuta.swenoop.server.Response;

import java.util.UUID;

public class PackageController {
    private UserRepository u_repo = UserRepository.GetInstance();
    private CardRepository c_repo = CardRepository.GetInstance();

    private ObjectMapper mapper = new ObjectMapper();

    private static PackageController instance;

    private PackageController(){}

    public static PackageController getInstance(){
        if(instance != null) return instance;
        instance = new PackageController();
        return instance;
    }

    public Response buyPackage(String username)
    {
        var user = u_repo.GetByUsername(username); //getting the user from the received token
        if(user.getCoins() >= 5)
        {
            var card_pack = Packagehandler.GeneratePackage(user.getID());
            var res = u_repo.UpdateCoins(user.getUsername(), -5); //constraint in DB coins >= 0

            if(!res)
            {
                System.out.println(Main.ANSI_RED + "User with username: " + username + " has insufficient funds" + Main.ANSI_RESET);
                return new Response(HttpStatus.PAYMENT_REQUIRED, ContentType.JSON, "");
            }

            for (var item: card_pack) {
                var result = c_repo.Add(item);
                if(result == null) //in case cards can't be added: give coins back to user
                {
                    u_repo.UpdateCoins(user.getUsername(), 5);
                    return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, "");
                }
            }
            try {
                System.out.println(Main.ANSI_GREEN + "Created package for username: " + username + " created successfully" + Main.ANSI_RESET);
                return new Response(HttpStatus.OK, ContentType.JSON, mapper.writeValueAsString(card_pack));
            }
            catch (JsonProcessingException e)
            {
                System.out.println(Main.ANSI_RED + "Encountered exception in PackageController.buyPackage()" + Main.ANSI_RESET);
                return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, "");
            }

        }else
        {
            System.out.println(Main.ANSI_RED + "User with username: " + username + "has insufficient funds" + Main.ANSI_RESET);
            return new Response(HttpStatus.PAYMENT_REQUIRED, ContentType.JSON, "");
        }
    }



}
