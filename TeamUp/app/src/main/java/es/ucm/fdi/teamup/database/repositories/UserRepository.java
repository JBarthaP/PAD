package es.ucm.fdi.teamup.database.repositories;

import java.util.List;

import es.ucm.fdi.teamup.database.entities.User;

public interface UserRepository {

    List<User> getAllUsers();

    User findUserById(int userId);

    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}
