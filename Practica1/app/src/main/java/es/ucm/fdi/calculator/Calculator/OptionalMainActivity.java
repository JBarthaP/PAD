package es.ucm.fdi.calculator.Calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OptionalMainActivity extends AppCompatActivity {


    private Calculator calculator;

    private List<TextView> numbers;

    private int textSelected;

    private static final String TAG = OptionalMainActivity.class.getSimpleName();

    private void addXandY() {

        Log.i(TAG, "Entramos en el método de addXandY");

        //Creamos el intento
        Intent intent = new Intent(this, CalculatorResultActivity.class);

        //Recuperamos la información de los dos inputs
        double a = Double.parseDouble(numbers.get(0).getText().toString());
        double b = Double.parseDouble(numbers.get(1).getText().toString());

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
    public void setTxt (String txt) {
        TextView numberSelected = numbers.get(textSelected);
        numbers.get(textSelected).setText(numberSelected.getText() + txt);
    }

    public void changeSelected(int i)  {
        textSelected = i;
        Log.d(TAG, "Cambio el texto elegido");
        for (TextView txtView : numbers) {
            txtView.setBackgroundColor(Color.WHITE);
            txtView.setTextColor(Color.BLACK);
        }
        numbers.get(textSelected).setBackgroundColor(Color.RED);
    }
    private void onClickMethod(View view) {
        Button b = (Button) view;
        String textView = b.getText().toString();

        TextView numberSelected = numbers.get(textSelected);
        Log.d(TAG, String.format("Modificamos el número %d", textSelected));
        try {
            if (textView.equals("C") && numberSelected.length() == 0) {
                throw new Exception("Texto vacío");
            }
            if (textView.equals("C")) {
                numberSelected.setText(numberSelected.getText().subSequence(0, numberSelected.length() - 1));
            } else if (textView.equals("CA")) {
                numberSelected.setText("");
                changeSelected(0);
            } else {
                setTxt(textView);
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optional_main);

        Log.d(TAG, "Arranco la aplicación correctamente");

        calculator = new Calculator();
        TextView txtX = findViewById(R.id.number1);
        txtX.setBackgroundColor(Color.RED);
        TextView txtY = findViewById(R.id.number2);
        numbers = new ArrayList<>(2);
        numbers.add(txtX);
        numbers.add(txtY);
        textSelected = 0;


        Log.d(TAG, "Definiciones de variables correctas");

        Button button = findViewById(R.id.buttonEqual);

        button.setOnClickListener(view -> {
            try {
                if (numbers.get(0).getText().length() == 0 || numbers.get(1).getText().length() == 0) {
                    throw new Exception("Uno de los números es vacio");
                }
                this.addXandY();
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        });

        Button buttonCA = findViewById(R.id.buttonCA);
        buttonCA.setOnClickListener(view -> onClickMethod(view));

        Button buttonC = findViewById(R.id.buttonC);
        buttonC.setOnClickListener(view -> onClickMethod(view));

        Button buttonSum = findViewById(R.id.buttonSum);
        buttonSum.setOnClickListener(view -> {
            int i = (textSelected == 0) ? 1 : 0;
            changeSelected(i);
        });

        Button buttonDot = findViewById(R.id.buttonDecimal);
        buttonDot.setOnClickListener(view -> onClickMethod(view));

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