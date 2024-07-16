package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.User;
import utils.Connect;
import views.UserView;

public class UserController {
    private UserView userView;
    private Connect conn;

    public UserController() {
        this.userView = new UserView(this);
        conn = Connect.getInstance();
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();

        String query = "SELECT * FROM users";

        try {
            PreparedStatement statement = conn.getConn().prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Integer id = result.getInt(1);
                String name = result.getString(2);
                String email = result.getString(3);
                String password = result.getString(4);
                String dob = result.getString(5);
                users.add(new User(id, name, email, password, null	));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void insertUser(String name, String author) {
        String query = "INSERT INTO users (name, author) VALUES (?, ?)";

        try {
            PreparedStatement statement = conn.getConn().prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, author);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Integer id, String name, String author) {
        String query = "UPDATE users SET name = ?, author = ? WHERE id = ?";

        try {
            PreparedStatement stmt = conn.getConn().prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, author);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(Integer id) {
        String query = "DELETE FROM users WHERE id = ?";

        try {
            PreparedStatement stmt = conn.getConn().prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void navigateToMenu() {
        userView.showMenu();
    }

    public void handleMenu(String choice) {
        switch (choice) {
            case "1":
                userView.displayUsers(getUsers());
                break;
            case "2":
                userView.insertUser();
                break;
            case "3":
                userView.displayUsers(getUsers());
                userView.updateUser();
                break;
            case "4":
                userView.displayUsers(getUsers());
                userView.deleteUser();
                break;
            default:
                break;
        }
    }
}
