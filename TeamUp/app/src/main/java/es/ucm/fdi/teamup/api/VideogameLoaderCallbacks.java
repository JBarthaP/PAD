package es.ucm.fdi.teamup.api;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import java.util.List;

import es.ucm.fdi.teamup.activities.GameActivity;
import es.ucm.fdi.teamup.activities.GameStartActivity;

public class VideogameLoaderCallbacks implements LoaderManager.LoaderCallbacks<List<VideogameInfo>> {

    public static final String EXTRA_QUERY = "queryString";

    private Context context;

    public VideogameLoaderCallbacks(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Loader<List<VideogameInfo>> onCreateLoader(int id, @Nullable Bundle args) {

        String queryString = "";

        if (args != null) {
            queryString = args.getString(EXTRA_QUERY);
        }

        return new VideogameLoader(context, queryString);

    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<VideogameInfo>> loader, List<VideogameInfo> data) {

        GameStartActivity activity = (GameStartActivity) context;

        activity.updateVideogameResultList(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<VideogameInfo>> loader) {

    }


}
