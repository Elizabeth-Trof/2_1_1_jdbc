package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("Tom", "Ivanov", (byte) 12);
        User user2 = new User("Ivan", "Petrov", (byte) 24);
        User user3 = new User("Lena", "Sidorova", (byte) 55);

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
