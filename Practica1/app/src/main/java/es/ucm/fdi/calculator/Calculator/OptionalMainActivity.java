package es.ucm.fdi.calculator.Calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    private void onClickMethod(View view) {
        Button b = (Button) view;
        String textView = b.getText().toString();

        if (textView.equals("C")) {
            txtX.setText(txtX.getText().subSequence(0,txtX.length()-1));
        }
        else if(textView.equals("CA")) {
            txtX.setText("");
        }
        else {
            setTxtX(textView);
        }

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
        buttonCA.setOnClickListener(view -> onClickMethod(view));

        Button buttonC = findViewById(R.id.buttonC);
        buttonC.setOnClickListener(view -> onClickMethod(view));

        Button button0 = findViewById(R.id.buttonNumber0);
        button0.setOnClickListener(view -> onClickMethod(view));

        Button button00 = findViewById(R.id.buttonNumber00);
        button00.setOnClickListener(view -> onClickMethod(view));

        Button button1 = findViewById(R.id.buttonNumber1);
        button1.setOnClickListener(view -> onClickMethod(view));

        Button button2 = findViewById(R.id.buttonNumber2);
        button2.setOnClickListener(view -> onClickMethod(view));

        Button button3 = findViewById(R.id.buttonNumber3);
        button3.setOnClickListener(view -> onClickMethod(view));

        Button button4 = findViewById(R.id.buttonNumber4);
        button4.setOnClickListener(view -> onClickMethod(view));

        Button button5 = findViewById(R.id.buttonNumber5);
        button5.setOnClickListener(view -> onClickMethod(view));

        Button button6 = findViewById(R.id.buttonNumber6);
        button6.setOnClickListener(view -> onClickMethod(view));

        Button button7 = findViewById(R.id.buttonNumber7);
        button7.setOnClickListener(view -> onClickMethod(view));

        Button button8 = findViewById(R.id.buttonNumber8);
        button8.setOnClickListener(view -> onClickMethod(view));

        Button button9 = findViewById(R.id.buttonNumber9);
        button9.setOnClickListener(view -> onClickMethod(view));




    }
}