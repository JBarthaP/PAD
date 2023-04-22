package es.ucm.fdi.teamup.models;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class ViewUtils {

    @FunctionalInterface
    public interface Function<T>{
        void aply(T element);
    }

    public static LinearLayout createStyledLinearLayout(Context context, Function<LinearLayout> function){
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(params);
        function.aply(linearLayout);
        return linearLayout;
    }

    public static LinearLayout createStyledHorizontalLinearLayout(Context context, Function<LinearLayout> function){
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(params);
        function.aply(linearLayout);
        return linearLayout;
    }

    public static EditText createStyledEditText(Context context, String label, Function<EditText> function){
        EditText editText = new EditText(context);
        editText.setText(label);
        editText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        editText.setTextColor(Color.BLACK);
        function.aply(editText);
        return editText;
    }

    public static TextInputLayout createStyledTextInputLayout(Context context, String label, Function<TextInputEditText> function){
        TextInputLayout textInputLayout = new TextInputLayout(context);
        TextInputEditText textInputEditText = new TextInputEditText(textInputLayout.getContext());
        textInputEditText.setHint(label);
        textInputLayout.addView(textInputEditText);
        textInputLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textInputLayout.setDefaultHintTextColor(ColorStateList.valueOf(Color.BLACK));
        textInputEditText.setTextColor(Color.BLACK);
        textInputLayout.setBoxBackgroundColor(Color.TRANSPARENT);
        function.aply(textInputEditText);
        return textInputLayout;
    }


    public static TextView createStyledTextView(Context context, String label, Function<TextView> function){
        TextView textView = new TextView(context);
        textView.setText(label);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setTextColor(Color.BLACK);
        function.aply(textView);
        return textView;
    }



    public  static CardView createStyledCardView(Context context, Function<CardView> function){
        CardView cardView = new CardView(context);
        function.aply(cardView);
        return  cardView;
    }


    public static GradientDrawable createBorder(int width, int color, Function<GradientDrawable> function){
        GradientDrawable border = new GradientDrawable();
        border.setColor(Color.argb(0,0,0,0));
        border.setStroke(width, color);
        function.aply(border);
        return border;
    }

    public static View createSeparator(Context context, int height){
        View separator = new View(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                height
        );
        separator.setLayoutParams(params);
        separator.setBackgroundColor(Color.BLACK);
        return separator;
    }

    public static Spinner createSpinner(Context context, ArrayList<String> options, Function<Spinner> function){
        Spinner spinner = new Spinner(context);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
        function.aply(spinner);
        System.out.print("Creando spinner: " + spinner.toString());
        return spinner;
    }

}
