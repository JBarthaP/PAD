package es.ucm.fdi.googlebooksclient;

import android.content.Context;
import android.net.Uri;
import android.nfc.Tag;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BookLoader extends AsyncTaskLoader<String> {

    private static final String KEY = "AIzaSyAXEcmlI4XnU3lADHcwZGBiIemMm8zXjzw";

    private static final String BASE_URL = "https://www.googleapis.com/books/v1/volumes?";

    private static final String QUERY_PARAM = "q";

    private static final String PRINT_TYPES = "printType";


    private static final String TAG = BookLoader.class.getSimpleName();



    public BookLoader(@NonNull Context context)
    {
        super(context);
    }

    private URL builtUrl(String queryText, String printTypes) {
        try {
            Uri builtURI = Uri.parse(BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryText)
                    .appendQueryParameter(PRINT_TYPES, printTypes)
                    .appendQueryParameter("key",KEY)
                    .build();
            URL requestURL = new URL(builtURI.toString());
            return requestURL;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

    public String convertIsToString(InputStream stream) {
        try {
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }
            if (builder.length() == 0) {
                return null;
            }
            String resultString = builder.toString();
            return resultString;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

    private String getBookInfoJson(String queryString, String printTypes) {
        URL requestURL = builtUrl(queryString,printTypes);
        HttpURLConnection conn = null;
        InputStream is = null;
        try {
            conn  = (HttpURLConnection) requestURL.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            int response = conn.getResponseCode();
            is = conn.getInputStream();
            String contentAsString = convertIsToString(is);
            return contentAsString;
        }
        catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        finally {
            if( conn != null) {
                conn.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }



    @Override
    public String loadInBackground() {
        List<String> volumenes = new ArrayList<String>();

        return volumenes;
    }
}
