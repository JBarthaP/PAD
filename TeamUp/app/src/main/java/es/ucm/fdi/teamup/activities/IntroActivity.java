package es.ucm.fdi.teamup.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import es.ucm.fdi.teamup.R;

public class IntroActivity extends AppCompatActivity {

    private AppCompatButton login_button;

    private AppCompatButton register_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        login_button = findViewById(R.id.login_button);

        register_button = findViewById(R.id.register_button);

        login_button.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        register_button.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

    }
}