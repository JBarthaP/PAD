package es.ucm.fdi.teamup.activities;

import static android.view.FrameMetrics.ANIMATION_DURATION;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import es.ucm.fdi.teamup.R;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;


public class SavedGamesActivity extends AppCompatActivity {
    private FrameLayout desplegable;

    private ConstraintLayout desplegable2;
    private ImageView collapsibleButton;

    private boolean filterclosed;
    ValueAnimator anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_games);
        desplegable = findViewById(R.id.filterFrame);
        desplegable2 = findViewById(R.id.filterInteriorContainer);
        collapsibleButton = findViewById(R.id.collapsibleButton);
        collapsibleButton.setOnClickListener((view)->{
            makeAnimation();
        });
        anim = ValueAnimator.ofInt(500,0);
        filterclosed = true;

        anim.setDuration(600);
        anim.addUpdateListener(valueAnimator -> {
            int value = (int) valueAnimator.getAnimatedValue();
            ViewGroup.LayoutParams layoutParams = desplegable2.getLayoutParams();
            layoutParams.height = value;
            desplegable2.setLayoutParams(layoutParams);
        });

    }

    public void makeAnimation(){
        if(anim.isRunning()) return;
        anim.start();
        collapsibleButton.animate().rotationBy(180).setDuration(500).start();

        if(filterclosed){
            anim.setIntValues(500,0);
            filterclosed = false;
        }
        else {
            anim.setIntValues(0,500);
            filterclosed = true;
        }


    }
}


