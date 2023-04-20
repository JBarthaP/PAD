package es.ucm.fdi.teamup.activities;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

import es.ucm.fdi.teamup.Controlador;
import es.ucm.fdi.teamup.R;
import es.ucm.fdi.teamup.models.TeamManager;
import es.ucm.fdi.teamup.models.ViewUtils;

public class RandomTeamInputActivity extends AppCompatActivity {

    Intent actualIntent;
    LinearLayout membersLayout;

    Button generateButton;

    ArrayList<EditText> allMemberInputs = new ArrayList<>();

    Controlador controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_team_input);
        controller = (Controlador) getApplication();
        actualIntent = getIntent();
        membersLayout = findViewById(R.id.custom_teams_input_layout);
        generateButton = findViewById(R.id.regenerate_random_teams_button);
        int team_number = actualIntent.getIntExtra("team_number", 0);
        int member_number = actualIntent.getIntExtra("member_number", 0);
        for (int i = 0; i < member_number; i++) {
            EditText inputText = ViewUtils.createStyledEditText(this, "", (e)->{
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) e.getLayoutParams();
                params.setMargins(0,20,0,20);
                e.setLayoutParams(params);
                e.setPadding(30,30,30,30);
                e.setBackground(ViewUtils.createBorder(2, Color.BLACK, (element)->{
                    element.setCornerRadius(16);
                }));
            });
            membersLayout.addView(inputText);
            allMemberInputs.add(inputText);
        }
        generateButton.setOnClickListener((view -> {
            for (EditText input : allMemberInputs) {
                if (input.getText().toString().equals("")) return; //TODO controlar mejor los inputs
            }
            TeamManager teamManager = controller.getTeamManager();
            teamManager.setnTeams(team_number);
            for (EditText input : allMemberInputs) {
                teamManager.addMember(input.getText().toString());
            }
            teamManager.generateRandomTeams();
            Intent intent = new Intent(this, RandomTeamResultActivity.class);
            startActivity(intent);
        }));
    }
}