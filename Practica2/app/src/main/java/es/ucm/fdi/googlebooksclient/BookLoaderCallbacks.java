package es.ucm.fdi.googlebooksclient;

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

public class BookLoaderCallbacks implements LoaderManager.LoaderCallbacks<String> {

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
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
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
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray itemsArray = jsonObject.getJSONArray("items");

            int i = 0;
            String title = null;
            String author = null;
            while (i < itemsArray.length()) {
                //Cogemos la información necesaria
                JSONObject libro = itemsArray.getJSONObject(i);
                JSONObject info = libro.getJSONObject("volumeInfo");

                try {
                    title = info.getString("title");
                    author = info.getString("authors");

                    Log.d(TAG, title + "" + author);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TAG, e.getMessage());
                }

                i++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //Limpia los datos del loader
    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
    }
}
