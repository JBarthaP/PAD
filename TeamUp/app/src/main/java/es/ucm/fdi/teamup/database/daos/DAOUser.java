package es.ucm.fdi.teamup.database.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.ucm.fdi.teamup.database.entities.User;

@Dao
public interface DAOUser {
    @Query("SELECT * FROM users")
    List<User> getAll();

    @Query("SELECT * FROM users WHERE userId IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM users WHERE  userId = :userId")
    User findById(int userId);

    @Query("SELECT * FROM users WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Query("SELECT * FROM users WHERE username LIKE :name AND " +
            "password LIKE :password LIMIT 1")
    User findByNameAndPassword(String name, String password);

    @Insert
    void insertAll(User... users);

    @Insert
    void insertUser(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);
}

