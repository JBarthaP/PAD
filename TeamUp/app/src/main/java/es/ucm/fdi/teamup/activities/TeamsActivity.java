package es.ucm.fdi.teamup.activities;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.google.android.material.textfield.TextInputLayout;

import es.ucm.fdi.teamup.Controlador;
import es.ucm.fdi.teamup.R;
import es.ucm.fdi.teamup.models.Utils;

public class TeamsActivity extends AppCompatActivity {

    private static final String TAG = TeamsActivity.class.getSimpleName();
    TextInputLayout members_input;
    TextInputLayout teams_input;

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
            generateRandomTeamsListener(teams_input,members_input);
        });

        generate_custom.setOnClickListener((view -> {
            generateCustomTeamsListener(teams_input,members_input);
        }));

    }

    public void generateCustomTeamsListener(TextInputLayout teams_input, TextInputLayout members_input){


        int teamNumber = Utils.getInputValueAsInt(teams_input);
        int memberNumber =  Utils.getInputValueAsInt(members_input);
        //Log.d(TAG, "" + teamNumber);
        //Caso que los inputs no sean correctos
        if (teamNumber == -1 || memberNumber == -1) {
            if(memberNumber == -1){
                TapTargetView.showFor(this, TapTarget.forView(members_input, "Informacion faltante", "Por favor rellene la información necesaria"));
            }
            else{
                TapTargetView.showFor(this, TapTarget.forView(teams_input, "Informacion faltante", "Por favor rellene la información necesaria"));
            }
            return;
            }
        Intent intent = new Intent(this, CustomTeamInputActivity.class);
        intent.putExtra("team_number", teamNumber);
        intent.putExtra("member_number", memberNumber);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void generateRandomTeamsListener(TextInputLayout teams_input, TextInputLayout members_input){


        int teamNumber = Utils.getInputValueAsInt(teams_input);
        int memberNumber =  Utils.getInputValueAsInt(members_input);
        //Log.d(TAG, "" + teamNumber);
        //Caso que los inputs no sean correctos
        if (teamNumber == -1 || memberNumber == -1) {
            if(memberNumber == -1){
                TapTargetView.showFor(this, TapTarget.forView(members_input, "Informacion faltante", "Por favor rellene la información necesaria"));
            }
            else{
                TapTargetView.showFor(this, TapTarget.forView(teams_input, "Informacion faltante", "Por favor rellene la información necesaria"));
            }
            return;
            //TODO hacer popup o algo vistoso
        }
        Intent intent = new Intent(this, RandomTeamInputActivity.class);
        intent.putExtra("team_number", teamNumber);
        intent.putExtra("member_number", memberNumber);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK); //FLAG para iniciar actividades desde fuera de una actividad
        startActivity(intent);
    }


}