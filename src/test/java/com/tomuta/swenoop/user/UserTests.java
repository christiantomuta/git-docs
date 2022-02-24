package com.tomuta.swenoop.user;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class UserTests {

    @Test
    void testCoins(){
        User user = new User("Lukas", "Aichbauer");
        assertEquals(user.getCoins(), 20);
    }
    @Test
    void testUserNameGetter(){
        User user = new User("Christian", "dies");
        assertEquals(user.getUsername(), "Christian");
    }

    @Test
    void testEloValue(){
        User user = new User("Lukas", "Aichbauer");
        assertEquals(user.getElo(), 100);
    }

}
