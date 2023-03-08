package es.ucm.fdi.googlebooksclient;

import android.content.Context;
import android.net.Uri;
import android.nfc.Tag;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BookLoader extends AsyncTaskLoader<List<BookInfo>> {

    private static final String KEY = "AIzaSyAXEcmlI4XnU3lADHcwZGBiIemMm8zXjzw";

    private static final String BASE_URL = "https://www.googleapis.com/books/v1/volumes?";

    private static final String QUERY_PARAM = "q";

    private static final String PRINT_TYPES = "printType";

    private String queryString;

    private String printType;

    private static final String TAG = BookLoader.class.getSimpleName();

    public BookLoader(@NonNull Context context, String queryString, String printType) {
        super(context);
        this.queryString = queryString;
        this.printType = printType;
    }

    private List<BookInfo> fromJsonResponse(String data) {
        List<BookInfo> newData = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray itemsArray = jsonObject.getJSONArray("items");

            int i = 0;
            String title = null;
            String author = null;
            String urlInfo = null;
            while (i < itemsArray.length()) {
                //Cogemos la información necesaria
                JSONObject libro = itemsArray.getJSONObject(i);
                JSONObject info = libro.getJSONObject("volumeInfo");

                try {
                    title = info.getString("title");
                    if(info.has("authors")) {
                        author = info.getString("authors");
                    }

//                    Log.d(TAG, title + "" + author);
                    urlInfo = info.getString("infoLink");
                    newData.add(new BookInfo(title, author, new URL(Uri.parse(urlInfo).toString())));




                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TAG, e.getMessage());
                }

                i++;
            }
            Log.d(TAG, newData.toString());
            return newData;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    private URL builtUrl(String queryText, String printTypes) {
        try {
            Uri builtURI = Uri.parse(BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryText)
                    .appendQueryParameter(PRINT_TYPES, printTypes)
                    .appendQueryParameter("key", KEY)
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

    private List<BookInfo> getBookInfoJson(String queryString, String printTypes) {
        URL requestURL = builtUrl(queryString, printTypes);
        HttpURLConnection conn = null;
        InputStream is = null;
        try {
            conn = (HttpURLConnection) requestURL.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            int response = conn.getResponseCode();
            is = conn.getInputStream();
            String contentAsString = convertIsToString(is);
//            Log.d(TAG, contentAsString);
            return fromJsonResponse(contentAsString);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        } finally {
            if (conn != null) {
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
    public List<BookInfo> loadInBackground() {
        return getBookInfoJson(queryString, printType);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

}
