package es.ucm.fdi.googlebooksclient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private EditText buscador;

    private RadioGroup filtros;

    private
    BookLoaderCallbacks bookLoaderCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buscador = findViewById(R.id.buscador);
        bookLoaderCallbacks = new BookLoaderCallbacks();
        LoaderManager loaderManager = LoaderManager.getInstance(this);
        if(loaderManager.getLoader(BOOK_LOADER_ID) != null){
            loaderManager.initLoader(BOOK_LOADER_ID,null, bookLoaderCallbacks);
        }

        Button button = findViewById(R.id.search_button);
        button.setOnClickListener(view -> {
            this.searchBooks(view);

        });
    }

    public void searchBooks(View view) {
        String queryString = buscador.getText().toString();
        int printType = filtros.getCheckedRadioButtonId();
        //Queda comprobacion
        Bundle queryBundle = new Bundle();
        queryBundle.putString(BookLoaderCallbacks.EXTRA_QUERY, queryString);
        queryBundle.putString(BookLoaderCallbacks.EXTRA_PRINT_TYPE, printType);
        LoaderManager.getInstance(this)
                .restartLoader(BOOK_LOADER_ID, queryBundle, bookLoaderCallbacks);
    }
}