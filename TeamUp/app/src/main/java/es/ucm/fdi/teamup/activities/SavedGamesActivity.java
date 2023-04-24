package es.ucm.fdi.teamup.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import es.ucm.fdi.teamup.Controlador;
import es.ucm.fdi.teamup.R;
import es.ucm.fdi.teamup.database.AppDatabase;
import es.ucm.fdi.teamup.database.daos.DAOGame;
import es.ucm.fdi.teamup.database.entities.GameEntity;
import es.ucm.fdi.teamup.database.repositories.GameRepository;
import es.ucm.fdi.teamup.database.repositories.GameRepositoryImp;
import es.ucm.fdi.teamup.models.Utils;
import es.ucm.fdi.teamup.models.ViewUtils;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.List;


public class SavedGamesActivity extends AppCompatActivity {
    private FrameLayout desplegable;

    private Controlador controller;
    private ConstraintLayout desplegable2;
    private ImageView collapsibleButton;

    private LinearLayout gamesLayout;

    private List<GameEntity> gameEntities;

    private Button filterButton;

    private TextInputLayout participantes;

    private TextInputLayout ganador;

    private ImageView returnButton;
    private  TextInputLayout juego;
    private Resources res;
    private boolean filterclosed;
    ValueAnimator anim;

    int heightValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_games);
        res = getResources();
        controller = (Controlador) getApplication();
        desplegable = findViewById(R.id.filterFrame);
        desplegable2 = findViewById(R.id.filterInteriorContainer);
        collapsibleButton = findViewById(R.id.collapsibleButton);
        collapsibleButton.setOnClickListener((view)->{
            makeAnimation();
        });

        heightValue = 500;
        Configuration config = getResources().getConfiguration();
        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) heightValue = 250;

        anim = ValueAnimator.ofInt(heightValue,0);
        filterclosed = true;
        gamesLayout = findViewById(R.id.gameListContainer);
        filterButton = findViewById(R.id.filterButton);
        participantes = findViewById(R.id.search_name_games);
        juego = findViewById(R.id.search_type_games);
        ganador = findViewById(R.id.search_winner_games);
        AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());
        DAOGame daoGame = db.daoGame();
        GameRepository gameRepository = new GameRepositoryImp(daoGame);
        gameEntities = gameRepository.getGamesByUser(controller.getUserLogged().getUserId());
        returnButton = findViewById(R.id.gameListReturn);
        createGamesLayout();

        anim.setDuration(600);
        anim.addUpdateListener(valueAnimator -> {
            int value = (int) valueAnimator.getAnimatedValue();
            ViewGroup.LayoutParams layoutParams = desplegable2.getLayoutParams();
            layoutParams.height = value;
            desplegable2.setLayoutParams(layoutParams);
        });



        filterButton.setOnClickListener((e)->{createFilteredGamesLayout();});

        returnButton.setOnClickListener((e)->{
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

    }

    public void makeAnimation(){
        if(anim.isRunning()) return;


        anim.start();
        collapsibleButton.animate().rotationBy(180).setDuration(500).start();

        if(filterclosed){
            anim.setIntValues(heightValue,0);
            filterclosed = false;
        }
        else {
            anim.setIntValues(0,heightValue);
            filterclosed = true;
        }


    }

    public void createGamesLayout(){

        for(GameEntity game: gameEntities){
            LinearLayout verticalLayout = ViewUtils.createStyledLinearLayout(this, (e)->{});
            verticalLayout.addView(ViewUtils.createStyledTextView(this, game.getGame_name(), (e)->{
                e.setHeight(90);
                e.setTextSize(20);
                e.setGravity(View.TEXT_ALIGNMENT_CENTER);
                e.setBackgroundColor(res.getColor(R.color.orange));
                e.setTextColor(res.getColor(R.color.white));
                e.setClipToOutline(true);
                e.setPadding(20,0,0,0);
                e.setBackground(ViewUtils.createBorder(0, Color.BLACK, (element)->{
                    element.setColor(res.getColor(R.color.orange));
                }));
            }));
            LinearLayout horizLayout = ViewUtils.createStyledHorizontalLinearLayout(this, (e)->{});
            String winners = game.getPositionString().split("/")[0];
            String winnerTeam = winners.split(":")[0];
            String winnerMembersString = winners.split(":")[1];
            String[] winnerMembers = winnerMembersString.split(",");
            LinearLayout verticalLayout2 = ViewUtils.createStyledLinearLayout(this,(e)->{});
            LinearLayout verticalLayout3 = ViewUtils.createStyledLinearLayout(this,(e)->{e.setPadding(40, 0, 0, 0);});


            verticalLayout2.addView(ViewUtils.createStyledTextView(this,getString(R.string.game_type_text)+": " + game.getVideogameName(), (e)->{
                e.setPadding(30, 10, 20, 30);
                e.setTextSize(16);
            }));

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            verticalLayout2.addView(ViewUtils.createStyledTextView(this, getString(R.string.date_text)+": " + sdf.format(game.getFecha()), (e)->{
                e.setPadding(30, 10, 20, 30);
                e.setTextSize(16);
            }));

            verticalLayout3.addView(ViewUtils.createStyledTextView(this, getString(R.string.winner_text)+": " + winnerTeam, (e)->{
                e.setTextSize(16);
            }));
            verticalLayout3.addView(ViewUtils.createStyledTextView(this, getString(R.string.members_text)+": ", (e)->{}));
            for(String member: winnerMembers){
                verticalLayout3.addView(ViewUtils.createStyledTextView(this,"- " + member, (e)->{

                }));
            }
            horizLayout.addView(verticalLayout2);
            horizLayout.addView(verticalLayout3);


            verticalLayout.addView(horizLayout);
            verticalLayout.setOnClickListener((view -> {
                controller.setSelectedGame(game);
                Intent intent = new Intent(this, GameDetailsActivity.class);
                startActivity(intent);
            }));
            gamesLayout.addView(verticalLayout);
            gamesLayout.addView(ViewUtils.createSeparator(this, 3));
        }
    }
    public void createFilteredGamesLayout(){
        gamesLayout.removeAllViews();
        String winnerFilter = Utils.getInputValueAsString(ganador);
        String nameFilter = Utils.getInputValueAsString(participantes);
        String typeFilter = Utils.getInputValueAsString(juego);

        for(GameEntity game: gameEntities){

            if(!typeFilter.equals("")){
                if(game.getVideogameName() == null) continue;
                else if(!game.getVideogameName().contains(typeFilter)) continue;
            }
            if((!nameFilter.equals("")) && !game.getGame_name().contains(nameFilter)) continue;
            boolean isWinner = false;

            LinearLayout verticalLayout = ViewUtils.createStyledLinearLayout(this, (e)->{});
            verticalLayout.addView(ViewUtils.createStyledTextView(this, game.getGame_name(), (e)->{
                e.setHeight(90);
                e.setTextSize(20);
                e.setGravity(View.TEXT_ALIGNMENT_CENTER);
                e.setBackgroundColor(res.getColor(R.color.orange));
                e.setTextColor(res.getColor(R.color.white));
                e.setClipToOutline(true);
                e.setPadding(20,0,0,0);
                e.setBackground(ViewUtils.createBorder(0, Color.BLACK, (element)->{
                    element.setColor(res.getColor(R.color.orange));
                }));
            }));
            LinearLayout horizLayout = ViewUtils.createStyledHorizontalLinearLayout(this, (e)->{});
            String winners = game.getPositionString().split("/")[0];
            String winnerTeam = winners.split(":")[0];
            String winnerMembersString = winners.split(":")[1];
            String[] winnerMembers = winnerMembersString.split(",");
            LinearLayout verticalLayout2 = ViewUtils.createStyledLinearLayout(this,(e)->{});
            LinearLayout verticalLayout3 = ViewUtils.createStyledLinearLayout(this,(e)->{e.setPadding(40, 0, 0, 0);});


            verticalLayout2.addView(ViewUtils.createStyledTextView(this,getString(R.string.game_type_text)+": " + game.getVideogameName(), (e)->{
                e.setPadding(30, 10, 20, 30);
                e.setTextSize(16);
            }));

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            verticalLayout2.addView(ViewUtils.createStyledTextView(this, getString(R.string.date_text)+": " + sdf.format(game.getFecha()), (e)->{
                e.setPadding(30, 10, 20, 30);
                e.setTextSize(16);
            }));

            if(winnerTeam.contains(winnerFilter)) isWinner = true;
            verticalLayout3.addView(ViewUtils.createStyledTextView(this, getString(R.string.winner_text)+": " + winnerTeam, (e)->{
                e.setTextSize(16);
            }));
            verticalLayout3.addView(ViewUtils.createStyledTextView(this, getString(R.string.members_text)+": ", (e)->{}));
            for(String member: winnerMembers){
                verticalLayout3.addView(ViewUtils.createStyledTextView(this,"- " + member, (e)->{}));
                if(member.contains(winnerFilter)) isWinner = true;
            }
            if(!isWinner) continue;
            horizLayout.addView(verticalLayout2);
            horizLayout.addView(verticalLayout3);


            verticalLayout.addView(horizLayout);
            verticalLayout.setOnClickListener((view -> {
                controller.setSelectedGame(game);
                Intent intent = new Intent(this, GameDetailsActivity.class);
                startActivity(intent);
            }));
            gamesLayout.addView(verticalLayout);
            gamesLayout.addView(ViewUtils.createSeparator(this, 3));
        }
    }
}


