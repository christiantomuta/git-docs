package com.tomuta.swenoop.DB;

import com.tomuta.swenoop.DTOs.RegisterAndLoginDTO;
import com.tomuta.swenoop.Package.Package;
import com.tomuta.swenoop.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

public class UserRepository implements IRepository<User> {

    private static UserRepository instance;
    public static UserRepository GetInstance(){
        if(instance != null) return instance;
        instance = new UserRepository();
        return instance;
    }

    Connection connection;

    private UserRepository() {
        this.connection = getConnection.connect_to_database();
    }



    @Override
    public boolean delete(User toDelete) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE user_ID =?");
            statement.setString(1, toDelete.getID().toString());

            var res = statement.execute();
            return res;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public boolean delete(UUID id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE user_ID =?");
            statement.setString(1, id.toString());

            var res = statement.execute();
            return res;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public User GetById(UUID id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE user_ID =?");
            statement.setString(1, id.toString());

            var resultSet = statement.executeQuery();

            resultSet.next();

            User res = new User(UUID.fromString(resultSet.getString("user_id")),
                    resultSet.getString("username"),
                    resultSet.getString("user_password"),
                    resultSet.getInt("coins"),
                    resultSet.getString("description"), resultSet.getInt("elo"));
            return res;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User Add(User toAdd)  {

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (user_ID,username,password,coins,user_description,ELO) VALUES(?,?,?,?,?,?)");
            statement.setString(1, toAdd.getID().toString());
            statement.setString(2, toAdd.getUsername());
            statement.setString(3, toAdd.getPassword());
            statement.setInt(4, toAdd.getCoins());
            statement.setString(5, toAdd.getDescription());
            statement.setInt(6, toAdd.getElo());

            if(statement.execute()) return toAdd;
            return null;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public User Update(User toUpdate) {
        return null;
    }

    public boolean ValidLogin(RegisterAndLoginDTO toVerify)  {

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT username FROM users WHERE username=? AND password=?");
            statement.setString(1, toVerify.Username);
            statement.setString(2, toVerify.Password);

            return statement.executeQuery().next();
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean UpdateCoins(User user, int change) {

        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET coins=? WHERE user_id=?");
            statement.setInt(1, user.getCoins()+change);
            statement.setString(2, user.getID().toString());

            statement.execute();
            return (statement.getUpdateCount() > 0);

        }catch(Exception e)
        {
            return false;
        }

    }

    @Override
    public List<User> GetAll() {
        return null;
    }

    public User GetByUsername(String username) {

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username=?");
            statement.setString(1, username);

            var resultSet = statement.executeQuery();

            resultSet.next();

            User res = new User(UUID.fromString(resultSet.getString("user_id")),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getInt("coins"),
                    resultSet.getString("user_description"), resultSet.getInt("elo"));
            return res;
        } catch (Exception e) {
            return null;
        }

    }
}
