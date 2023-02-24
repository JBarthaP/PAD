package es.ucm.fdi.calculator.Calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class OptionalMainActivity extends AppCompatActivity {


    private Calculator calculator;

    private TextView txtX;

    private TextView txtY;

    private static final String TAG = OptionalMainActivity.class.getSimpleName();

    private void addXandY() {

        Log.i(TAG, "Entramos en el método de addXandY");

        //Creamos el intento
        Intent intent = new Intent(this, CalculatorResultActivity.class);

        //Recuperamos la información de los dos inputs
        double a = Double.parseDouble(txtX.getText().toString());
        double b = Double.parseDouble(txtY.getText().toString());

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

    public void setTxtX (String txt) {
        txtX.setText(txtX.getText() + txt);
    }

    public void setTxtY (String txt) {
        txtY.setText(txtY.getText() + txt);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optional_main);

        Log.d(TAG, "Arranco la aplicación correctamente");

        calculator = new Calculator();
        txtX = findViewById(R.id.number1);
        txtY = findViewById(R.id.number2);

        Log.d(TAG, "Definiciones de variables correctas");

        Button button = findViewById(R.id.buttonEqual);


        button.setOnClickListener(view -> {
            this.addXandY();
        });

        Button buttonCA = findViewById(R.id.buttonCA);

        buttonCA.setOnClickListener(view -> {
           txtX.setText("");
        });



        Button buttonC = findViewById(R.id.buttonC);
        buttonCA.setOnClickListener(view -> {
            txtX.setText("");
        });


        Button button0 = findViewById(R.id.buttonNumber0);
        Button button00 = findViewById(R.id.buttonNumber00);
        Button button1 = findViewById(R.id.buttonNumber1);
        Button button2 = findViewById(R.id.buttonNumber2);
        Button button3 = findViewById(R.id.buttonNumber3);
        Button button4 = findViewById(R.id.buttonNumber4);
        Button button5 = findViewById(R.id.buttonNumber5);
        Button button6 = findViewById(R.id.buttonNumber6);
        Button button7 = findViewById(R.id.buttonNumber7);
        Button button8 = findViewById(R.id.buttonNumber8);
        Button button9 = findViewById(R.id.buttonNumber9);



    }
}