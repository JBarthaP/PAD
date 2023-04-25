package es.ucm.fdi.teamup.database.repositories;

import java.util.List;

import es.ucm.fdi.teamup.database.daos.DAOGame;
import es.ucm.fdi.teamup.database.entities.GameEntity;

public class GameRepositoryImp implements GameRepository {

    private DAOGame daoGame;

    public GameRepositoryImp(DAOGame daoGame) {
        this.daoGame = daoGame;
    }

    @Override
    public List<GameEntity> getAllGameEntitys() {
        return daoGame.getAll();
    }

    @Override
    public List<GameEntity> getGamesByUser(int id) {
        return daoGame.getGamesByUser(id);
    }


    @Override
    public void insertGameEntity(GameEntity GameEntity) {
        daoGame.insertGame(GameEntity);
    }

    @Override
    public void updateGameEntity(GameEntity GameEntity) {
        daoGame.update(GameEntity);
    }

    @Override
    public void deleteGameEntity(GameEntity GameEntity) {
        daoGame.delete(GameEntity);
    }
}
