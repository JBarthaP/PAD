package es.ucm.fdi.teamup.database.repositories;

import java.util.List;

import es.ucm.fdi.teamup.database.daos.DAOUser;
import es.ucm.fdi.teamup.database.entities.User;

public class UserRepositoryImp implements UserRepository{

    private DAOUser daoUser;

    public UserRepositoryImp(DAOUser daoUser) {
        this.daoUser = daoUser;
    }

    @Override
    public List<User> getAllUsers() {
        return daoUser.getAll();
    }

    @Override
    public User findUserById(int userId) {
        return daoUser.findById(userId);
    }

    @Override
    public User findUserByNameAndPassword(String name, String password) {
        return daoUser.findByNameAndPassword(name,password);
    }

    @Override
    public void insertUser(User user) {
        daoUser.insertUser(user);
    }

    @Override
    public void updateUser(User user) {
        daoUser.update(user);

    }

    @Override
    public void deleteUser(User user) {
        daoUser.delete(user);
    }
}
