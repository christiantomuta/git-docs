package com.tomuta.swenoop.server.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomuta.swenoop.DB.CardRepository;
import com.tomuta.swenoop.DB.UserRepository;
import com.tomuta.swenoop.cards.Card;
import com.tomuta.swenoop.http.ContentType;
import com.tomuta.swenoop.http.HttpStatus;
import com.tomuta.swenoop.server.Response;
import com.tomuta.swenoop.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FightController {

    private UserRepository u_repo = UserRepository.GetInstance();
    private CardRepository c_repo = CardRepository.GetInstance();

    private ObjectMapper mapper = new ObjectMapper();

    private static FightController instance;

    private FightController() {
    }

    private ReentrantLock reentrantLock = new ReentrantLock();
    private User waitingUser = null;

    private final int MAX_ROUNDS = 100;

    public static FightController getInstance() {
        if (instance != null) return instance;
        instance = new FightController();
        return instance;
    }

    public Response queueForBattle(String username) {
        reentrantLock.lock(); //lock to prevent race condition on waitingUser
        var user = u_repo.GetByUsername(username);
        if (user == null) return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, "");

        var user_cards = c_repo.getDeckByUser(user.getID());
        if(user_cards.size() != 4) return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, "");

        //If there is no user waiting -> user is queued
        if (waitingUser == null) {
            waitingUser = user;
            reentrantLock.unlock();
            return new Response(HttpStatus.OK, ContentType.JSON, "");
        }
        else if(waitingUser.getID().equals(user.getID()))
        {
            reentrantLock.unlock();
            return new Response(HttpStatus.ALREADY_REPORTED, ContentType.JSON, "");
        }
        //if there is already a user waiting
        else {
            battle(waitingUser, user);

            //reset queue
            waitingUser = null;
            reentrantLock.unlock();
            return new Response(HttpStatus.OK, ContentType.JSON, "");
        }
    }

    private void battle(User waitingUser, User newUser) {

        System.out.println("----------START BATTLE with user1:"+waitingUser.getUsername().replaceAll("\\s+", "")+" user2:"+newUser.getUsername().replaceAll("\\s+", "") + "------------");
        var cards_waitingUser = c_repo.getDeckByUser(waitingUser.getID());
        var cards_newUser = c_repo.getDeckByUser(newUser.getID());

        //if somehow a user manages to set his deck != 4 Cards -> clients would be notified and users will be dequeued
        if(cards_waitingUser.size() != 4 || cards_newUser.size() != 4) return;

        UUID winner = CalculateBattle(waitingUser,newUser,cards_waitingUser,cards_newUser);

        if(winner == null)
        {
            return;
        }
        else if (winner.equals(waitingUser.getID()))
        {
            u_repo.UpdateElo(waitingUser.getID(), 5);
            u_repo.UpdateElo(newUser.getID(), -3);
        }
        else if (winner.equals(newUser.getID()))
        {
            u_repo.UpdateElo(newUser.getID(), 5);
            u_repo.UpdateElo(waitingUser.getID(), -3);
        }
}


    //true = card1 is in advantage
    //false = card2 is in advantage
    //null = neither is in advantage
    public Boolean specialitychecker(Card card1, Card card2) {

        Card.ECard_type type1 = card1.getCard_type();
        Card.ECard_type type2 = card2.getCard_type();

        Card.element_type element1 = card1.getElem();
        Card.element_type element2 = card2.getElem();

        //RULES HERE
        if(type1 == Card.ECard_type.Goblin && type2 == Card.ECard_type.Dragon)
        {
            return false;
        }
        else if(type1 == Card.ECard_type.Dragon && type2 == Card.ECard_type.Goblin)
        {
            return true;
        }
        else if(type1 == Card.ECard_type.Ork && type2 == Card.ECard_type.Wizard)
        {
            return false;
        }
        else if(type1 == Card.ECard_type.Wizard && type2 == Card.ECard_type.Ork)
        {
            return true;
        }
        else if(type1 == Card.ECard_type.Knight && type2 == Card.ECard_type.Spell && element2 == Card.element_type.Water)
        {
            return false;
        }
        else if(type1 == Card.ECard_type.Spell && type2 == Card.ECard_type.Knight && element1 == Card.element_type.Water)
        {
            return true;
        }
        else if(type1 == Card.ECard_type.Spell && type2 == Card.ECard_type.Kraken)
        {
            return false;
        }
        else if(type1 == Card.ECard_type.Kraken && type2 == Card.ECard_type.Spell)
        {
            return true;
        }
        else if(type1 == Card.ECard_type.Dragon && type2 == Card.ECard_type.Elf && element2 == Card.element_type.Fire)
        {
            return false;
        }
        else if(type1 == Card.ECard_type.Elf && type2 == Card.ECard_type.Dragon && element1 == Card.element_type.Fire)
        {
            return true;
        }
        else {
            return null;
        }
    }

    //Returns the winner's UUID or null in case of a draw
    public UUID CalculateBattle(User waitingUser, User newUser, List<Card> cards_waitingUser, List<Card> cards_newUser)
    {
        Random random = new Random();

        int round_counter = 1;

        while (round_counter <= MAX_ROUNDS) {

            System.out.println("----------ROUND NUMBER: "+ round_counter +"------------");
            //draw random cards
            Card card_waitingUser = (Card) cards_waitingUser.toArray()[random.nextInt(cards_waitingUser.size())];
            Card card_newUser = (Card) cards_newUser.toArray()[random.nextInt(cards_newUser.size())];

            System.out.println("----------DRAW PHASE------------");
            System.out.println("----------"+waitingUser.getUsername().replaceAll("\\s+", "")+" drew: " + card_waitingUser.toString());
            System.out.println("----------"+newUser.getUsername().replaceAll("\\s+", "")+" drew: " + card_newUser.toString());

            //spell vs spell -> element is relevant
            Card.ECard_type type_waitingUser = card_waitingUser.getCard_type();
            Card.ECard_type type_newUser = card_newUser.getCard_type();

            Card.element_type elementType_waitingUser = card_waitingUser.getElem();
            Card.element_type elementType_newUser = card_newUser.getElem();

            //temporary variables -> will not be carried over to next round
            int damage_waitingUser = card_waitingUser.getDamage();
            int damage_newUser = card_newUser.getDamage();


            System.out.println("----------CALCULATION PHASE------------");
            //Spell vs Spell advantages
            if (type_waitingUser == Card.ECard_type.Spell && type_newUser == Card.ECard_type.Spell) {
                //who is in advantage?
                if (elementType_waitingUser == Card.element_type.Fire && elementType_newUser == Card.element_type.Water
                        || elementType_waitingUser == Card.element_type.Water && elementType_newUser == Card.element_type.Regular
                        || elementType_waitingUser == Card.element_type.Regular && elementType_newUser == Card.element_type.Fire) {

                    System.out.println(newUser.getUsername().replaceAll("\\s+", "") + " is in elemental advantage");

                    damage_newUser = damage_newUser * 2;
                    damage_waitingUser = damage_waitingUser / 2;

                    System.out.println(waitingUser.getUsername().replaceAll("\\s+", "") + " Damage: " + damage_waitingUser + " | " + newUser.getUsername().replaceAll("\\s+", "") + " Damage: " + damage_newUser);

                    if (damage_newUser > damage_waitingUser) {
                        cards_newUser.add(card_waitingUser);
                        cards_waitingUser.remove(card_waitingUser);
                        System.out.println(newUser.getUsername().replaceAll("\\s+", "") + " won round number: " + round_counter);
                    } else if (damage_waitingUser > damage_newUser) {
                        cards_waitingUser.add(card_newUser);
                        cards_newUser.remove(card_newUser);
                        System.out.println(waitingUser.getUsername().replaceAll("\\s+", "") + " won round number: " + round_counter);
                    } else {
                        System.out.println("Draw in round number: " + round_counter);
                        round_counter++;
                        continue;
                    }
                }
                else if (elementType_waitingUser == Card.element_type.Water && elementType_newUser == Card.element_type.Fire
                        || elementType_waitingUser == Card.element_type.Regular && elementType_newUser == Card.element_type.Water
                        || elementType_waitingUser == Card.element_type.Fire && elementType_newUser == Card.element_type.Regular)
                {

                    System.out.println(waitingUser.getUsername().replaceAll("\\s+", "") + " is in elemental advantage");
                    damage_waitingUser = damage_waitingUser * 2;
                    damage_newUser = damage_newUser / 2;

                    System.out.println(waitingUser.getUsername().replaceAll("\\s+", "") + " Damage: " + damage_waitingUser + " | " + newUser.getUsername().replaceAll("\\s+", "") + " Damage: " + damage_newUser);

                    if (damage_newUser > damage_waitingUser) {
                        cards_newUser.add(card_waitingUser);
                        cards_waitingUser.remove(card_waitingUser);
                        System.out.println(newUser.getUsername().replaceAll("\\s+", "") + " won round number: " + round_counter);
                    } else if (damage_waitingUser > damage_newUser) {
                        cards_waitingUser.add(card_newUser);
                        cards_newUser.remove(card_newUser);
                        System.out.println(waitingUser.getUsername().replaceAll("\\s+", "") + " won round number: " + round_counter);
                    } else {
                        System.out.println("Draw in round number: " + round_counter);
                        round_counter++;
                        continue;
                    }
                }
                else
                {
                    System.out.println(waitingUser.getUsername().replaceAll("\\s+", "") + " Damage: " + damage_waitingUser + " | " + newUser.getUsername().replaceAll("\\s+", "") + " Damage: " + damage_newUser);

                    if (damage_newUser > damage_waitingUser) {
                        cards_newUser.add(card_waitingUser);
                        cards_waitingUser.remove(card_waitingUser);
                        System.out.println(newUser.getUsername() + " won round number: " + round_counter);
                    } else if (damage_waitingUser > damage_newUser) {
                        cards_waitingUser.add(card_newUser);
                        cards_newUser.remove(card_newUser);
                        System.out.println(waitingUser.getUsername().replaceAll("\\s+", "") + " won round number: " + round_counter);
                    } else {
                        System.out.println("Draw in round number: " + round_counter);
                        round_counter++;
                        continue;
                    }
                }
            }
            //Spell vs Monster or Monster vs Monster
            else {
                //Special rules
                System.out.println("-------------------CHECK FOR SPECIAL RULES-------------------");
                var advantageExists = specialitychecker(card_waitingUser, card_newUser);
                if (advantageExists == null) {
                    System.out.println("NO SPECIAL RULES APPLIED");
                    if (damage_newUser > damage_waitingUser) {
                        cards_newUser.add(card_waitingUser);
                        cards_waitingUser.remove(card_waitingUser);
                        System.out.println(newUser.getUsername().replaceAll("\\s+", "") + " won round number: " + round_counter);
                    } else if (damage_waitingUser > damage_newUser) {
                        cards_waitingUser.add(card_newUser);
                        cards_newUser.remove(card_newUser);
                        System.out.println(waitingUser.getUsername().replaceAll("\\s+", "") + " won round number: " + round_counter);
                    } else {
                        System.out.println("Draw in round number: " + round_counter);
                        round_counter++;
                        continue;
                    }
                } else if (advantageExists) {

                    System.out.println("SPECIAL RULE FOUND: " + waitingUser.getUsername().replaceAll("\\s+", "")
                            + " won by special rule ("
                            + waitingUser.getUsername().replaceAll("\\s+", "") + " Card:" + card_waitingUser.toString()
                            + " | "
                            + newUser.getUsername().replaceAll("\\s+", "") + " Card:"+ card_newUser.toString() + ")");

                    cards_waitingUser.add(card_newUser);
                    cards_newUser.remove(card_newUser);
                } else if (!advantageExists) {

                    System.out.println("SPECIAL RULE FOUND: " + newUser.getUsername().replaceAll("\\s+", "")
                            + " won by special rule ("
                            + newUser.getUsername().replaceAll("\\s+", "") + " Card:" + card_newUser.toString()
                            + " | "
                            + waitingUser.getUsername().replaceAll("\\s+", "") + " Card:"+ card_waitingUser.toString() + ")");

                    cards_newUser.add(card_waitingUser);
                    cards_waitingUser.remove(card_waitingUser);
                }
            }
            if (cards_newUser.size() == 0) {
                System.out.println(waitingUser.getUsername().replaceAll("\\s+", "") + " won the battle!");
                return waitingUser.getID();
            } else if (cards_waitingUser.size() == 0) {
                System.out.println(newUser.getUsername().replaceAll("\\s+", "") + " won the battle!");
                return newUser.getID();
            }
            ++round_counter;
        }
        System.out.println("No winner! user1:"+waitingUser.getUsername()+" user2:"+newUser.getUsername());
        return null;
    }
}
