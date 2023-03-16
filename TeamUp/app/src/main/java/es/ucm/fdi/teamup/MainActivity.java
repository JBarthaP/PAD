package es.ucm.fdi.teamup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button createTeamsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createTeamsButton = findViewById(R.id.createTeamButton);
        createTeamsButton.setOnClickListener((view)->{
            Intent intent = new Intent(this, TeamsActivity.class);
            startActivity(intent);
        });


    }


}