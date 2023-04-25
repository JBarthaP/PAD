package es.ucm.fdi.teamup.models;

public class GameTeam {
    private Team team;
    private int position;

    public GameTeam(Team team) {
        this.team = team;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getPosition() {
        return position;
    }
}
