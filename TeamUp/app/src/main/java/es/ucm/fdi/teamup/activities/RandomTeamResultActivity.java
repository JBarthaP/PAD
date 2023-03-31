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
import java.util.stream.Collectors;

import es.ucm.fdi.teamup.Controlador;
import es.ucm.fdi.teamup.R;
import es.ucm.fdi.teamup.models.GameTeam;
import es.ucm.fdi.teamup.models.Team;
import es.ucm.fdi.teamup.models.TeamManager;
import es.ucm.fdi.teamup.models.ViewUtils;

public class RandomTeamResultActivity extends AppCompatActivity {

    Intent actualIntent;
    TeamManager teamManager;
    LinearLayout teamsLayout;

    Button reGenerateTeams;

    Button finishTeams;
    Controlador controller;

    ArrayList<EditText> teamNameInputs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_team_result);
        controller = (Controlador) getApplication();
        actualIntent = getIntent();
        teamNameInputs = new ArrayList<>();
        teamsLayout = findViewById(R.id.custom_teams_input_layout);
        reGenerateTeams = findViewById(R.id.regenerate_random_teams_button);
        teamManager = controller.getTeamManager();
        finishTeams = findViewById(R.id.finish_random_teams);

        this.createResultTeamsView();

        reGenerateTeams.setOnClickListener((view) -> {
            teamManager.generateRandomTeams();
            this.createResultTeamsView();
        });

        finishTeams.setOnClickListener((view -> {
            ArrayList<String> names = new ArrayList<>();
            for(EditText t: teamNameInputs){
                String name = t.getText().toString();
                if(name.equals("")) return;
                names.add(name);
            }
            controller.changeTeamNames(names);
            controller.startGame();
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        }));

    }

    private void createResultTeamsView() {
        teamsLayout.removeAllViews();
        ArrayList<Team> teams = teamManager.getTeams();
        for (Team team : teams) {
            EditText teamName = ViewUtils.createStyledEditText(this,team.getName(), (e)->{});
            LinearLayout linearLayout = ViewUtils.createStyledLinearLayout(this,(e)->{});
            for (String member : team.getMembers()) {
                TextView memberText = ViewUtils.createStyledTextView(this,member,(e)->{});
                linearLayout.addView(memberText);
            }
            teamNameInputs.add(teamName);
            teamsLayout.addView(teamName);
            teamsLayout.addView(linearLayout);

        }
    }

}