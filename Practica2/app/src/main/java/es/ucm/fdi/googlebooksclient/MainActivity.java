package es.ucm.fdi.googlebooksclient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int BOOK_LOADER_ID = 0;
    private EditText buscador;

    private RadioGroup filtros;

    private ArrayList<BookInfo> myBookList;

    private BookLoaderCallbacks bookLoaderCallbacks;

    private BooksResultListAdapter bookAdapter;

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buscador = findViewById(R.id.buscador);
        filtros = findViewById(R.id.filtros);
        bookLoaderCallbacks = new BookLoaderCallbacks(this, bookAdapter); // TODO preguntar si esto es correcto, necesario sacar el adapter del algun lado
        LoaderManager loaderManager = LoaderManager.getInstance(this);
        if (loaderManager.getLoader(BOOK_LOADER_ID) != null) {
            loaderManager.initLoader(BOOK_LOADER_ID, null, bookLoaderCallbacks);
        }

        Button button = findViewById(R.id.search_button);
        button.setOnClickListener(view -> {
            this.searchBooks(view);

        });

        recyclerView = findViewById(R.id.recyclerview);
        myBookList = new ArrayList<>();
        bookAdapter = new BooksResultListAdapter(this, myBookList);
        recyclerView.setAdapter(bookAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void searchBooks(View view) {
        String queryString = buscador.getText().toString();
        int choosenFilter = filtros.getCheckedRadioButtonId();
        queryString = "Game";
        String printType = "books";
        //Queda comprobacion
        Bundle queryBundle = new Bundle();
        queryBundle.putString(BookLoaderCallbacks.EXTRA_QUERY, queryString);
        queryBundle.putString(BookLoaderCallbacks.EXTRA_PRINT_TYPE, printType);
        LoaderManager.getInstance(this).restartLoader(BOOK_LOADER_ID, queryBundle, bookLoaderCallbacks);
    }

    void updateBooksResultList(List<BookInfo> bookInfos) {
        bookAdapter.setBooksData(bookInfos);
        bookAdapter.notifyDataSetChanged();
    }
}