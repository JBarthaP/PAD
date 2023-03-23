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


    }


}