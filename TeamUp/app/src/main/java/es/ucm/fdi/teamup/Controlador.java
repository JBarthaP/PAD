package es.ucm.fdi.teamup;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.Application;
import android.content.Intent;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import es.ucm.fdi.teamup.activities.CustomTeamInputActivity;
import es.ucm.fdi.teamup.activities.RandomTeamInputActivity;
import es.ucm.fdi.teamup.models.Game;
import es.ucm.fdi.teamup.models.GameTeam;
import es.ucm.fdi.teamup.models.Team;
import es.ucm.fdi.teamup.models.TeamManager;
import es.ucm.fdi.teamup.models.Utils;

public class Controlador extends Application {

    private TeamManager teamManager;

    private Game actualGame;

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

    public void generateCustomTeamsListener(TextInputLayout teams_input, TextInputLayout members_input){


        int teamNumber = Utils.getInputValueAsInt(teams_input);
        int memberNumber =  Utils.getInputValueAsInt(members_input);
        //Log.d(TAG, "" + teamNumber);
        //Caso que los inputs no sean correctos
        if (teamNumber == -1 || memberNumber == -1) {
            return;
            //TODO hacer popup o algo vistoso
        }
        Intent intent = new Intent(this, CustomTeamInputActivity.class);
        intent.putExtra("team_number", teamNumber);
        intent.putExtra("member_number", memberNumber);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void generateRandomTeamsListener(TextInputLayout teams_input, TextInputLayout members_input){


        int teamNumber = Utils.getInputValueAsInt(teams_input);
        int memberNumber =  Utils.getInputValueAsInt(members_input);
        //Log.d(TAG, "" + teamNumber);
        //Caso que los inputs no sean correctos
        if (teamNumber == -1 || memberNumber == -1) {
            return;
            //TODO hacer popup o algo vistoso
        }
        Intent intent = new Intent(this, RandomTeamInputActivity.class);
        intent.putExtra("team_number", teamNumber);
        intent.putExtra("member_number", memberNumber);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK); //FLAG para iniciar actividades desde fuera de una actividad
        startActivity(intent);
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


