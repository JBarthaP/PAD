package es.ucm.fdi.teamup.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.ucm.fdi.teamup.Controlador;
import es.ucm.fdi.teamup.R;
import es.ucm.fdi.teamup.api.VideogameInfo;
import es.ucm.fdi.teamup.api.VideogameLoaderCallbacks;
import es.ucm.fdi.teamup.models.Utils;
import es.ucm.fdi.teamup.models.ViewUtils;

import com.getkeepsafe.taptargetview.TapTargetView;
import com.getkeepsafe.taptargetview.TapTarget;



public class GameStartActivity extends AppCompatActivity {

    private static final String TAG = GameStartActivity.class.getSimpleName();

    private static final int VIDEOGAME_LOADER_ID = 0;
    VideogameLoaderCallbacks videogameLoaderCallbacks;
    Button searchButton;
    LinearLayout videogameSpinnerLayout;
    Spinner spinner;

    private Controlador controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);
        controller = (Controlador) getApplication();
        TextInputLayout gameName = findViewById(R.id.gameNameInput);
        String name = "Partida: " + Utils.getActualDateString();
        gameName.getEditText().setText(name);
        Button continueButton = findViewById(R.id.continueToTeamsButton);


        videogameSpinnerLayout = findViewById(R.id.videogameSpinnerLayout);

        videogameLoaderCallbacks = new VideogameLoaderCallbacks(this);
        LoaderManager loaderManager = LoaderManager.getInstance(this);
        if(loaderManager.getLoader(VIDEOGAME_LOADER_ID) != null){
            loaderManager.initLoader(VIDEOGAME_LOADER_ID, null, videogameLoaderCallbacks);
        }

        searchButton = findViewById(R.id.searchButton);

        searchButton.setOnClickListener(view -> {
            this.searchVideogames(view);
        });


        continueButton.setOnClickListener((view)->{
            String resultName = Utils.getInputValueAsString(gameName);
            if(resultName.equals("")){

                TapTargetView.showFor(this, TapTarget.forView(gameName, "Informacion faltante", "Por favor rellene la información necesaria"));
                return;
            }
            controller.createGame(resultName);

            if(spinner != null){
                String videogameSelected = spinner.getSelectedItem().toString();
                controller.getActualGame().setVideogameName(videogameSelected);

                Log.d(TAG, videogameSelected);
            }

            Intent intent = new Intent(this, TeamsActivity.class);
            startActivity(intent);
        });
    }


    public void searchVideogames(View view){

        String queryString = "";

        EditText searchBar = findViewById(R.id.searchBar);

        queryString = searchBar.getText().toString();

        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        if(inputManager != null){
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;

        if(connMgr != null){
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        if(networkInfo != null && networkInfo.isConnected() && queryString.length() != 0){

            Bundle queryBundle = new Bundle();
            queryBundle.putString(VideogameLoaderCallbacks.EXTRA_QUERY, queryString);
            LoaderManager.getInstance(this).restartLoader(VIDEOGAME_LOADER_ID, queryBundle, videogameLoaderCallbacks);

        }
        else{
            //no esta conectado a internet
            Log.d(TAG, "no hay internet");
            this.videogameResultListShowErrorMessage("No hay conexion");

        }

    }

    public void updateVideogameResultList(List<VideogameInfo> videogameInfos){

        if(videogameInfos == null || videogameInfos.size() == 0){

            this.videogameResultListShowErrorMessage("No hay resultados");

        }
        else{

            ArrayList<String> videogameNames = Utils.map((ArrayList<VideogameInfo>) videogameInfos, (videogame) -> {
                return videogame.getTitle();
            });


            //LinearLayout layout = findViewById(R.id.searchResultLayout);

            /*if(videogameSpinnerLayout == null){

                videogameSpinnerLayout = ViewUtils.createStyledHorizontalLinearLayout(this, (e)->{
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) e.getLayoutParams();
                    params.setMargins(0,30,0,0);
                    e.setLayoutParams(params);
                });

                layout.addView(videogameSpinnerLayout);

            }*/

            videogameSpinnerLayout.removeAllViewsInLayout();

            spinner = ViewUtils.createSpinner(this, videogameNames , (e)->{
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) e.getLayoutParams();
                params.width = LinearLayout.LayoutParams.MATCH_PARENT;
                params.height = LinearLayout.LayoutParams.MATCH_PARENT;
                params.setMargins(0,0,0,0);
                e.setLayoutParams(params);
            });

            spinner.setBackground(ViewUtils.createBorder(2, Color.BLACK,(e->{
                e.setCornerRadius(16f);
            })));

            videogameSpinnerLayout.addView(spinner);

        }

    }


    public void videogameResultListShowErrorMessage(String errorMessage){

        videogameSpinnerLayout.removeAllViewsInLayout();

        ImageView icon = new ImageView(this);
        icon.setImageResource(R.drawable.error_icon);
        icon.setPadding(0,50,0,0);

        TextView textView = new TextView(this);
        textView.setText(errorMessage);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setPadding(50,50,50,50);

        videogameSpinnerLayout.addView(icon);
        videogameSpinnerLayout.addView(textView);

    }



}