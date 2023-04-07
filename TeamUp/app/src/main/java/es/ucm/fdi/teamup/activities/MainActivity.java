package es.ucm.fdi.teamup.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import es.ucm.fdi.teamup.Controlador;
import es.ucm.fdi.teamup.R;

public class MainActivity extends AppCompatActivity {

    Button createTeamsButton;

    Controlador controller;
    Button createGameButton;

    Button registerButton;
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
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });




    }


}