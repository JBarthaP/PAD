package es.ucm.fdi.teamup.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import es.ucm.fdi.teamup.R;
import es.ucm.fdi.teamup.models.Team;
import es.ucm.fdi.teamup.models.TeamManager;
import es.ucm.fdi.teamup.models.ViewUtils;
import kotlin.Triple;

public class CustomTeamInputActivity extends AppCompatActivity {

    private Intent actualIntent;
    private LinearLayout teamsLayout;

    private int team_number;
    private  int member_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_team_input);
        actualIntent = getIntent();

        teamsLayout = findViewById(R.id.custom_teams_input_layout);

        team_number = actualIntent.getIntExtra("team_number", 0);
        member_number = actualIntent.getIntExtra("member_number", 0);
        this.createInputTeamsView();

    }


    private void createInputTeamsView() {
        teamsLayout.removeAllViews();

        Triple<Integer,Integer,Integer> teamSize = TeamManager.getTeamsSize(team_number, member_number);
        int membersPerGroup = teamSize.getFirst();
        int bigTeams = teamSize.getSecond();
        int smallTeams = teamSize.getThird();


        for (int i = 0; i < bigTeams; i++) {
            EditText teamName = ViewUtils.createStyledEditText(this,"Team " + (i+1));
            LinearLayout linearLayout = ViewUtils.createStyledLinearLayout(this);

            for (int j = 0; j < membersPerGroup; j++) {
                EditText memberText = ViewUtils.createStyledEditText(this, "Member " + (j+1));
                linearLayout.addView(memberText);
            }
            teamsLayout.addView(teamName);
            teamsLayout.addView(linearLayout);
        }
        for (int i = 0; i < smallTeams; i++) {
            EditText teamName = ViewUtils.createStyledEditText(this,"Team " + (i+bigTeams+1));
            LinearLayout linearLayout = ViewUtils.createStyledLinearLayout(this);

            for (int j = 0; j < membersPerGroup-1; j++) {
                EditText memberText = ViewUtils.createStyledEditText(this, "Member " + (j+1));
                linearLayout.addView(memberText);
            }
            teamsLayout.addView(teamName);
            teamsLayout.addView(linearLayout);
        }

    }

}