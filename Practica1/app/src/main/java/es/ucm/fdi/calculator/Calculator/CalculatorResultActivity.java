package es.ucm.fdi.calculator.Calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorResultActivity extends AppCompatActivity {

    private TextView txtView;


    private Button returnButton;

    private static final String TAG = CalculatorResultActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_result);

        Log.i(TAG, String.format("Se inicio la actividad %s", TAG));

        Intent intent = getIntent();
        double suma = intent.getDoubleExtra("suma", 0.0);

        Log.d(TAG, String.format("El valor de la suma es %f",suma));

        txtView = findViewById(R.id.resultado);
        txtView.setText(Double.toString(suma));

        returnButton = findViewById(R.id.button2);
        returnButton.setOnClickListener(view -> {
            this.finish();
        });
    }
}