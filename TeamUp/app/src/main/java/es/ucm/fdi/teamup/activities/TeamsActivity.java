package es.ucm.fdi.teamup.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import es.ucm.fdi.teamup.R;

public class TeamsActivity extends AppCompatActivity {

    private static final String TAG = TeamsActivity.class.getSimpleName();
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
        generate_random.setOnClickListener((view) -> {
            int teamNumber = getInputValueAsInt(teams_input);
            int memberNumber = getInputValueAsInt(members_input);
            Log.d(TAG, "" + teamNumber);
            //Caso que los inputs no sean correctos
            if (teamNumber == -1 || memberNumber == -1) {
                //TODO hacer popup o algo vistoso
            }
            Intent intent = new Intent(this, RandomTeamInputActivity.class);
            intent.putExtra("team_number", teamNumber);
            intent.putExtra("member_number", memberNumber);
            startActivity(intent);
        });

    }

    private int getInputValueAsInt(EditText input) {
        String inputString = input.getText().toString();
        if (!inputString.equals("") || inputString.equals("0"))
            return Integer.parseInt(inputString);
        return -1;
    }
}