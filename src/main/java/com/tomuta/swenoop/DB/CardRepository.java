package com.tomuta.swenoop.DB;

import com.tomuta.swenoop.cards.Card;
import com.tomuta.swenoop.user.User;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
