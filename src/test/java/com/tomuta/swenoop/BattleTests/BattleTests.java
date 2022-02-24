package com.tomuta.swenoop.BattleTests;

import com.tomuta.swenoop.cards.Card;
import com.tomuta.swenoop.server.Controllers.FightController;
import com.tomuta.swenoop.user.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BattleTests {

    FightController f_controller = FightController.getInstance();

    //Test Battle Rules
    @Test
    void ElementalAdvantageFireWaterTest(){

        User user1 = new User("Test", "Test");
        Card card1 = new Card(UUID.randomUUID(),"",user1.getID(),
                true, Card.ECard_type.Spell, 10, Card.element_type.Water);
        List<Card> cards_user1 = new ArrayList<>();
        cards_user1.add(card1);

        User user2 = new User("Test", "Test");
        Card card2 = new Card(UUID.randomUUID(),"",user1.getID(),
                true, Card.ECard_type.Spell, 20, Card.element_type.Fire);
        List<Card> cards_user2 = new ArrayList<>();
        cards_user2.add(card2);

        var res = f_controller.CalculateBattle(user1,user2,cards_user1,cards_user2);

        assertEquals(res,user1.getID());
    }


    @Test
    void ElementalAdvantageWaterRegularTest(){

        User user1 = new User("Test", "Test");
        Card card1 = new Card(UUID.randomUUID(),"",user1.getID(),
                true, Card.ECard_type.Spell, 10, Card.element_type.Regular);
        List<Card> cards_user1 = new ArrayList<>();
        cards_user1.add(card1);

        User user2 = new User("Test", "Test");
        Card card2 = new Card(UUID.randomUUID(),"",user1.getID(),
                true, Card.ECard_type.Spell, 20, Card.element_type.Water);
        List<Card> cards_user2 = new ArrayList<>();
        cards_user2.add(card2);

        var res = f_controller.CalculateBattle(user1,user2,cards_user1,cards_user2);

        assertEquals(res,user1.getID());
    }


    @Test
    void ElementalAdvantageFireRegularTest(){

        User user1 = new User("Test", "Test");
        Card card1 = new Card(UUID.randomUUID(),"",user1.getID(),
                true, Card.ECard_type.Spell, 10, Card.element_type.Fire);
        List<Card> cards_user1 = new ArrayList<>();
        cards_user1.add(card1);

        User user2 = new User("Test", "Test");
        Card card2 = new Card(UUID.randomUUID(),"",user1.getID(),
                true, Card.ECard_type.Spell, 20, Card.element_type.Regular);
        List<Card> cards_user2 = new ArrayList<>();
        cards_user2.add(card2);

        var res = f_controller.CalculateBattle(user1,user2,cards_user1,cards_user2);

        assertEquals(res,user1.getID());
    }



    @Test
    void MonsterVSMonsterTest(){

        User user1 = new User("Test", "Test");
        Card card1 = new Card(UUID.randomUUID(),"",user1.getID(),
                true, Card.ECard_type.Ork, 20, Card.element_type.Fire);
        List<Card> cards_user1 = new ArrayList<>();
        cards_user1.add(card1);

        User user2 = new User("Test", "Test");
        Card card2 = new Card(UUID.randomUUID(),"",user1.getID(),
                true, Card.ECard_type.Ork, 10, Card.element_type.Regular);
        List<Card> cards_user2 = new ArrayList<>();
        cards_user2.add(card2);

        var res = f_controller.CalculateBattle(user1,user2,cards_user1,cards_user2);

        assertEquals(res,user1.getID());
    }


    @Test
    void DrawTest(){

        User user1 = new User("Test", "Test");
        Card card1 = new Card(UUID.randomUUID(),"",user1.getID(),
                true, Card.ECard_type.Ork, 20, Card.element_type.Fire);
        List<Card> cards_user1 = new ArrayList<>();
        cards_user1.add(card1);

        User user2 = new User("Test", "Test");
        Card card2 = new Card(UUID.randomUUID(),"",user1.getID(),
                true, Card.ECard_type.Ork, 20, Card.element_type.Regular);
        List<Card> cards_user2 = new ArrayList<>();
        cards_user2.add(card2);

        var res = f_controller.CalculateBattle(user1,user2,cards_user1,cards_user2);

        assertEquals(res,null);
    }

    @Test
    void SpecialRuleOrkVSWizardTest(){

        User user1 = new User("Test", "Test");
        Card card1 = new Card(UUID.randomUUID(),"",user1.getID(),
                true, Card.ECard_type.Wizard, 10, Card.element_type.Fire);
        List<Card> cards_user1 = new ArrayList<>();
        cards_user1.add(card1);

        User user2 = new User("Test", "Test");
        Card card2 = new Card(UUID.randomUUID(),"",user1.getID(),
                true, Card.ECard_type.Ork, 50, Card.element_type.Regular);
        List<Card> cards_user2 = new ArrayList<>();
        cards_user2.add(card2);

        var res = f_controller.CalculateBattle(user1,user2,cards_user1,cards_user2);

        assertEquals(res,user1.getID());
    }

    @Test
    void SpecialRuleKnightVSWaterSpellTest(){

        User user1 = new User("Test", "Test");
        Card card1 = new Card(UUID.randomUUID(),"",user1.getID(),
                true, Card.ECard_type.Spell, 10, Card.element_type.Water);
        List<Card> cards_user1 = new ArrayList<>();
        cards_user1.add(card1);

        User user2 = new User("Test", "Test");
        Card card2 = new Card(UUID.randomUUID(),"",user1.getID(),
                true, Card.ECard_type.Knight, 50, Card.element_type.Regular);
        List<Card> cards_user2 = new ArrayList<>();
        cards_user2.add(card2);

        var res = f_controller.CalculateBattle(user1,user2,cards_user1,cards_user2);

        assertEquals(res,user1.getID());
    }

    @Test
    void SpecialRuleKnightVSRegularSpellTest(){

        User user1 = new User("Test", "Test");
        Card card1 = new Card(UUID.randomUUID(),"",user1.getID(),
                true, Card.ECard_type.Spell, 10, Card.element_type.Regular);
        List<Card> cards_user1 = new ArrayList<>();
        cards_user1.add(card1);

        User user2 = new User("Test", "Test");
        Card card2 = new Card(UUID.randomUUID(),"",user1.getID(),
                true, Card.ECard_type.Knight, 50, Card.element_type.Regular);
        List<Card> cards_user2 = new ArrayList<>();
        cards_user2.add(card2);

        var res = f_controller.CalculateBattle(user1,user2,cards_user1,cards_user2);

        assertEquals(res,user2.getID());
    }

    @Test
    void SpecialRuleGoblinVSDragonTest(){

        User user1 = new User("Test", "Test");
        Card card1 = new Card(UUID.randomUUID(),"",user1.getID(),
                true, Card.ECard_type.Dragon, 10, Card.element_type.Fire);
        List<Card> cards_user1 = new ArrayList<>();
        cards_user1.add(card1);

        User user2 = new User("Test", "Test");
        Card card2 = new Card(UUID.randomUUID(),"",user1.getID(),
                true, Card.ECard_type.Goblin, 50, Card.element_type.Regular);
        List<Card> cards_user2 = new ArrayList<>();
        cards_user2.add(card2);

        var res = f_controller.CalculateBattle(user1,user2,cards_user1,cards_user2);

        assertEquals(res,user1.getID());
    }


    @Test
    void SpecialRuleKrakenVSSpellTest(){

        User user1 = new User("Test", "Test");
        Card card1 = new Card(UUID.randomUUID(),"",user1.getID(),
                true, Card.ECard_type.Kraken, 10, Card.element_type.Fire);
        List<Card> cards_user1 = new ArrayList<>();
        cards_user1.add(card1);

        User user2 = new User("Test", "Test");
        Card card2 = new Card(UUID.randomUUID(),"",user1.getID(),
                true, Card.ECard_type.Spell, 50, Card.element_type.Regular);
        List<Card> cards_user2 = new ArrayList<>();
        cards_user2.add(card2);

        var res = f_controller.CalculateBattle(user1,user2,cards_user1,cards_user2);

        assertEquals(res,user1.getID());
    }


    @Test
    void SpecialRuleFireElfVSDragon(){

        User user1 = new User("Test", "Test");
        Card card1 = new Card(UUID.randomUUID(),"",user1.getID(),
                true, Card.ECard_type.Elf, 10, Card.element_type.Fire);
        List<Card> cards_user1 = new ArrayList<>();
        cards_user1.add(card1);

        User user2 = new User("Test", "Test");
        Card card2 = new Card(UUID.randomUUID(),"",user1.getID(),
                true, Card.ECard_type.Dragon, 50, Card.element_type.Regular);
        List<Card> cards_user2 = new ArrayList<>();
        cards_user2.add(card2);

        var res = f_controller.CalculateBattle(user1,user2,cards_user1,cards_user2);

        assertEquals(res,user1.getID());
    }

}
