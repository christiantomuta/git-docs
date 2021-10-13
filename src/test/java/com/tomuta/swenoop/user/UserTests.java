package com.tomuta.swenoop.user;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class UserTests {
    User user;
    @BeforeEach
    void beforeEachFunction(){
        user = new User("Chris", "pw", 150);
    }

    @Test
    void testFunction(){
        User user2 = new User("das", "dies", 666);
        user2.setCoins(345);
        assertEquals(user2.getCoins(), 345);

    }

}
