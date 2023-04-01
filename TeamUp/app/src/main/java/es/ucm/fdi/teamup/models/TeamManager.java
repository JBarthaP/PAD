package es.ucm.fdi.teamup.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import kotlin.Triple;

public class TeamManager {
    private ArrayList<String> members;

    private int nTeams;
    private ArrayList<Team> teams;


    public static Triple<Integer, Integer, Integer> getTeamsSize(int nTeams, int nMembers){
        int membersPerGroup = (int) Math.ceil((double) nMembers / (double) nTeams);
        int bigTeams = nTeams;
        int smallTeams = 0;

        //Dependiendo si el número de equipos es impar habrá que hacer equipos con números diferentes
        if (nMembers % nTeams != 0) {
            bigTeams = nMembers - ((membersPerGroup - 1) * nTeams);
            smallTeams = nTeams - bigTeams;
        }
        return new Triple(membersPerGroup,bigTeams,smallTeams);
    }
    public TeamManager() {
        this.members = new ArrayList<>();
        this.nTeams = 0;
    }

    public TeamManager(int nTeams) {
        this.members = new ArrayList<>();
        this.nTeams = nTeams;
    }

    public TeamManager(ArrayList<String> members) {
        this.members = members;
    }

    public void addMember(String member) {
        members.add(member);
    }

    public void generateRandomTeams() {

        Triple<Integer, Integer, Integer> teamSize = TeamManager.getTeamsSize(this.nTeams, this.members.size());
        int membersPerGroup = teamSize.getFirst();
        int bigTeams = teamSize.getSecond();
        int smallTeams = teamSize.getThird();

        teams = new ArrayList<>();
        Random rand = new Random();
        ArrayList<String> membersBag = new ArrayList<>(this.members);

        for (int i = 0; i < bigTeams; i++) {
            ArrayList<String> team = new ArrayList<>();
            for (int j = 0; j < membersPerGroup; j++) {
                int index = rand.nextInt(membersBag.size());
                team.add(membersBag.get(index));
                membersBag.remove(index);
            }
            this.teams.add(new Team("Team " + (i + 1), team));
        }
        for (int i = 0; i < smallTeams; i++) {
            ArrayList<String> team = new ArrayList<>();
            for (int j = 0; j < (membersPerGroup - 1); j++) {
                int index = rand.nextInt(membersBag.size());
                team.add(membersBag.get(index));
                membersBag.remove(index);
            }
            this.teams.add(new Team("Team " + (i + bigTeams + 1), team));
        }
    }


    public void addTeam(Team team){
        teams.add(team);
    }

    public ArrayList<String> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<String> members) {
        this.members = members;
    }

    public void setnTeams(int nTeams) {
        this.nTeams = nTeams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public int getnTeams() {
        return nTeams;
    }
}

