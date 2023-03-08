package es.ucm.fdi.googlebooksclient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int BOOK_LOADER_ID = 0;
    private EditText autorTxt;
    private EditText tituloTxt;

    private TextView resultadosTxt;

    private RadioGroup filtros;

    private ArrayList<BookInfo> myBookList;

    private BookLoaderCallbacks bookLoaderCallbacks;

    private BooksResultListAdapter bookAdapter;

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autorTxt = findViewById(R.id.authors);
        tituloTxt = findViewById(R.id.title);
        filtros = findViewById(R.id.filtros);
        resultadosTxt = findViewById(R.id.resultadosText);

        recyclerView = findViewById(R.id.include3);
        myBookList = new ArrayList<>();
        bookAdapter = new BooksResultListAdapter(this, myBookList);
        recyclerView.setAdapter(bookAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookLoaderCallbacks = new BookLoaderCallbacks(this, bookAdapter); // TODO preguntar si esto es correcto, necesario sacar el adapter del algun lado
        LoaderManager loaderManager = LoaderManager.getInstance(this);
        if (loaderManager.getLoader(BOOK_LOADER_ID) != null) {
            loaderManager.initLoader(BOOK_LOADER_ID, null, bookLoaderCallbacks);
        }

        Button button = findViewById(R.id.search_button);
        button.setOnClickListener(view -> {
            this.searchBooks(view);

        });

    }

    public void searchBooks(View view) {

        String queryString = "";
        queryString += tituloTxt.getText().toString();
        int choosenFilter = filtros.getCheckedRadioButtonId();
        String printType = "";
        switch(choosenFilter) {
            case R.id.radio_books:
                printType = "books";
                break;

            case R.id.radio_magazines:
                printType = "magazines";
                break;

            case R.id.radio_all:
                printType = "all";
                break;
        }

        // Código para saber si el usuario esta en un proceso de búsqueda ocultarle el teclado
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputManager != null ) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        //Comprobación de que la conexión esta operativa
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        //Si la conexión esta operativa hacer llamada
        if (networkInfo != null && networkInfo.isConnected()
                && queryString.length() != 0) {
            autorTxt.setText("");
            tituloTxt.setText("");
            resultadosTxt.setText(R.string.loading);
            Bundle queryBundle = new Bundle();
            queryBundle.putString(BookLoaderCallbacks.EXTRA_QUERY, queryString);
            queryBundle.putString(BookLoaderCallbacks.EXTRA_PRINT_TYPE, printType);
            LoaderManager.getInstance(this).restartLoader(BOOK_LOADER_ID, queryBundle, bookLoaderCallbacks);
        }
        else {
            if (queryString.length() == 0) {
                autorTxt.setText("");
                tituloTxt.setText("");
                resultadosTxt.setText(R.string.no_search);
            } else {
                autorTxt.setText("");
                tituloTxt.setText("");
                resultadosTxt.setText(R.string.no_network);
            }
        }

    }

    void updateBooksResultList(List<BookInfo> bookInfos) {
        bookAdapter.setBooksData(bookInfos);
        bookAdapter.notifyDataSetChanged();
        if(bookInfos.size() == 0) {
            resultadosTxt.setText(R.string.no_data);
        }
        else {
            resultadosTxt.setText(R.string.results);
        }
    }
}