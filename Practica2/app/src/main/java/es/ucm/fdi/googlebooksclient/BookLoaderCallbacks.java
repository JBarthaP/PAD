package es.ucm.fdi.googlebooksclient;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class BookLoaderCallbacks implements LoaderManager.LoaderCallbacks<List<BookInfo>> {

    public static final String EXTRA_QUERY = "queryString";
    public static final String EXTRA_PRINT_TYPE = "printType";

    public static final String TAG = BookLoaderCallbacks.class.getSimpleName();

    private Context context;

    private BooksResultListAdapter bookAdapter;

    public BookLoaderCallbacks(Context context, BooksResultListAdapter bookAdapter) {
        this.context = context;
        this.bookAdapter = bookAdapter;
    }

    //Metodo que se llama cuando se instancia el loader
    @NonNull
    @Override
    public Loader<List<BookInfo>> onCreateLoader(int id, @Nullable Bundle args) {
        String queryString = "";
        String printType = "";
        if (args != null) {
            queryString = args.getString(EXTRA_QUERY);
            printType = args.getString(EXTRA_PRINT_TYPE);
        }
        return new BookLoader(context, queryString, printType); //TODO no habría que borrar el anterior?
    }

    //Metodo que se llama cuando se termina la tarea asincrona
    @Override
    public void onLoadFinished(@NonNull Loader<List<BookInfo>> loader, List<BookInfo> data) {
        Log.d(TAG, data.toString());
        bookAdapter.setBooksData(data);
        bookAdapter.notifyDataSetChanged();
    }

    //Limpia los datos del loader
    @Override
    public void onLoaderReset(@NonNull Loader<List<BookInfo>> loader) {
    }
}
