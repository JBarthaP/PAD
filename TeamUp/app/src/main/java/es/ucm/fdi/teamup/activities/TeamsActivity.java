package es.ucm.fdi.teamup.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import es.ucm.fdi.teamup.Controlador;
import es.ucm.fdi.teamup.R;

public class TeamsActivity extends AppCompatActivity {

    private static final String TAG = TeamsActivity.class.getSimpleName();
    EditText members_input;
    EditText teams_input;

    Button generate_custom;

    Button generate_random;

    Controlador controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = (Controlador) getApplication();
        setContentView(R.layout.activity_teams);
        members_input = findViewById(R.id.total_members_input);
        teams_input = findViewById(R.id.team_number_input);
        generate_custom = findViewById(R.id.generate_custom_teams_button);
        generate_random = findViewById(R.id.generate_random_teams_button);


        generate_random.setOnClickListener((view) -> {
            controller.generateRandomTeamsListener(teams_input,members_input);
        });

        generate_custom.setOnClickListener((view -> {
            controller.generateCustomTeamsListener(teams_input,members_input);
        }));

    }



}