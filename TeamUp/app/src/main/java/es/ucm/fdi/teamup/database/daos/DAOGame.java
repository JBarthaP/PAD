package es.ucm.fdi.teamup.database.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.ucm.fdi.teamup.database.entities.GameEntity;
import es.ucm.fdi.teamup.database.entities.User;

@Dao
public interface DAOGame {
    @Query("SELECT * FROM games")
    List<GameEntity> getAll();

    @Query("SELECT * FROM games WHERE  userId = :userId")
    List<GameEntity> getGamesByUser(int userId);

    @Insert
    void insertGame(GameEntity game);

    @Update
    void update(GameEntity game);

    @Delete
    void delete(GameEntity game);
}
