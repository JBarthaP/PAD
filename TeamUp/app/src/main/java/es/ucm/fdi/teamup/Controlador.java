package es.ucm.fdi.teamup;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.Application;
import android.content.Intent;
import android.widget.EditText;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import es.ucm.fdi.teamup.activities.CustomTeamInputActivity;
import es.ucm.fdi.teamup.activities.RandomTeamInputActivity;
import es.ucm.fdi.teamup.database.entities.User;
import es.ucm.fdi.teamup.models.Game;
import es.ucm.fdi.teamup.models.GameTeam;
import es.ucm.fdi.teamup.models.Team;
import es.ucm.fdi.teamup.models.TeamManager;
import es.ucm.fdi.teamup.models.Utils;

public class Controlador extends Application {

    private TeamManager teamManager;

    private Game actualGame;

    private User userLogged;

    public static enum Evento {

    }

    public Controlador() {
        teamManager = new TeamManager();
    }

//    public void tratarEvento(Evento event, Object[] data) {
//        switch(event) {
//            case Evento.GENERARQUIPOS: teamManager.generarEquipos()
//        }
//    }

    public TeamManager getTeamManager() {
        return teamManager;
    }

    public void setTeamManager(TeamManager teamManager) {
        this.teamManager = teamManager;
    }

    public User getUserLogged() {
        return userLogged;
    }

    public void setUserLogged(User userLogged) {
        this.userLogged = userLogged;
    }



    public void deleteTeams(){
        this.teamManager = new TeamManager();
    }


    public void createGame(String name){
        this.actualGame = new Game(name);
    }

    public void startGame(){
        ArrayList<GameTeam> teams = new ArrayList<>();
        for(Team team: teamManager.getTeams()){
            teams.add(new GameTeam(team));
        }
        this.actualGame.setTeams(teams);
    }

    public void finishGame(){
        this.actualGame = null;
    }

    public Game getActualGame(){
        return this.actualGame;
    }

    public void changeTeamNames(ArrayList<String> newNames){
        for(int i = 0; i < this.teamManager.getTeams().size(); i++){
            this.teamManager.getTeams().get(i).setName(newNames.get(i));
        }
    }

}


