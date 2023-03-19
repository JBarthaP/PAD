package es.ucm.fdi.teamup.models;

import android.content.Context;
import android.graphics.Color;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewUtils {
    public static LinearLayout createStyledLinearLayout(Context context){
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(20, 0, 0, 0);
        linearLayout.setLayoutParams(params);
        return linearLayout;
    }

    public static EditText createStyledEditText(Context context, String label){
        EditText editText = new EditText(context);
        editText.setText(label);
        editText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        editText.setTextColor(Color.BLACK);
        return editText;
    }

    public static TextView createStyledTextView(Context context, String label){
        TextView textView = new TextView(context);
        textView.setText(label);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setTextColor(Color.BLACK);
        return textView;
    }

}
