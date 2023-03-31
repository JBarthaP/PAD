package es.ucm.fdi.teamup.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import es.ucm.fdi.teamup.Controlador;
import es.ucm.fdi.teamup.R;
import es.ucm.fdi.teamup.models.Utils;

public class GameStartActivity extends AppCompatActivity {

    private Controlador controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);
        controller = (Controlador) getApplication();
        TextInputLayout gameName = findViewById(R.id.gameNameInput);
        String name = "Partida: " + Utils.getActualDateString();
        gameName.getEditText().setText(name);
        Button continueButton = findViewById(R.id.continueToTeamsButton);

        continueButton.setOnClickListener((view)->{
            String resultName = Utils.getInputValueAsString(gameName);
            if(name.equals("")) return;
            controller.createGame(resultName);
            Intent intent = new Intent(this, TeamsActivity.class);
            startActivity(intent);
        });
    }



}