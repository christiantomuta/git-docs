package com.tomuta.swenoop.cards;
import com.tomuta.swenoop.user.User;
import org.junit.jupiter.api.*;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CardsTest {
    Card card;
//public Card(UUID id, UUID owner, ECard_type card_type, int damage, element_type elem) {
    @BeforeEach
    void beforeEachFunction(){
        card = new Card(UUID.randomUUID(), UUID.randomUUID(), Card.ECard_type.Goblin, 50, Card.element_type.Water);

    }

    @Test
    void testDamageGetter(){
        assertEquals(card.getDamage(), 50);
    }

    @Test
    void testECard_typeAndtoStringFunction(){
        assertEquals(Card.ECard_type.Goblin.toString(), "Goblin");
    }

    @Test
    void TestUUIDRandomGenerator(){
        UUID temp_ID = UUID.randomUUID();
        assertNotEquals(temp_ID, UUID.randomUUID());
    }
}
