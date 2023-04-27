package es.ucm.fdi.teamup.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

import es.ucm.fdi.teamup.Controlador;
import es.ucm.fdi.teamup.R;
import es.ucm.fdi.teamup.database.entities.User;

public class SettingsActivity extends AppCompatActivity {

    private static final String TAG = GameStartActivity.class.getSimpleName();

    Spinner language_dropdown;

    Button change_language;

    Button logout;
    Controlador controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        language_dropdown = findViewById(R.id.language_spinner);
        change_language = findViewById(R.id.change_language);
        logout = findViewById(R.id.log_out);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.languages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        language_dropdown.setAdapter(adapter);
        controller = (Controlador) getApplication();

        change_language.setOnClickListener(view -> {
            String language_choose = "";
            switch (language_dropdown.getSelectedItemPosition()) {
                case 0: {
                    language_choose = "es";
                }
                break;
                case 1: {
                    language_choose = "en";
                }
                break;
            }
            setLocale(language_choose);
            finish();
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        logout.setOnClickListener((view -> {
            controller.setUserLogged(null);
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }));

        controller = (Controlador) getApplication();
        User user = controller.getUserLogged();
        TextView username = findViewById(R.id.username);

        if (user == null) {
            logout.setEnabled(false);
            Log.d(TAG, "El usuario no esta logueado");
        } else {
            Log.d(TAG, user.getUsername());
            username.setText(user.getUsername());
        }


    }

    private void setLocale(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources resources = getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, metrics);

    }
}