package es.ucm.fdi.teamup.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import es.ucm.fdi.teamup.Controlador;
import es.ucm.fdi.teamup.R;
import es.ucm.fdi.teamup.database.entities.GameEntity;
import es.ucm.fdi.teamup.models.GameTeam;
import es.ucm.fdi.teamup.models.Team;
import es.ucm.fdi.teamup.models.ViewUtils;


public class GameDetailsActivity extends AppCompatActivity {

    private TextView fecha;
    private TextView hora;
    private TextView name;
    private TextView type;
    private TextView first;
    private TextView second;
    private TextView third;

    private ImageView returnButton;

    private LinearLayout positionContainer;
    private Controlador controller;

    private Resources res;
    private String[] teams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);
        controller = (Controlador) getApplication();
        fecha = findViewById(R.id.detailedDate5);
        hora = findViewById(R.id.detailedHour2);
        name = findViewById(R.id.detailsGameName);
        type = findViewById(R.id.detailedType2);
        first = findViewById(R.id.detailedfirstplace);
        second = findViewById(R.id.detailedsecondplace);
        third = findViewById(R.id.detailedthirdplace);
        positionContainer = findViewById(R.id.detailedPositionContainer);
        res = getResources();
        returnButton = findViewById(R.id.detailsReturn);
        GameEntity gameInfo = controller.getSelectedGame();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        fecha.setText(formatter.format(gameInfo.getFecha()));

        formatter = new SimpleDateFormat("HH:mm:ss");
        hora.setText(formatter.format(gameInfo.getFecha()));

        name.setText(gameInfo.getGame_name());
        type.setText(gameInfo.getVideogameName());

        teams = gameInfo.getPositionString().split("/");

        if(teams.length >= 1){
            first.setText(teams[0].split(":")[0]);
        }
        else {
            first.setText("-");
        }
        if(teams.length >= 2){
            second.setText(teams[1].split(":")[0]);
        }
        else {
            second.setText("-");
        }
        if(teams.length >= 3){
            third.setText(teams[2].split(":")[0]);
        }
        else {
            third.setText("-");
        }

        createPositions();

        returnButton.setOnClickListener((e)->{
            Intent intent = new Intent(this, SavedGamesActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

    }

    public void createPositions(){
        int position = 0;
        for(String team: teams){
            position++;
            String[] splittedteam = team.split(":");
            String[] members = splittedteam[1].split(",");

            LinearLayout layout = ViewUtils.createStyledLinearLayout(this, (e)->{
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0,50,0,40);
                e.setLayoutParams(params);
                e.setBackground(ViewUtils.createBorder(4, Color.BLACK, (element)->{
                    element.setCornerRadius(16f);
                }));
                e.setPadding(4,4,4,4);
            });

            layout.addView(ViewUtils.createStyledTextView(this, splittedteam[0],(e)->{
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
            }));

            for(String member: members){
                layout.addView(ViewUtils.createSeparator(this, 2));
                layout.addView(ViewUtils.createStyledTextView(this, member, (e)->{
                    e.setPadding(20,0,0,0);
                    e.setTextSize(18);
                }));
            }
            CardView card = ViewUtils.createStyledCardView(this, (e)->{});
            card.addView(layout);
            int finalPosition = position;
            positionContainer.addView(ViewUtils.createStyledTextView(this, position + "º Position", (view)->{
                view.setPadding(10,40,0,20);
                view.setTextSize(24);
                if(finalPosition == 1){
                    view.setTextColor(getResources().getColor(R.color.gold));
                }
                else if(finalPosition == 2){
                    view.setTextColor(getResources().getColor(R.color.silver));
                }
                else if (finalPosition == 3) {
                    view.setTextColor(getResources().getColor(R.color.bronze));
                }
                else {
                    view.setTextColor(Color.BLACK);
                }
            }));
            positionContainer.addView(card);

        }

    }

}