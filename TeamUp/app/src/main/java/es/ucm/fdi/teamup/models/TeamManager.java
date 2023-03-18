package es.ucm.fdi.teamup.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class TeamManager implements Serializable {
    private ArrayList<String> members;

    private int nTeams;
    private ArrayList<Team> teams;

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

        int membersPerGroup = (int) Math.ceil((double) members.size() / (double) this.nTeams);
        int bigTeams = this.nTeams;
        int smallTeams = 0;

        //Dependiendo si el número de equipos es impar habrá que hacer equipos con números diferentes
        if (members.size() % this.nTeams != 0) {
            bigTeams = this.members.size() - ((membersPerGroup - 1) * this.nTeams);
            smallTeams = this.nTeams - bigTeams;
        }
        teams = new ArrayList<>();
        Random rand = new Random();
        ArrayList<String> membersBag = new ArrayList<>(this.members);
        System.out.println(membersBag.size());
        System.out.println(membersPerGroup);
        System.out.println(this.nTeams);
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

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public int getnTeams() {
        return nTeams;
    }
}

