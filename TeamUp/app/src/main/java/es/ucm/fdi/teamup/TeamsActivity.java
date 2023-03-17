package es.ucm.fdi.teamup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class TeamsActivity extends AppCompatActivity {

    EditText members_input;
    EditText teams_input;

    Button generate_custom;

    Button generate_random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        members_input = findViewById(R.id.total_members_input);
        teams_input = findViewById(R.id.team_number_input);
        generate_custom = findViewById(R.id.generate_custom_teams_button);
        generate_random = findViewById(R.id.generate_random_teams_button);
        generate_random.setOnClickListener((view)->{
            Intent intent = new Intent(this, RandomTeamInputActivity.class);
            int teamNumber = getInputValueAsInt(teams_input);
            int memberNumber = getInputValueAsInt(members_input);
            if(teamNumber == -1 || memberNumber == -1) return;
            intent.putExtra("team_number", teamNumber);
            intent.putExtra("member_number", memberNumber);
            startActivity(intent);
        });

    }

    private int getInputValueAsInt(EditText input){
        String inputString = input.getText().toString();
        if(!inputString.equals(""))
            return Integer.parseInt(inputString);
        return -1;
    }
}