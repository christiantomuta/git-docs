import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static java.lang.Thread.sleep;

public class Main {
    public static final String BASEURL = "http://localhost:10001";
    public static void main(String[] args) {


        ObjectMapper mapper = new ObjectMapper();
        HttpClient client = HttpClient.newHttpClient();

        //---------------------------------------------------------------------------------
        System.out.println("\nStarting Kienboec REGISTER Test");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/users"))
                //.setHeader("Authorization", "Basic kienboec-mtcgToken")
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\"Username\":\"kienboec\", \"Password\":\"daniel\"}"))
                //.GET()
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (res.statusCode() == 201) {
                System.out.println("Create Test Kienboec successful");
            }
            else
            {
                System.out.println("Creation of user Kienboeck FAILED");
            }
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }
        //---------------------------------------------------------------------------------

        System.out.println("\nStarting Altenhofer REGISTER Test");
        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/users"))
                .setHeader("Content-Type", "application/json")
                //.setHeader("Authorization", "Basic kienboec-mtcgToken")
                .POST(HttpRequest.BodyPublishers.ofString("{\"Username\":\"altenhof\", \"Password\":\"markus\"}"))
                //.GET()
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (res.statusCode() == 201) {
                System.out.println("Create Altenhofer Test successful");
            }
            else
            {
                System.out.println("Creation of user Altenhofer FAILED");
            }
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }

        //---------------------------------------------------------------------------------

        //---------------------------------------------------------------------------------

        System.out.println("\nDouble register Altenhofer Test");
        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/users"))
                .setHeader("Content-Type", "application/json")
                //.setHeader("Authorization", "Basic kienboec-mtcgToken")
                .POST(HttpRequest.BodyPublishers.ofString("{\"Username\":\"altenhof\", \"Password\":\"markus\"}"))
                //.GET()
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (res.statusCode() != 201) {
                System.out.println("Failed as expected");
            }
            else
            {
                System.out.println("double register test was UNSUCCESSFUL ");
            }
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }

        //---------------------------------------------------------------------------------

        //---------------------------------------------------------------------------------

        System.out.println("\nDouble register Kienboec Test");
        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/users"))
                .setHeader("Content-Type", "application/json")
                //.setHeader("Authorization", "Basic kienboec-mtcgToken")
                .POST(HttpRequest.BodyPublishers.ofString("{\"Username\":\"kienboec\", \"Password\":\"markus\"}"))
                //.GET()
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (res.statusCode() != 201) {
                System.out.println("Failed as expected");
            }
            else
            {
                System.out.println("double register test was UNSUCCESSFUL ");
            }
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }

        //---------------------------------------------------------------------------------



        //---------------------------------------------------------------------------------
        System.out.println("\nStarting Kienboeck LOGIN Test");
        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/sessions"))
                .setHeader("Content-Type", "application/json")
                //.setHeader("Authorization", "Basic kienboec-mtcgToken")
                .POST(HttpRequest.BodyPublishers.ofString("{\"Username\":\"kienboec\", \"Password\":\"daniel\"}"))
                //.GET()
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (res.statusCode() == 200) {
                System.out.println("Login Test Kienboeck successful");
            }
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }

        //---------------------------------------------------------------------------------


        //---------------------------------------------------------------------------------

        System.out.println("\nStarting Altenhofer LOGIN Test");
        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/sessions"))
                .setHeader("Content-Type", "application/json")
                //.setHeader("Authorization", "Basic kienboec-mtcgToken")
                .POST(HttpRequest.BodyPublishers.ofString("{\"Username\":\"altenhof\", \"Password\":\"markus\"}"))
                //.GET()
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (res.statusCode() == 200) {
                System.out.println("Login Test Altenhofer successful");
            }
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }

        //---------------------------------------------------------------------------------

        //---------------------------------------------------------------------------------

        System.out.println("\nLogin with wrong credentials test");
        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/sessions"))
                .setHeader("Content-Type", "application/json")
                //.setHeader("Authorization", "Basic kienboec-mtcgToken")
                .POST(HttpRequest.BodyPublishers.ofString("{\"Username\":\"altenhof\", \"Password\":\"test\"}"))
                //.GET()
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (res.statusCode() == 403) {
                System.out.println("Login with wrong credentials failed as expected");
            }
            else
            {
                System.out.println("Login with wrong credentials was POSSIBLE");
            }
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }

        //---------------------------------------------------------------------------------




        //---------------------------------------------------------------------------------
        //11 package -> curl -X GET http://localhost:10001/cards --header "Authorization: Basic kienboec-mtcgToken"
        // -> random 4 auswählen -> ids in eine Liste
        // -> request mit den Ids builden

        System.out.println("\nStarting Kienboeck ACQUIRE PACKAGES Test");
        List<UUID> cardIdsForLaterKienboeck = new ArrayList<>();
        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/transactions/packages"))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Basic kienboec-mtcgToken")
                //.POST(HttpRequest.BodyPublishers.ofString("{\"Username\":\"altenhof\", \"Password\":\"markus\"}"))
                .POST(HttpRequest.BodyPublishers.ofString(""))
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (res.statusCode() == 200) {
                System.out.println("Cards Test Kienboeck successful");
            } else {
            System.out.println("Cards Test Kienboeck UNSUCCESSFUL");
            }
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }

        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/transactions/packages"))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Basic kienboec-mtcgToken")
                //.POST(HttpRequest.BodyPublishers.ofString("{\"Username\":\"altenhof\", \"Password\":\"markus\"}"))
                .POST(HttpRequest.BodyPublishers.ofString(""))
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (res.statusCode() == 200) {
                System.out.println("Cards Test Kienboeck successful");
            } else {
                System.out.println("Cards Test Kienboeck UNSUCCESSFUL");
            }
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }

        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/transactions/packages"))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Basic kienboec-mtcgToken")
                //.POST(HttpRequest.BodyPublishers.ofString("{\"Username\":\"altenhof\", \"Password\":\"markus\"}"))
                .POST(HttpRequest.BodyPublishers.ofString(""))
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (res.statusCode() == 200) {
                System.out.println("Cards Test Kienboeck successful");
            } else {
                System.out.println("Cards Test Kienboeck UNSUCCESSFUL");
            }
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }


        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/transactions/packages"))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Basic kienboec-mtcgToken")
                //.POST(HttpRequest.BodyPublishers.ofString("{\"Username\":\"altenhof\", \"Password\":\"markus\"}"))
                .POST(HttpRequest.BodyPublishers.ofString(""))
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (res.statusCode() == 200) {
                System.out.println("Cards Test Kienboeck successful");
            } else {
                System.out.println("Cards Test Kienboeck UNSUCCESSFUL");
            }
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }
        //---------------------------------------------------------------------------------

        System.out.println("\nStarting Kienboeck RUN OUT OF MONEY Test");
        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/transactions/packages"))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Basic kienboec-mtcgToken")
                //.POST(HttpRequest.BodyPublishers.ofString("{\"Username\":\"altenhof\", \"Password\":\"markus\"}"))
                .POST(HttpRequest.BodyPublishers.ofString(""))
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (res.statusCode() != 200) {
                System.out.println("Kienboec ran out of money successful");
            } else {
                System.out.println("Kienboec ran out of money UNSUCCESSFUL");
            }
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }


        //-------------------------------------------------------------------------------
        System.out.println("\nStarting Altenhofer ACQUIRE PACKAGES Test");
        List<UUID> cardIdsForLaterAltenhofer = new ArrayList<>();
        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/transactions/packages"))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Basic altenhof-mtcgToken")
                .POST(HttpRequest.BodyPublishers.ofString(""))
                //.GET()
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (res.statusCode() == 200) {
                System.out.println("Cards Test Altenhofer successful");
            } else {
                System.out.println("Cards Test Altenhofer UNSUCCESSFUL");
            }
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }

        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/transactions/packages"))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Basic altenhof-mtcgToken")
                .POST(HttpRequest.BodyPublishers.ofString(""))
                //.GET()
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (res.statusCode() == 200) {
                System.out.println("Cards Test Altenhofer successful");
            } else {
                System.out.println("Cards Test Altenhofer UNSUCCESSFUL");
            }
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }

        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/transactions/packages"))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Basic altenhof-mtcgToken")
                .POST(HttpRequest.BodyPublishers.ofString(""))
                //.GET()
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (res.statusCode() == 200) {
                System.out.println("Cards Test Altenhofer successful");
            } else {
                System.out.println("Cards Test Altenhofer UNSUCCESSFUL");
            }
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }

        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/transactions/packages"))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Basic altenhof-mtcgToken")
                .POST(HttpRequest.BodyPublishers.ofString(""))
                //.GET()
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (res.statusCode() == 200) {
                System.out.println("Cards Test Altenhofer successful");
            } else {
                System.out.println("Cards Test Altenhofer UNSUCCESSFUL");
            }
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }

        //---------------------------------------------------------------------------------

        System.out.println("\nStarting Altenhof RUN OUT OF MONEY Test");
        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/transactions/packages"))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Basic altenhof-mtcgToken")
                //.POST(HttpRequest.BodyPublishers.ofString("{\"Username\":\"altenhof\", \"Password\":\"markus\"}"))
                .POST(HttpRequest.BodyPublishers.ofString(""))
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (res.statusCode() != 200) {
                System.out.println("Altenhof ran out of money successful");
            } else {
                System.out.println("Altenhof ran out of money UNSUCCESSFUL");
            }
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }


        //------------------------------------------------------------------------------------------------
        System.out.println("\nSHOW all CARDS Kienboeck");

        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/cards"))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Basic kienboec-mtcgToken")
                //.POST(HttpRequest.BodyPublishers.ofString("{\"Username\":\"altenhof\", \"Password\":\"markus\"}"))
                .GET()
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());

            com.tomuta.swenoop.cards.Card[] cards = mapper.readValue(res.body(), com.tomuta.swenoop.cards.Card[].class);

            for (int i = 0; i < 4; i++) {
                cardIdsForLaterKienboeck.add(cards[i].getId());
            }

            System.out.println(res.body());
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }
        //---------------------------------------------------------------------------------

        //---------------------------------------------------------------------------------

        System.out.println("\nSHOW all CARDS Altenhofer");

        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/cards"))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Basic altenhof-mtcgToken")
                //.POST(HttpRequest.BodyPublishers.ofString("{\"Username\":\"altenhof\", \"Password\":\"markus\"}"))
                .GET()
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());

            com.tomuta.swenoop.cards.Card[] cards = mapper.readValue(res.body(), com.tomuta.swenoop.cards.Card[].class);

            for (int i = 0; i < 4; i++) {
                cardIdsForLaterAltenhofer.add(cards[i].getId());
            }

            System.out.println(res.body());
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }

        //---------------------------------------------------------------------------------


        //---------------------------------------------------------------------------------

        System.out.println("\nUnconfigures DECK Kienboeck");
        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/deck"))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Basic kienboec-mtcgToken")
                //.POST(HttpRequest.BodyPublishers.ofString("{\"Username\":\"altenhof\", \"Password\":\"markus\"}"))
                .GET()
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(res.body());
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }
        //---------------------------------------------------------------------------------

        //---------------------------------------------------------------------------------

        System.out.println("\nUnconfigures DECK Altenhofer");

        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/deck"))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Basic altenhof-mtcgToken")
                //.POST(HttpRequest.BodyPublishers.ofString("{\"Username\":\"altenhof\", \"Password\":\"markus\"}"))
                .GET()
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(res.body());
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }

        //---------------------------------------------------------------------------------

        System.out.println("\nStarting Kienboeck CONFIGURE DECK Test");
        StringBuilder sb = new StringBuilder();
        sb.append("{\"cardIds\":");
        sb.append("[");
        for (var cardID : cardIdsForLaterKienboeck
        ) {
            sb.append("\"").append(cardID).append("\"").append(",");
        }
        sb.replace(sb.length() - 1, sb.length(), "");
        sb.append("]}");

        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/deck"))
                .setHeader("Authorization", "Basic kienboec-mtcgToken")
                .PUT(HttpRequest.BodyPublishers.ofString(sb.toString()))
                //.GET()
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (res.statusCode() == 200) {
                System.out.println("Configuration Deck Test Kienboeck successful");
            } else {
                System.out.println("Configuration Deck Test Kienboeck UNSUCCESSFUL");
            }
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }

        //---------------------------------------------------------------------------------

        //---------------------------------------------------------------------------------

        System.out.println("Configured DECK Kienboeck");

        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/deck"))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Basic kienboec-mtcgToken")
                //.POST(HttpRequest.BodyPublishers.ofString("{\"Username\":\"altenhof\", \"Password\":\"markus\"}"))
                .GET()
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(res.body());
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }
        //---------------------------------------------------------------------------------


        System.out.println("Kienboec tries to use altenhof cards test");
        sb = new StringBuilder();
        sb.append("{\"cardIds\":");
        sb.append("[");
        for (var cardID : cardIdsForLaterAltenhofer
        ) {
            sb.append("\"").append(cardID).append("\"").append(",");
        }
        sb.replace(sb.length() - 1, sb.length(), "");
        sb.append("]}");

        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/deck"))
                .setHeader("Authorization", "Basic kienboec-mtcgToken")
                .PUT(HttpRequest.BodyPublishers.ofString(sb.toString()))
                //.GET()
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (res.statusCode() != 200) {
                System.out.println("Kienboec failed to use altenhofs cards as expected");
            } else {
                System.out.println("Kienboec managed to use altenhofs cards UNEXPECTED");
            }
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }
        //---------------------------------------------------------------------------------

        //---------------------------------------------------------------------------------

        System.out.println("Configured DECK Kienboeck after configuration failed");

        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/deck"))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Basic kienboec-mtcgToken")
                //.POST(HttpRequest.BodyPublishers.ofString("{\"Username\":\"altenhof\", \"Password\":\"markus\"}"))
                .GET()
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(res.body());
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }
        //---------------------------------------------------------------------------------


        //--------------------------------------------------------------------------
        System.out.println("\nStarting Altenhofer CONFIGURE DECK Test");
        sb = new StringBuilder();
        sb.append("{\"cardIds\":");
        sb.append("[");
        for (var cardID : cardIdsForLaterAltenhofer
        ) {
            sb.append("\"").append(cardID).append("\"").append(",");
        }
        sb.replace(sb.length() - 1, sb.length(), "");
        sb.append("]}");

        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/deck"))
                .setHeader("Authorization", "Basic altenhof-mtcgToken")
                .PUT(HttpRequest.BodyPublishers.ofString(sb.toString()))
                //.GET()
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (res.statusCode() == 200) {
                System.out.println("Configure Deck Test Altenhofer successful");
            } else {
                System.out.println("Configure Deck Test Altenhofer UNSUCCESSFUL");
            }
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }

        //---------------------------------------------------------------------------------



        //---------------------------------------------------------------------------------

        System.out.println("Configured DECK Altenhofer");

        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/deck"))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Basic altenhof-mtcgToken")
                //.POST(HttpRequest.BodyPublishers.ofString("{\"Username\":\"altenhof\", \"Password\":\"markus\"}"))
                .GET()
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(res.body());
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }

        //---------------------------------------------------------------------------------


        //---------------------------------------------------------------------------------

        System.out.println("\nUPDATE test Altenhofer");

        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/users/altenhof"))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Basic altenhof-mtcgToken")
                .PUT(HttpRequest.BodyPublishers.ofString("{\"Description\": \"me playin...\"}"))
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (res.statusCode() == 200) {
                System.out.println("Update Test Altenhofer successful");
            } else {
                System.out.println("Update Test Altenhofer UNSUCCESSFUL");
            }
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }
        //---------------------------------------------------------------------------------

        //---------------------------------------------------------------------------------

        System.out.println("\nShow Kienboeck before update");

        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/users"))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Basic kienboec-mtcgToken")
                .GET()
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (res.statusCode() == 200) {
                System.out.println(res.body());
            } else {
                System.out.println("Show Kienboeck before update was UNSUCCESSFUL");
            }
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }

        //---------------------------------------------------------------------------------


        //---------------------------------------------------------------------------------

        System.out.println("UPDATE test Kienboeck");

        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/users/kienboec"))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Basic kienboec-mtcgToken")
                .PUT(HttpRequest.BodyPublishers.ofString("{\"Description\": \"me playin...\"}"))
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (res.statusCode() == 200) {
                System.out.println("Update Test Altenhofer successful");
            } else {
                System.out.println("Update Test Altenhofer UNSUCCESSFUL");
            }
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }

        //---------------------------------------------------------------------------------

        //---------------------------------------------------------------------------------

        System.out.println("Show Kienboeck after update");

        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/users"))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Basic kienboec-mtcgToken")
                .GET()
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (res.statusCode() == 200) {
                System.out.println(res.body());
            } else {
                System.out.println("Show Kienboeck before update was UNSUCCESSFUL");
            }
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }

        //---------------------------------------------------------------------------------


        //---------------------------------------------------------------------------------

        System.out.println("\nSHOW STATS test Kienboeck");

        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/stats"))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Basic kienboec-mtcgToken")
                .GET()
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(res.body());
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }

        //---------------------------------------------------------------------------------


        //---------------------------------------------------------------------------------

        System.out.println("SHOW STATS test Altenhofer");

        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/stats"))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Basic altenhof-mtcgToken")
                .GET()
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(res.body());
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }

        //---------------------------------------------------------------------------------

        //---------------------------------------------------------------------------------

        System.out.println("SHOW SCOREBOARD");

        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/score"))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Basic altenhof-mtcgToken")
                .GET()
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(res.body());
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }

        //---------------------------------------------------------------------------------



        //---------------------------------------------------------------------------------

        System.out.println("\nQueue Kienboec for battle");

        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/battles"))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Basic kienboec-mtcgToken")
                .POST(HttpRequest.BodyPublishers.ofString(""))
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());

            if(res.statusCode() == 200)
            {
                System.out.println("Queued kienboec successfully");
            }
            else
            {
                System.out.println("FAILED TO QUEUE Kienboec");
            }
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }

        //---------------------------------------------------------------------------------


        //---------------------------------------------------------------------------------

        System.out.println("Queue Kienboec again for battle");

        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/battles"))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Basic kienboec-mtcgToken")
                .POST(HttpRequest.BodyPublishers.ofString(""))
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());

            if(res.statusCode() == 208)
            {
                System.out.println("double queueing failed (expected!)");
            }
            else
            {
                System.out.println("FAILED TO QUEUE Kienboec");
            }
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }

        //---------------------------------------------------------------------------------


        System.out.println("Queue Altenhof for battle");

        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/battles"))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Basic altenhof-mtcgToken")
                .POST(HttpRequest.BodyPublishers.ofString(""))
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());

            if(res.statusCode() == 200)
            {
                System.out.println("Queued Altenhof successfully");
            }
            else
            {
                System.out.println("FAILED TO QUEUE Altenhof");
            }
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }

        //---------------------------------------------------------------------------------

        //wait for battle to be calculated -> server would notify clients after calculation finished
        try{sleep(2000);}catch(Exception e) {}

        //---------------------------------------------------------------------------------

        System.out.println("\nSHOW STATS test Kienboeck");

        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/stats"))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Basic kienboec-mtcgToken")
                .GET()
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(res.body());
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }

        //---------------------------------------------------------------------------------


        //---------------------------------------------------------------------------------

        System.out.println("SHOW STATS test Altenhofer");

        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/stats"))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Basic altenhof-mtcgToken")
                .GET()
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(res.body());
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }

        //---------------------------------------------------------------------------------

        //---------------------------------------------------------------------------------

        System.out.println("SHOW SCOREBOARD");

        request = HttpRequest.newBuilder()
                .uri(URI.create(BASEURL + "/score"))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Basic altenhof-mtcgToken")
                .GET()
                .build();

        try {
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(res.body());
        } catch (Exception e) {
            System.out.println("DIED");
            return;
        }

        //---------------------------------------------------------------------------------



    }
}