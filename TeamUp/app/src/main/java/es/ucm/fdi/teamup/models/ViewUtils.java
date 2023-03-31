package es.ucm.fdi.teamup.models;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

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

    public static EditText createStyledEditText(Context context, String label, Function<EditText> function){
        EditText editText = new EditText(context);
        editText.setText(label);
        editText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        editText.setTextColor(Color.BLACK);
        function.aply(editText);
        return editText;
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

}
