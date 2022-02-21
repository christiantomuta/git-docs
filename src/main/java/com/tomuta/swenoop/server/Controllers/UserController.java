package com.tomuta.swenoop.server.Controllers;

import com.tomuta.swenoop.DB.UserRepository;
import com.tomuta.swenoop.DTOs.RegisterAndLoginDTO;
import com.tomuta.swenoop.http.ContentType;
import com.tomuta.swenoop.http.HttpStatus;
import com.tomuta.swenoop.server.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomuta.swenoop.user.User;

public class UserController {

    private UserRepository u_repo = UserRepository.GetInstance();
    private ObjectMapper mapper = new ObjectMapper();

    private static UserController instance;

    private UserController(){}

    public static UserController getInstance(){
        if(instance != null) return instance;
        instance = new UserController();
        return instance;
    }

    public Response register(String requestBody)
    {
        try {
            var requestContent = mapper.readValue(requestBody, RegisterAndLoginDTO.class);
            var res = u_repo.Add(new User(requestContent.Username, requestContent.Password));
            if(res != null)
            {
                return new Response(HttpStatus.CREATED, ContentType.JSON, "");
            }
            return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, "");
        }catch(Exception e)
        {
            return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, "");
        }
    }

    public Response login(String requestBody)
    {
        try {
            var requestContent = mapper.readValue(requestBody, RegisterAndLoginDTO.class);
            var res = u_repo.ValidLogin(requestContent);
            if(res)
            {
                return new Response(HttpStatus.OK, ContentType.JSON, "Basic " + requestContent.Username +"-mtcgToken");
            }
            return new Response(HttpStatus.FORBIDDEN, ContentType.JSON, "");
        }catch(Exception e)
        {
            return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, "");
        }
    }
}
