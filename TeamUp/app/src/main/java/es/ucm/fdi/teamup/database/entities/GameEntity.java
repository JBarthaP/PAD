package es.ucm.fdi.teamup.database.entities;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "games",
        foreignKeys = @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "userId"))
public class GameEntity {

    @PrimaryKey(autoGenerate = true)
    private int gameId;

    @ColumnInfo(name = "game_name")
    @NonNull
    private String game_name;

    @ColumnInfo(name = "date")
    private Date fecha;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private int userId;


    @ColumnInfo(name = "ganador")
    private String ganador;


    private String positionString;

    public GameEntity(@NonNull String game_name, Date fecha, String positionString, int userId) {
        this.game_name = game_name;
        this.fecha = fecha;
        this.positionString = positionString;
        this.userId = userId;
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

    public String getPositionString() {
        return positionString;
    }

    public void setPositionString(String positionString) {
        this.positionString = positionString;
    }
}
