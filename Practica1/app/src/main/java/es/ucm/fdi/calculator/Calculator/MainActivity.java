package es.ucm.fdi.calculator.Calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private Calculator calculator;
    private EditText editTextX;
    private EditText editTextY;

    private static final String TAG = MainActivity.class.getSimpleName();

    private void addXandY() {

        Log.i(TAG, "Entramos en el método de addXandY");

        //Creamos el intento
        Intent intent = new Intent(this, CalculatorResultActivity.class);

        //Recuperamos la información de los dos inputs
        double a = Double.parseDouble(editTextX.getText().toString());
        double b = Double.parseDouble(editTextY.getText().toString());

        Log.d(TAG, "El valor de a es =" + a);
        Log.d(TAG, "El valor de b es =" + b);


        //Realizamos la suma
        double suma =  calculator.suma(a,b);

        Log.d(TAG, "El valor de la suma es =" + suma);

        //Introducimos el resultado en los extras del intento
        // he iniciamos la actividad donde se mostrará el resultado
        intent.putExtra("suma", suma);
        startActivity(intent);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "Arranco la aplicación correctamente");

        calculator = new Calculator();
        editTextX = findViewById(R.id.number1);
        editTextY = findViewById(R.id.number2);

        Log.d(TAG, "Definiciones de variables correctas");

        Button button = findViewById(R.id.button);

        button.setOnClickListener(view -> {
            this.addXandY();
        });



    }
}