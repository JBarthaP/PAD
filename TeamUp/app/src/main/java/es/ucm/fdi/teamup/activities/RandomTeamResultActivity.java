package es.ucm.fdi.teamup.activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

    Resources res;
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
        res = getResources();
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



    private void createResultTeamsView(){
        teamsLayout.removeAllViews();
        teamNameInputs = new ArrayList<>();
        ArrayList<Team> teams = teamManager.getTeams();
        for (Team team : teams) {

            LinearLayout layout = ViewUtils.createStyledLinearLayout(this, (e)->{
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0,50,0,0);
                e.setLayoutParams(params);
                e.setBackground(ViewUtils.createBorder(4, Color.BLACK, (element)->{
                    element.setCornerRadius(16f);
                }));
                e.setPadding(4,4,4,4);
            });
            EditText teamName = ViewUtils.createStyledEditText(this, team.getName(),(e)->{
                e.setHeight(70);
                e.setTextSize(20);
                e.setBackgroundColor(res.getColor(R.color.orange));
                e.setTextColor(res.getColor(R.color.white));
                e.setClipToOutline(true);
                e.setPadding(20,0,0,0);
                e.setBackground(ViewUtils.createBorder(0, Color.BLACK, (element)->{
                    element.setCornerRadii(new float[]{16, 16, 16, 16, 0, 0, 0, 0});
                    element.setColor(res.getColor(R.color.orange));
                }));
            });
            layout.addView(teamName);
            teamNameInputs.add(teamName);

            for(String member: team.getMembers()){
                layout.addView(ViewUtils.createSeparator(this, 2));
                layout.addView(ViewUtils.createStyledTextView(this, member, (e)->{
                    e.setPadding(20,0,0,0);
                    e.setTextSize(18);
                }));
            }
            CardView card = ViewUtils.createStyledCardView(this, (e)->{});
            card.addView(layout);
            teamsLayout.addView(card);
        }

    }


}