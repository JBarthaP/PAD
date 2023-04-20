package es.ucm.fdi.teamup.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.res.Resources;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import es.ucm.fdi.teamup.Controlador;
import es.ucm.fdi.teamup.R;
import es.ucm.fdi.teamup.models.GameTeam;
import es.ucm.fdi.teamup.models.Team;
import es.ucm.fdi.teamup.models.TeamManager;
import es.ucm.fdi.teamup.models.ViewUtils;
import kotlin.Triple;


public class CustomTeamInputActivity extends AppCompatActivity {


    private Intent actualIntent;
    private LinearLayout teamsLayout;

    Controlador controller;
    ArrayList<EditText> teamNamesInput;
    ArrayList<ArrayList<EditText>> memberNamesInput;
    private Button continue_button;

    private int team_number;

    private int member_number;

    private TeamManager teamManager;

    private Resources res;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_team_input);
        res = getResources();
        controller = (Controlador) getApplication();
        actualIntent = getIntent();
        teamManager = controller.getTeamManager();
        teamsLayout = findViewById(R.id.custom_teams_input_layout);
        continue_button = findViewById(R.id.continue_button);
        teamNamesInput = new ArrayList<>();
        memberNamesInput = new ArrayList<>();

        team_number = actualIntent.getIntExtra("team_number", 0);
        member_number = actualIntent.getIntExtra("member_number", 0);
        teamManager.setnTeams(team_number);

        this.createTeamsLayout();

        continue_button.setOnClickListener(view -> {
            if (!createTeams()) return;
            controller.startGame();
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        });
    }




    public boolean createTeams(){
        for(int i = 0; i < teamNamesInput.size(); i++){
            ArrayList<String> members = new ArrayList<>();

            String teamName = teamNamesInput.get(i).getText().toString();
            if(teamName.equals("")){
                TapTargetView.showFor(this, TapTarget.forView(teamNamesInput.get(i), "Informacion faltante", "Por favor rellene la información necesaria"));
                return false;
            }
            for(EditText memberInput:memberNamesInput.get(i)){
                String member = memberInput.getText().toString();
                if(member.equals("")){
                    TapTargetView.showFor(this, TapTarget.forView(memberInput, "Informacion faltante", "Por favor rellene la información necesaria"));
                    return false;
                }
                members.add(member);
                teamManager.addMember(member);
            }

            teamManager.addTeam(new Team(teamName,members));
        }
        return true;
    }

    private void createTeamsLayout(){

        Triple<Integer,Integer,Integer> teamSize = TeamManager.getTeamsSize(team_number, member_number);
        int membersPerGroup = teamSize.getFirst();
        int bigTeams = teamSize.getSecond();
        int smallTeams = teamSize.getThird();
        teamNamesInput = new ArrayList<>();
        memberNamesInput = new ArrayList<>();

        for (int i = 0; i < bigTeams+smallTeams; i++) {

            LinearLayout layout = ViewUtils.createStyledLinearLayout(this, (e)->{
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0,50,0,0);
                e.setLayoutParams(params);
                e.setBackground(ViewUtils.createBorder(4, Color.BLACK, (element)->{
                    element.setCornerRadius(16f);
                }));
                e.setPadding(4,4,4,4);
            });

            EditText teamText = ViewUtils.createStyledEditText(this, "Team " + (i+1) ,(e)->{
                e.setHeight(70);
                e.setTextSize(20);
                e.setBackgroundColor(res.getColor(R.color.orange));
                e.setTextColor(res.getColor(R.color.white));
                e.setClipToOutline(true);
                e.setPadding(20,0,0,0);
                e.setBackground(ViewUtils.createBorder(0, Color.BLACK, (element)->{
                    element.setCornerRadii(new float[]{16, 16, 16, 16, 0, 0, 0, 0});
                    element.setColor(res.getColor(R.color.orange));
                }));
            });

            teamNamesInput.add(teamText);
            layout.addView(teamText);

            if(i == bigTeams) membersPerGroup--;
            memberNamesInput.add(new ArrayList<>());
            for (int j = 0; j < membersPerGroup; j++) {

                layout.addView(ViewUtils.createSeparator(this, 2));
                EditText member = ViewUtils.createStyledEditText(this, "Member " + (j+1), (e) -> {
                    e.setBackgroundColor(Color.argb(0,0,0,0));
                    e.setPadding(20,0,0,0);
                    e.setTextSize(18);
                });
                memberNamesInput.get(i).add(member);
                layout.addView(member);
            }
            CardView card = ViewUtils.createStyledCardView(this, (e)->{});
            card.addView(layout);
            teamsLayout.addView(card);

        }
    }

}