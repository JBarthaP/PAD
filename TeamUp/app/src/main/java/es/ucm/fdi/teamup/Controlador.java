package es.ucm.fdi.teamup;

import android.app.Application;

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
}
