package es.ucm.fdi.teamup.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import es.ucm.fdi.teamup.R;

public class GameActivity extends AppCompatActivity {

    Button createAgainButton;
    Button storeGameButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        createAgainButton = findViewById(R.id.createAgainButton);
        storeGameButton = findViewById(R.id.storeGameButton);
        createAgainButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, RandomTeamResultActivity.class);
            startActivity(intent);
        });

        storeGameButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, StoreActivity.class);
            startActivity(intent);
        });
    }
}