package es.ucm.fdi.teamup.database.repositories;

import java.util.List;

import es.ucm.fdi.teamup.database.entities.GameEntity;

public interface GameRepository {

    List<GameEntity> getAllGameEntitys();

    void insertGameEntity(GameEntity GameEntity);

    void updateGameEntity(GameEntity GameEntity);

    void deleteGameEntity(GameEntity GameEntity);
}
