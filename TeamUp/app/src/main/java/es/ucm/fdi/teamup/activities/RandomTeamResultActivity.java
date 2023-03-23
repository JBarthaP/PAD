package es.ucm.fdi.teamup.activities;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import es.ucm.fdi.teamup.Controlador;
import es.ucm.fdi.teamup.R;
import es.ucm.fdi.teamup.models.Team;
import es.ucm.fdi.teamup.models.TeamManager;
import es.ucm.fdi.teamup.models.ViewUtils;

public class RandomTeamResultActivity extends AppCompatActivity {

    Intent actualIntent;
    TeamManager teamManager;
    LinearLayout teamsLayout;

    Button reGenerateTeams;

    Controlador controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_team_result);
        controller = (Controlador) getApplication();
        actualIntent = getIntent();
        teamsLayout = findViewById(R.id.custom_teams_input_layout);
        reGenerateTeams = findViewById(R.id.regenerate_random_teams_button);
        teamManager = controller.getTeamManager();


        this.createResultTeamsView();

        reGenerateTeams.setOnClickListener((view) -> {
            teamManager.generateRandomTeams();
            this.createResultTeamsView();
        });

    }

    private void createResultTeamsView() {
        teamsLayout.removeAllViews();
        ArrayList<Team> teams = teamManager.getTeams();
        for (Team team : teams) {
            EditText teamName = ViewUtils.createStyledEditText(this,team.getName());
            LinearLayout linearLayout = ViewUtils.createStyledLinearLayout(this);
            for (String member : team.getMembers()) {
                TextView memberText = ViewUtils.createStyledTextView(this,member);
                linearLayout.addView(memberText);
            }

            teamsLayout.addView(teamName);
            teamsLayout.addView(linearLayout);

        }
    }

}