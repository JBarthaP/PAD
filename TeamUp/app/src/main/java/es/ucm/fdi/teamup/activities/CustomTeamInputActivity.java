package es.ucm.fdi.teamup.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import es.ucm.fdi.teamup.Controlador;
import es.ucm.fdi.teamup.R;
import es.ucm.fdi.teamup.models.Team;
import es.ucm.fdi.teamup.models.TeamManager;
import es.ucm.fdi.teamup.models.ViewUtils;
import kotlin.Triple;


public class CustomTeamInputActivity extends AppCompatActivity {


    private Intent actualIntent;
    private LinearLayout teamsLayout;

    Controlador controller;
    ArrayList<TextInputLayout> teamNamesInput;
    ArrayList<ArrayList<TextInputLayout>> memberNamesInput;
    private Button continue_button;

    private TeamManager teamManager;


    private int team_number;
    private  int member_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_team_input);
        controller = (Controlador) getApplication();
        actualIntent = getIntent();
        teamManager = controller.getTeamManager();
        teamsLayout = findViewById(R.id.custom_teams_input_layout);
        continue_button = findViewById(R.id.continue_button);
        teamNamesInput = new ArrayList<>();
        memberNamesInput = new ArrayList<>();
        team_number = actualIntent.getIntExtra("team_number", 0);
        member_number = actualIntent.getIntExtra("member_number", 0);
        this.createInputTeamsView();

        continue_button.setOnClickListener(view -> {

        });
    }


    private void createInputTeamsView() {
        teamsLayout.removeAllViews();

        Triple<Integer,Integer,Integer> teamSize = TeamManager.getTeamsSize(team_number, member_number);
        int membersPerGroup = teamSize.getFirst();
        int bigTeams = teamSize.getSecond();
        int smallTeams = teamSize.getThird();
        teamNamesInput = new ArrayList<>();
        memberNamesInput = new ArrayList<>();

        //En caso de que haya equipos pequeños sobrantes, no se repita el número de equipo
        int last = 0;

        for (int i = 0; i < bigTeams; i++) {
            //EditText teamName = ViewUtils.createStyledEditText(this,"Team " + (i+1), (e)->{});
            TextInputLayout teamName= ViewUtils.createStyledTextInputLayout(this,"Team " + (i+1), (e)->{});
            teamNamesInput.add(teamName);
            ArrayList<TextInputLayout> teamMembersInput = new ArrayList<>();
            LinearLayout linearLayout = ViewUtils.createStyledLinearLayout(this, (e)->{});
            for (int j = 0; j < membersPerGroup; j++) {
                TextInputLayout memberText = ViewUtils.createStyledTextInputLayout(this, "Member " + (j+1), (e)->{});
                teamMembersInput.add(memberText);
                linearLayout.addView(memberText);
            }
            memberNamesInput.add(teamMembersInput);
            teamsLayout.addView(teamName);
            teamsLayout.addView(linearLayout);
            last = i;
        }
        last++;
        for (int i = last; i < smallTeams+last; i++) {
            //EditText teamName = ViewUtils.createStyledEditText(this,"Team " + (i+bigTeams+1), (e)->{});
            TextInputLayout teamName = ViewUtils.createStyledTextInputLayout(this,"Team " + (i+1), (e)->{});
            LinearLayout linearLayout = ViewUtils.createStyledLinearLayout(this, (e)->{});

            for (int j = 0; j < membersPerGroup-1; j++) {
                TextInputLayout memberText = ViewUtils.createStyledTextInputLayout(this, "Member " + (j+1), (e)->{});

                linearLayout.addView(memberText);
            }
            teamsLayout.addView(teamName);
            teamsLayout.addView(linearLayout);
        }

    }

    public boolean createTeams(){
        for(int i = 0; i < teamNamesInput.size(); i++){
            ArrayList<String> members = new ArrayList<>();
            //String teamName = teamNamesInput.get(i).getText().toString();
            String teamName = teamNamesInput.get(i).getEditText().toString();
            if(teamName.equals("")) return false;
            for(TextInputLayout memberInput:memberNamesInput.get(i)){
                String member = memberInput.getEditText().toString();
                if(member.equals("")) return false;
                members.add(member);
            }
            teamManager.addTeam(new Team(teamName,members));
        }
        return true;
    }

}