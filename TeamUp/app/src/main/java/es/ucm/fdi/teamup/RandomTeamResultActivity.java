package es.ucm.fdi.teamup;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RandomTeamResultActivity extends AppCompatActivity {

    Intent actualIntent;
    TeamManager teamManager;
    LinearLayout teamsLayout;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_team_result);
        actualIntent = getIntent();
        teamsLayout = findViewById(R.id.generated_random_teams_layout);

        teamManager = (TeamManager) actualIntent.getSerializableExtra("teamManager");

        ArrayList<Team> teams = teamManager.getTeams();


        for(Team team:teams){
            EditText teamName = new EditText(this);
            teamName.setText(team.getName());
            teamName.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            teamName.setTextColor(Color.BLACK);
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(20,0,0,0);
            linearLayout.setLayoutParams(params);
            for(String member:team.getMembers()){
                TextView memberText = new TextView(this);
                memberText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                memberText.setText(member);
                linearLayout.addView(memberText);
            }

            teamsLayout.addView(teamName);
            teamsLayout.addView(linearLayout);

        }

    }
}