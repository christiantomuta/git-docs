package com.tomuta.swenoop.server.Controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomuta.swenoop.DB.CardRepository;
import com.tomuta.swenoop.DB.UserRepository;
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
        var user = u_repo.GetByUsername(username);
        if(user.getCoins() >= 5)
        {
            var card_pack = Packagehandler.GeneratePackage(user.getID());
            var res = u_repo.UpdateCoins(user, -5);
            if(!res) return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, "");
            for (var item: card_pack) {
                var result = c_repo.Add(item);
                if(result == null)
                {
                    u_repo.UpdateCoins(user, 5);
                    return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, "");
                }
            }
            try {
                return new Response(HttpStatus.OK, ContentType.JSON, mapper.writeValueAsString(card_pack));
            }
            catch (JsonProcessingException e)
            {
                return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, "");
            }

        }else
        {
            return new Response(HttpStatus.PAYMENT_REQUIRED, ContentType.JSON, "");
        }
    }



}
