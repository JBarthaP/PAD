package es.ucm.fdi.teamup.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Team implements Serializable {
    String name;
    ArrayList<String> members;

    public Team(String name, ArrayList<String> members) {
        this.name = name;
        this.members = members;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getMembers() {
        return members;
    }


}
