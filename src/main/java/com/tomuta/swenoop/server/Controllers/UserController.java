package com.tomuta.swenoop.server.Controllers;

import com.tomuta.swenoop.DB.UserRepository;
import com.tomuta.swenoop.DTOs.RegisterAndLoginDTO;
import com.tomuta.swenoop.DTOs.UserRepresentationDTO;
import com.tomuta.swenoop.DTOs.UserUpdateDTO;
import com.tomuta.swenoop.Main;
import com.tomuta.swenoop.http.ContentType;
import com.tomuta.swenoop.http.HttpStatus;
import com.tomuta.swenoop.server.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomuta.swenoop.user.User;

import java.sql.SQLException;

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
        try { //exception handling: e.g. in case the mapper sends faulty data
            var requestContent = mapper.readValue(requestBody, RegisterAndLoginDTO.class); //making an object so we won't have to split strings
            var res = u_repo.Add(new User(requestContent.Username, requestContent.Password));
            if(res != null)
            {
                System.out.println(Main.ANSI_GREEN + "Created user with username: " + res.getUsername() + " created successfully" + Main.ANSI_RESET);
                return new Response(HttpStatus.CREATED, ContentType.JSON, "");
            }
            System.out.println(Main.ANSI_RED + "User registration failed, bad request" + Main.ANSI_RESET);
            return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, "");
        }catch(Exception e)
        {
            System.out.println(Main.ANSI_RED + "User registration failed, bad request!" + Main.ANSI_RESET);
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
                System.out.println(Main.ANSI_GREEN + "User with username: " + requestContent.Username + " logged in" + Main.ANSI_RESET);
                return new Response(HttpStatus.OK, ContentType.JSON, "Basic " + requestContent.Username +"-mtcgToken");
            }
            System.out.println(Main.ANSI_RED + "User with username: " + requestContent.Username + " failed to login" + Main.ANSI_RESET);
            return new Response(HttpStatus.FORBIDDEN, ContentType.JSON, "");
        }catch(Exception e)
        {
            System.out.println(Main.ANSI_RED + "Exception " + e.getMessage() + "encountered in UserController.login()" + Main.ANSI_RESET);
            return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, "");
        }
    }

    public Response getUserRepresentation(String username)
    {
        try {
            var res = u_repo.GetByUsername(username);
            if(res != null)
            {
                return new Response(HttpStatus.OK,
                        ContentType.JSON,
                        mapper.writeValueAsString( //return as JSON string (JSON = JavaScript object notation)
                                new UserRepresentationDTO(res.getUsername().replaceAll("\\s+",""), res.getCoins(), res.getDescription(),res.getElo())
                        ));
            }
            return new Response(HttpStatus.NOT_FOUND, ContentType.JSON, "");
        }catch(Exception e)
        {
            System.out.println(Main.ANSI_RED + "Exception " + e.getMessage() + "encountered in getUserRepresentation()" + Main.ANSI_RESET);
            return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, "");
        }
    }

    public Response updateUserDescription(String username, String requestBody)
    {
        try {
            var requestContent = mapper.readValue(requestBody, UserUpdateDTO.class);

            var user = u_repo.GetByUsername(username);

            if(user != null && !requestContent.Description.equals("") && requestContent.Description != null)
            {
                if(u_repo.UpdateUserBio(user.getID(), requestContent.Description))
                {
                    System.out.println(Main.ANSI_GREEN + "User description for username: " + username + " updated successfully" + Main.ANSI_RESET);
                    return new Response(HttpStatus.OK, ContentType.JSON, "");
                }
                System.out.println(Main.ANSI_RED + "Failed to update user description" + Main.ANSI_RESET);
                return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, "");
            }
            System.out.println(Main.ANSI_RED + "Failed to update user description" + Main.ANSI_RESET);
            return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, "");
        }catch(Exception e)
        {
            System.out.println(Main.ANSI_RED + "Exception encountered in updateUserDescription" + Main.ANSI_RESET);
            return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, "");
        }
    }

}
