package es.ucm.fdi.teamup.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import es.ucm.fdi.teamup.Controlador;
import es.ucm.fdi.teamup.R;

public class MainActivity extends AppCompatActivity {

    LinearLayout createTeamsButton;
    LinearLayout createGameButton;
    LinearLayout registerButton;
    LinearLayout settingsButton;

    Controlador controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = (Controlador) getApplication();
        createTeamsButton = findViewById(R.id.createTeamButton);
        createTeamsButton.setOnClickListener((view)->{
            Intent intent = new Intent(this, TeamsActivity.class);
            startActivity(intent);
        });

        createGameButton = findViewById(R.id.createGameButton);
        createGameButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, GameStartActivity.class);
            startActivity(intent);
        });

        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, IntroActivity.class);
            startActivity(intent);
        });

        settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, SelectionTournamentActivity.class);
            startActivity(intent);
        });




    }


}