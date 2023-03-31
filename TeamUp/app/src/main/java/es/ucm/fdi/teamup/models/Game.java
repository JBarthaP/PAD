package es.ucm.fdi.teamup.models;

import java.util.ArrayList;

public class Game {
    private ArrayList<GameTeam> teams;

    private String name;

    public Game(){}

    public Game(String name){
        this.name = name;
    }

    public ArrayList<GameTeam> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<GameTeam> teams) {
        this.teams = teams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
