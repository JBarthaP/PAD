package es.ucm.fdi.teamup.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.text.PrecomputedTextCompat;

import android.content.Intent;
import android.content.res.Resources;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import es.ucm.fdi.teamup.Controlador;
import es.ucm.fdi.teamup.R;
import es.ucm.fdi.teamup.models.GameTeam;
import es.ucm.fdi.teamup.models.Team;
import es.ucm.fdi.teamup.models.ViewUtils;

public class GameActivity extends AppCompatActivity {

    LinearLayout teamsLayout;
    Button createAgainButton;
    Button storeGameButton;

    Resources res;
    TextView gameName;
    Controlador controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        controller = (Controlador) getApplication();
        teamsLayout = findViewById(R.id.gameTeamLayout);
        gameName = findViewById(R.id.gameName);
        gameName.setText(controller.getActualGame().getName());
        res = getResources();
        this.createTeamsLayout();
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

    private void createTeamsLayout(){

        for(GameTeam gameteam: controller.getActualGame().getTeams()){
            Team team = gameteam.getTeam();
            LinearLayout layout = ViewUtils.createStyledLinearLayout(this, (e)->{
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0,50,0,0);
                e.setLayoutParams(params);
                e.setBackground(ViewUtils.createBorder(4, Color.BLACK, (element)->{
                    element.setCornerRadius(16f);
                }));
                e.setPadding(4,4,4,4);
            });

            layout.addView(ViewUtils.createStyledTextView(this, team.getName(),(e)->{
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

            for(String member: team.getMembers()){
                layout.addView(ViewUtils.createSeparator(this, 2));
                layout.addView(ViewUtils.createStyledTextView(this, member, (e)->{
                    e.setPadding(20,0,0,0);
                    e.setTextSize(18);
                }));
            }
            CardView card = ViewUtils.createStyledCardView(this, (e)->{});
            card.addView(layout);
            teamsLayout.addView(card);
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.versus_icon_small);
            imageView.setPadding(0,50,0,0);
            teamsLayout.addView(imageView);
        }
        teamsLayout.removeViewAt(teamsLayout.getChildCount()-1);
    }
}