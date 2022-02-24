package com.tomuta.swenoop.server;


import com.tomuta.swenoop.Main;
import com.tomuta.swenoop.http.ContentType;
import com.tomuta.swenoop.http.HttpStatus;
import com.tomuta.swenoop.server.Controllers.*;

//Facade pattern for controllers
public class MCTGApp implements ServerApp {


    //Singleton Pattern -> es existiert nur eine Instance der Klasse
    private static MCTGApp instance;
    public static MCTGApp GetInstance()
    {
        if(instance != null) return instance;

        instance = new MCTGApp();
        return instance;
    }

    private MCTGApp(){}

    UserController u_controller = UserController.getInstance();
    PackageController p_controller = PackageController.getInstance();
    CardController c_controller = CardController.getInstance();
    DeckController d_controller = DeckController.getInstance();
    StatsController s_controller = StatsController.getInstance();
    FightController f_controller = FightController.getInstance();

    @Override
    public Response handleRequest(Request request) {
        //Routing

        boolean hasAuthentification = request.getAuth() != null; //check if authentication was sent

        switch(request.getMethod()) //get method of request
        {
            case PUT -> {
                switch(request.getPathname().split("/")[1]){
                    case "users": {
                        if(!hasAuthentification){
                            System.out.println(Main.ANSI_RED + "POST /users was called without authentication" + Main.ANSI_RESET);
                            return new Response(HttpStatus.FORBIDDEN, ContentType.JSON, "");//check if authentication exists
                        }

                        var token = request.getAuth().split("Basic ")[1].split("-mtcgToken")[0];
                        var givenUser = request.getPathname().split("/")[2];

                        if(!token.equals(givenUser))
                        {
                            System.out.println(Main.ANSI_RED + "PUT /users was called but token didn't match the route " + Main.ANSI_RESET);
                            return new Response(HttpStatus.FORBIDDEN, ContentType.JSON, "");
                        }

                        System.out.println("Received update user request");
                        return u_controller.updateUserDescription(request.getAuth().split("Basic ")[1].split("-mtcgToken")[0],request.getBody());
                    }
                    case "deck": {
                        if(!hasAuthentification){
                            System.out.println(Main.ANSI_RED + "POST /deck was called without authentication" + Main.ANSI_RESET);
                            return new Response(HttpStatus.FORBIDDEN, ContentType.JSON, "");//check if authentication exists
                        }
                        System.out.println("Received update deck ");
                        return d_controller.setUserDeck(request.getAuth().split("Basic ")[1].split("-mtcgToken")[0], request.getBody());
                    }
                }
            }
            case POST -> { //create user
                switch(request.getPathname()){
                    case "/users": {
                        System.out.println("Received register request");
                        return u_controller.register(request.getBody());
                    }

                    case "/sessions": {
                        System.out.println("Received login request");
                        return u_controller.login(request.getBody());
                    }

                    case "/transactions/packages": {
                        if(!hasAuthentification) return new Response(HttpStatus.FORBIDDEN, ContentType.JSON, "");
                        System.out.println("Received buy package request");
                        return p_controller.buyPackage(request.getAuth().split("Basic ")[1].split("-mtcgToken")[0]); //split username from token
                    }

                    case "/battles": {
                        if(!hasAuthentification) return new Response(HttpStatus.FORBIDDEN, ContentType.JSON, "");
                        System.out.println("Received battles request");
                        return f_controller.queueForBattle(request.getAuth().split("Basic ")[1].split("-mtcgToken")[0]);
                    }
                }
            }
            case GET -> {
                switch(request.getPathname()){
                    case "/users": {
                        if(!hasAuthentification) return new Response(HttpStatus.FORBIDDEN, ContentType.JSON, "");
                        System.out.println("Received get user request");
                        return u_controller.getUserRepresentation(request.getAuth().split("Basic ")[1].split("-mtcgToken")[0]);
                    }
                    case "/cards": {
                        if(!hasAuthentification) return new Response(HttpStatus.FORBIDDEN, ContentType.JSON, "");
                        System.out.println("Received get cards");
                        return c_controller.getCardsOfUser(request.getAuth().split("Basic ")[1].split("-mtcgToken")[0]);
                    }
                    case "/deck": {
                        if(!hasAuthentification) return new Response(HttpStatus.FORBIDDEN, ContentType.JSON, "");
                        System.out.println("Received get deck ");
                        return d_controller.getUserDeck(request.getAuth().split("Basic ")[1].split("-mtcgToken")[0]);
                    }
                    case "/stats": {
                        if(!hasAuthentification) return new Response(HttpStatus.FORBIDDEN, ContentType.JSON, "");
                        System.out.println("Received get stats ");
                        return s_controller.getUserStats(request.getAuth().split("Basic ")[1].split("-mtcgToken")[0]);
                    }
                    case "/score": {
                        if(!hasAuthentification) return new Response(HttpStatus.FORBIDDEN, ContentType.JSON, "");
                        System.out.println("Received get score ");
                        return s_controller.getScoreBoard();
                    }
                }

            }
            case DELETE -> {

            }
        }
        return new Response(HttpStatus.NOT_FOUND, ContentType.JSON, "");
    }
}
