package es.ucm.fdi.teamup.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.text.PrecomputedTextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import es.ucm.fdi.teamup.Controlador;
import es.ucm.fdi.teamup.R;
import es.ucm.fdi.teamup.models.GameTeam;
import es.ucm.fdi.teamup.models.Team;
import es.ucm.fdi.teamup.models.Utils;
import es.ucm.fdi.teamup.models.ViewUtils;

public class GameActivity extends AppCompatActivity {

    LinearLayout teamsLayout;
    Button createAgainButton;
    Button storeGameButton;

    Button modalStoreGameButton;
    Button modalDiscardButton;

    Resources res;
    TextView gameName;
    Controlador controller;

    Dialog modal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        controller = (Controlador) getApplication();
        teamsLayout = findViewById(R.id.gameTeamLayout);
        gameName = findViewById(R.id.gameName);
        gameName.setText(controller.getActualGame().getName());
        res = getResources();
        modal = this.createDialog();
        modal.setContentView(R.layout.save_game_modal);
        this.insertPositionInput(modal.findViewById(R.id.gameResultLayout));
        modalStoreGameButton = modal.findViewById(R.id.modalSaveButton);
        modalDiscardButton = modal.findViewById(R.id.modalDiscardButton);

        this.createTeamsLayout();
        createAgainButton = findViewById(R.id.createAgainButton);
        storeGameButton = findViewById(R.id.storeGameButton);
        createAgainButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, RandomTeamResultActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        storeGameButton.setOnClickListener(view -> {
            modal.show();
        });

        modalStoreGameButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        modalDiscardButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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

    private Dialog createDialog(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.save_game_modal);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        return dialog;
    }

    private void insertPositionInput(LinearLayout layout){
        ArrayList<Spinner> spinners = new ArrayList<>();
        int i = 0;
        for(GameTeam team: controller.getActualGame().getTeams()){
            LinearLayout horizlayout = ViewUtils.createStyledHorizontalLinearLayout(this, (e)->{
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) e.getLayoutParams();
                params.setMargins(0,30,0,0);
                e.setLayoutParams(params);
            });
            int finalI = i;
            horizlayout.addView(ViewUtils.createStyledTextView(this, ((i+1)+"º-"), (e)->{
                if(finalI == 0) e.setTextColor(res.getColor(R.color.gold));
                else if(finalI == 1) e.setTextColor(res.getColor(R.color.silver));
                else if(finalI == 2) e.setTextColor(res.getColor(R.color.bronze));
                e.setTextSize(30);
                e.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
            }));

            ArrayList<String> teamNames = Utils.map(controller.getActualGame().getTeams(), (e)->{return e.getTeam().getName();});
            teamNames.add(0,"None");
            Spinner spinner = ViewUtils.createSpinner(this, teamNames , (e)->{
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) e.getLayoutParams();
                params.width = LinearLayout.LayoutParams.WRAP_CONTENT;
                params.height = LinearLayout.LayoutParams.MATCH_PARENT;
                params.setMargins(50,0,0,0);
                e.setLayoutParams(params);
            });

            spinner.setBackground(ViewUtils.createBorder(2,Color.BLACK,(e->{
                e.setCornerRadius(16f);
            })));

            horizlayout.addView(spinner);

            layout.addView(horizlayout);
            spinners.add(spinner);
            i++;
        }
        for(Spinner spinner: spinners){
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // Aquí se ejecuta el código al seleccionar un elemento del Spinner
                    for(Spinner sp: spinners){
                        if(sp.getSelectedItemPosition() == position && !spinner.equals(sp)){
                            sp.setSelection(0);
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // Aquí se ejecuta el código si no se selecciona ningún elemento del Spinner
                }
            });
        }
    }
    protected void onDestroy() {
        super.onDestroy();
        if (modal != null) {
            modal.dismiss();
            modal = null;
        }
    }

}