package com.tomuta.swenoop.SerializationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomuta.swenoop.DTOs.RegisterAndLoginDTO;
import com.tomuta.swenoop.DTOs.ScoreBoardEntryDTO;
import com.tomuta.swenoop.DTOs.StatsRepresentation;
import com.tomuta.swenoop.DTOs.UserRepresentationDTO;
import com.tomuta.swenoop.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SerializationTests {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void UserRepresentationSerializationTest()
    {
        User user = new User("Testuser", "Test");
        UserRepresentationDTO userDTO = new UserRepresentationDTO(user.getUsername(), user.getCoins(), user.getDescription(), user.getElo());

        try {
            assertEquals("{\"username\":\"Testuser\",\"coins\":20,\"description\":\"\",\"elo\":100}", mapper.writeValueAsString(userDTO));
        }
        catch(Exception e)
        {
            Assertions.fail();
        }
    }


    @Test
    public void UserRepresentationDeserializationTest()
    {

        try {
            UserRepresentationDTO userDTO = mapper.readValue("{\"username\":\"Testuser\",\"coins\":20,\"description\":\"\",\"elo\":100}", UserRepresentationDTO.class);
            assertEquals("Testuser", userDTO.username);
            assertEquals(20, userDTO.coins);
            assertEquals("", userDTO.description);
            assertEquals(100, userDTO.elo);
        }
        catch(Exception e)
        {
            Assertions.fail();
        }
    }


    @Test
    public void ScoreBoardEntrySerialization()
    {
        User user = new User("Testuser", "Test");
        ScoreBoardEntryDTO scoreBoardEntryDTO = new ScoreBoardEntryDTO(user.getUsername(), user.getElo());

        try {
            assertEquals("{\"Username\":\"Testuser\",\"Elo\":100}", mapper.writeValueAsString(scoreBoardEntryDTO));
        }
        catch(Exception e)
        {
            Assertions.fail();
        }
    }


    @Test
    public void ScoreBoardEntryDeserialization()
    {
        try {
            ScoreBoardEntryDTO scoreBoardEntryDTO = mapper.readValue("{\"Username\":\"Testuser\",\"Elo\":100}", ScoreBoardEntryDTO.class);
            assertEquals("Testuser", scoreBoardEntryDTO.Username);
            assertEquals(100, scoreBoardEntryDTO.Elo);
        }
        catch(Exception e)
        {
            Assertions.fail();
        }
    }


    @Test
    public void StatsRepresentationSerialization()
    {
        User user = new User("Testuser", "Test");
        StatsRepresentation statsRepresentation = new StatsRepresentation(user.getUsername(), user.getElo());

        try {
            assertEquals("{\"Username\":\"Testuser\",\"Elo\":100}", mapper.writeValueAsString(statsRepresentation));
        }
        catch(Exception e)
        {
            Assertions.fail();
        }
    }

    @Test
    public void StatsRepresentationDeserialization()
    {
        try {
            StatsRepresentation statsRepresentation = mapper.readValue("{\"Username\":\"Testuser\",\"Elo\":100}", StatsRepresentation.class);
            assertEquals("Testuser", statsRepresentation.Username);
            assertEquals(100, statsRepresentation.Elo);
        }
        catch(Exception e)
        {
            Assertions.fail();
        }
    }



    @Test
    public void RegisterAndLoginDeserialization()
    {
        try {
            RegisterAndLoginDTO registerAndLoginDTO = mapper.readValue("{\"Username\":\"Testuser\",\"Password\":\"secret\"}", RegisterAndLoginDTO.class);
            assertEquals("Testuser", registerAndLoginDTO.Username);
            assertEquals("secret", registerAndLoginDTO.Password);
        }
        catch(Exception e)
        {
            Assertions.fail();
        }
    }



}
