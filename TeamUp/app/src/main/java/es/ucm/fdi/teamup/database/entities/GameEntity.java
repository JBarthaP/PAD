package es.ucm.fdi.teamup.database.entities;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "games")
public class GameEntity {

    @PrimaryKey(autoGenerate = true)
    private int gameId;

    @ColumnInfo(name = "game_name")
    @NonNull
    private String game_name;

    @ColumnInfo(name = "date")
    private Date fecha;

    @ColumnInfo(name = "ganador")
    private String ganador;

    public GameEntity(@NonNull String game_name, Date fecha, String ganador) {
        this.game_name = game_name;
        this.fecha = fecha;
        this.ganador = ganador;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    @NonNull
    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(@NonNull String game_name) {
        this.game_name = game_name;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }
}
