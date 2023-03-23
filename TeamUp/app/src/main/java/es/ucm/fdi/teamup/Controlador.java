package es.ucm.fdi.teamup;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;

import es.ucm.fdi.teamup.activities.CustomTeamInputActivity;
import es.ucm.fdi.teamup.activities.RandomTeamInputActivity;
import es.ucm.fdi.teamup.models.TeamManager;

public class Controlador extends Application {

    private TeamManager teamManager;

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

    public void generateCustomTeamsListener(EditText teams_input, EditText members_input){
        int teamNumber = getInputValueAsInt(teams_input);
        int memberNumber = getInputValueAsInt(members_input);
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

    public void generateRandomTeamsListener(EditText teams_input, EditText members_input){
        int teamNumber = getInputValueAsInt(teams_input);
        int memberNumber = getInputValueAsInt(members_input);
        //Log.d(TAG, "" + teamNumber);
        //Caso que los inputs no sean correctos
        if (teamNumber == -1 || memberNumber == -1) {
            return;
            //TODO hacer popup o algo vistoso
        }
        Intent intent = new Intent(this, RandomTeamInputActivity.class);
        intent.putExtra("team_number", teamNumber);
        intent.putExtra("member_number", memberNumber);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private int getInputValueAsInt(EditText input) {
        String inputString = input.getText().toString();
        if (!inputString.equals("") || inputString.equals("0"))
            return Integer.parseInt(inputString);
        return -1;
    }

}
