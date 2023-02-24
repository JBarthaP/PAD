package es.ucm.fdi.calculator.Calculator;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.util.Log;
        import android.widget.Button;
        import android.widget.TextView;

        import org.w3c.dom.Text;

        import java.util.ArrayList;
        import java.util.List;

public class OptionalMainActivityAlberto extends AppCompatActivity {


    private Calculator calculator;

    private TextView txtX;

    private TextView txtY;

    private static final String TAG = OptionalMainActivity.class.getSimpleName();

    private int selectedTextView;
    List<TextView> textViews;

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

    public void setSelectedTextView(int i){
        this.selectedTextView = i;

        txtX.setBackgroundColor(Color.WHITE);
        txtY.setBackgroundColor(Color.WHITE);
        textViews.get(this.selectedTextView).setBackgroundColor(Color.CYAN);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optional_main_alberto);

        Log.d(TAG, "Arranco la aplicación correctamente");

        calculator = new Calculator();
        txtX = findViewById(R.id.number1);
        txtY = findViewById(R.id.number2);

        txtX.setText("");
        txtY.setText("");

        textViews = new ArrayList<TextView>();
        textViews.add(txtX);
        textViews.add(txtY);
        this.setSelectedTextView(0);

        Log.d(TAG, "Definiciones de variables correctas");

        Button buttonEqual = findViewById(R.id.buttonEqual);

        buttonEqual.setOnClickListener(view -> {
            this.addXandY();
        });

        Button buttonCA = findViewById(R.id.buttonCA);
        Button buttonC = findViewById(R.id.buttonC);

        buttonCA.setOnClickListener(view -> {
            txtX.setText("");
            txtY.setText("");
            this.setSelectedTextView(0);
        });

        buttonC.setOnClickListener(view -> {
            textViews.get(this.selectedTextView).setText("");
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

        button1.setOnClickListener(view -> {
            textViews.get(this.selectedTextView).setText(textViews.get(this.selectedTextView).getText()+"1");
        });


        Button buttonSum = findViewById(R.id.buttonSum);

        buttonSum.setOnClickListener(view -> {

            if(this.selectedTextView == 0){
                this.setSelectedTextView(1);
            }

        });



    }
}