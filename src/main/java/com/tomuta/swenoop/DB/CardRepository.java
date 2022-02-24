package com.tomuta.swenoop.DB;

import com.tomuta.swenoop.cards.Card;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CardRepository implements IRepository<Card> {

    private static CardRepository instance;

    public static CardRepository GetInstance() {
        if (instance != null) return instance;
        instance = new CardRepository();
        return instance;
    }

    Connection connection;

    private CardRepository() {
        this.connection = getConnection.connect_to_database();
    }

    @Override
    public boolean delete(Card toDelete) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM cards WHERE card_id =?");
            statement.setString(1, toDelete.getId().toString());
            var res = statement.execute();
            return res;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(UUID id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM cards WHERE user_ID =?");
            statement.setString(1, id.toString());

            var res = statement.execute();
            return res;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Card GetById(UUID id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cards WHERE card_id =?");
            statement.setString(1, id.toString());

            var resultSet = statement.executeQuery();

            resultSet.next();

            Card res = new Card(UUID.fromString(resultSet.getString("card_id")),
                    resultSet.getString("description"),
                    UUID.fromString(resultSet.getString("user_id")),
                    resultSet.getBoolean("inDeck"),
                    Card.ECard_type.valueOf(resultSet.getString("card_kind")),
                    resultSet.getInt("damage"),
                    Card.element_type.valueOf(resultSet.getString("card_element")));

            return res;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Card> getCardsByUser(UUID id) {
        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cards WHERE user_id=?");
            statement.setString(1, id.toString());

            var resultSet = statement.executeQuery();

            List<Card> res = new ArrayList<>();
            while(resultSet.next()) {
                res.add(new Card(UUID.fromString(resultSet.getString("card_id")),
                        resultSet.getString("card_description"),
                        UUID.fromString(resultSet.getString("user_id")),
                        resultSet.getBoolean("inDeck"),
                        Card.ECard_type.values()[resultSet.getInt("card_kind")],
                        resultSet.getInt("damage"),
                        Card.element_type.values()[resultSet.getInt("card_element")]));
            }
            return res;
        } catch (Exception e) {
            return null;
        }
    }


    public List<Card> getDeckByUser(UUID id) {
        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cards WHERE user_id=? AND inDeck=true");
            statement.setString(1, id.toString());

            var resultSet = statement.executeQuery();

            List<Card> res = new ArrayList<>();
            while(resultSet.next()) {
                res.add(new Card(UUID.fromString(resultSet.getString("card_id")),
                        resultSet.getString("card_description"),
                        UUID.fromString(resultSet.getString("user_id")),
                        resultSet.getBoolean("inDeck"),
                        Card.ECard_type.values()[resultSet.getInt("card_kind")],
                        resultSet.getInt("damage"),
                        Card.element_type.values()[resultSet.getInt("card_element")]));
            }
            return res;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean setUserDeck(UUID userId, List<String> cardIds) {
        try {

            //Check if cards are owned by the given user - do given cards belong to given user?
            PreparedStatement statement;

            for(var cardId : cardIds)
            {
                statement = connection.prepareStatement("SELECT * FROM cards WHERE user_id=? AND card_id=?");
                statement.setString(1, userId.toString());
                statement.setString(2, cardId); //is user and card ID the same as the data given to this method?
                var resultSet = statement.executeQuery();
                if(!resultSet.next())
                {
                    return false;
                }
            }

            //Reset inDeck property of cards which are owned by the given user
            statement = connection.prepareStatement("SELECT * FROM cards WHERE user_id=? AND inDeck=true");
            statement.setString(1, userId.toString());

            var resultSet = statement.executeQuery();

            while(resultSet.next())
            {
                UUID card_id = UUID.fromString(resultSet.getString("card_id"));
                statement = connection.prepareStatement("UPDATE cards SET inDeck=false WHERE card_id=?");
                statement.setString(1, card_id.toString());
                statement.execute(); //ERROR HANDLING -> revert? Transactions?
            }

            //Set new Deck
            for(var cardId : cardIds)
            {
                statement = connection.prepareStatement("UPDATE cards SET inDeck=true WHERE card_id=?");
                statement.setString(1, cardId.toString());
                statement.execute();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public Card Add(Card toAdd) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO cards (card_id,card_description,card_kind,card_element,damage,user_id,indeck) VALUES(?,?,?,?,?,?,?)");
            statement.setString(1, toAdd.getId().toString());
            statement.setString(2, toAdd.getDescription());
            statement.setInt(3, toAdd.getCard_type().ordinal());
            statement.setInt(4, toAdd.getElem().ordinal());
            statement.setInt(5, toAdd.getDamage());
            statement.setString(6, toAdd.getOwner().toString());
            statement.setBoolean(7, toAdd.isInDeck());

            statement.execute();
            if((statement.getUpdateCount() > 0)) return toAdd;
            return null;
        } catch (SQLException e) {
            return null;
        }

    }

    @Override
    public Card Update(Card toUpdate) {
        return null;
    }

    @Override
    public List<Card> GetAll() {
        return null;
    }
}
