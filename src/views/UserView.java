package views;

import java.util.ArrayList;
import java.util.Scanner;

import controllers.UserController;
import models.User;

public class UserView {
    private UserController userController;
    private Scanner s;

    public UserView(UserController userController) {
        this.userController = userController;
        s = new Scanner(System.in);
    }

    public void showMenu() {
        System.out.println("1. Display All");
        System.out.println("2. Insert Data");
        System.out.println("3. Update Data");
        System.out.println("4. Delete Data");
        System.out.print(">> ");
        
        userController.handleMenu(s.nextLine());
    }

    public void displayUsers(ArrayList<User> users) {
        System.out.println("Current Users: ");
        for (User user : users) {
            System.out.println(user.getId() + " " + user.getName() + " " + user.getAuthor());
        }
    }

    public void insertUser() {
        String name, author;
        System.out.print("Input Name: ");
        name = s.nextLine();

        System.out.print("Input Author: ");
        author = s.nextLine();

        userController.insertUser(name, author);
    }

    public void updateUser() {
        Integer id;
        String name, author;
        System.out.print("ID: ");
        id = s.nextInt(); s.nextLine();

        System.out.print("Name: ");
        name = s.nextLine();

        System.out.print("Author: ");
        author = s.nextLine();

        userController.updateUser(id, name, author);
    }

    public void deleteUser() {
        Integer id;
        System.out.print("Input ID: ");
        id = s.nextInt(); s.nextLine();

        userController.removeUser(id);
    }
}
