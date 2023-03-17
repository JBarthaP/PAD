package es.ucm.fdi.teamup;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class RandomTeamInputActivity extends AppCompatActivity {

    Intent actualIntent;
    LinearLayout membersLayout;

    Button generateButton;

    ArrayList<EditText> allMemberInputs = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_team_input);
        actualIntent = getIntent();
        membersLayout = findViewById(R.id.generated_random_teams_layout);
        generateButton = findViewById(R.id.generate_random_teams_confirmation);
        int team_number = actualIntent.getIntExtra("team_number", 0);
        int member_number = actualIntent.getIntExtra("member_number", 0);
        for(int i = 0; i<member_number; i++){
            EditText inputText = new EditText(this);
            inputText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            membersLayout.addView(inputText);
            allMemberInputs.add(inputText);
        }
        generateButton.setOnClickListener((view->{

            for (EditText input:allMemberInputs){
                if(input.getText().toString().equals("")) return;
            }
            TeamManager teamManager = new TeamManager(team_number);
            for (EditText input:allMemberInputs){
                teamManager.addMember(input.getText().toString());
            }
            teamManager.generateRandomTeams();

            Intent intent = new Intent(this, RandomTeamResultActivity.class);
            intent.putExtra("teamManager", teamManager);
            startActivity(intent);
        }));
    }
}