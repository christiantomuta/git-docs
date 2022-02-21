package com.tomuta.swenoop.server;


import com.tomuta.swenoop.http.ContentType;
import com.tomuta.swenoop.http.HttpStatus;
import com.tomuta.swenoop.server.Controllers.PackageController;
import com.tomuta.swenoop.server.Controllers.UserController;

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

    @Override
    public Response handleRequest(Request request) {
        //Routing
        switch(request.getMethod())
        {
            case PUT -> {
                switch(request.getPathname()){
                    case "/users": {
                        System.out.println("Received update user request");
                        break;
                    }
                }
            }
            case POST -> {
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
                        System.out.println("Received buy package request");
                        return p_controller.buyPackage(request.getAuth().split("Basic ")[1].split("-mtcgToken")[0]);
                    }

                }


            }
            case GET -> {
                switch(request.getPathname()){
                    case "/users": {
                        System.out.println("Received get user request");
                    }
                    case "/card": {
                        System.out.println("Received get card ");
                    }
                }

            }
            case DELETE -> {

            }
        }
        return new Response(HttpStatus.NOT_FOUND, ContentType.JSON, "");
    }
}
