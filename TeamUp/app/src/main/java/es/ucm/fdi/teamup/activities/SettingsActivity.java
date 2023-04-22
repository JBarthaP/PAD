package es.ucm.fdi.teamup.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Locale;

import es.ucm.fdi.teamup.R;

public class SettingsActivity extends AppCompatActivity {

    Spinner language_dropdown;

    Button change_language;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        language_dropdown = findViewById(R.id.language_spinner);
        change_language = findViewById(R.id.change_language);
        ArrayAdapter<CharSequence>adapter= ArrayAdapter.createFromResource(this, R.array.languages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        language_dropdown.setAdapter(adapter);

        change_language.setOnClickListener(view -> {
            String language_choose = "";
            switch(language_dropdown.getSelectedItemPosition()) {
                case 0: {
                    language_choose = "es";
                }break;
                case 1: {
                    language_choose = "en";
                }break;
            }
            setLocale(language_choose);
            finish();
            startActivity(getIntent());
        });



    }
    private void setLocale(String language) {
        Locale locale= new Locale(language);
        Locale.setDefault(locale);
        Resources resources = getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, metrics);

    }
}