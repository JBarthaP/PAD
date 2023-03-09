package es.ucm.fdi.googlebooksclient;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import java.util.List;

public class BookLoaderCallbacks implements LoaderManager.LoaderCallbacks<List<BookInfo>> {

    public static final String EXTRA_QUERY = "queryString";
    public static final String EXTRA_PRINT_TYPE = "printType";

    public static final String TAG = BookLoaderCallbacks.class.getSimpleName();

    private Context context;


    public BookLoaderCallbacks(Context context) {
        this.context = context;
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
        return new BookLoader(context, queryString, printType);
    }

    //Metodo que se llama cuando se termina la tarea asincrona
    @Override
    public void onLoadFinished(@NonNull Loader<List<BookInfo>> loader, List<BookInfo> data) {
//        Log.d(TAG, data.toString());
        MainActivity activity = (MainActivity) context;
        activity.updateBooksResultList(data);
    }

    //Limpia los datos del loader
    @Override
    public void onLoaderReset(@NonNull Loader<List<BookInfo>> loader) {
    }
}
