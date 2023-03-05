package es.ucm.fdi.googlebooksclient;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

public class BookLoaderCallbacks implements LoaderManager.LoaderCallbacks<String>{

    //Metodo que se llama cuando se instancia el loader
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new BookLoader();
    }

    //Metodo que se llama cuando se termina la tarea asincrona
    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {

    }

    //Limpia los datos del loader
    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {}
}
