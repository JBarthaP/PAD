package es.ucm.fdi.teamup.api;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class VideogameLoader extends AsyncTaskLoader<List<VideogameInfo>> {

    private static final String KEY_VALUE = "03f47d83f3b94d20a3b0ad6afb17008a";

    private static final String BASE_URL = "https://api.rawg.io/api/games?";

    private static final String QUERY_PARAM = "search";

    private static final String PAGE_SIZE = "page_size";

    private static final String KEY = "key";

    private static final String MAX_RESULTS_VALUE = "3";

    private String queryString;

    public VideogameLoader(@NonNull Context context, String queryString){
        super(context);
        this.queryString = queryString;
    }

    private List<VideogameInfo> fromJsonResponse(String data){

        List<VideogameInfo> newData = new ArrayList<>();

        try{
            JSONObject jsonObject = new JSONObject(data);
            JSONArray itemsArray = jsonObject.getJSONArray("results");

            int i = 0;
            String title = null;

            while(i < itemsArray.length()){

                JSONObject videojuego = itemsArray.getJSONObject(i);

                try{
                    title = videojuego.getString("name");

                    newData.add(new VideogameInfo(title));
                }
                catch(Exception e){
                    e.printStackTrace();
                }

                i++;
            }

            return newData;

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }


    private URL builtUrl(String queryText){

        try{

            Uri builtURI = Uri.parse(BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryText)
                    .appendQueryParameter(PAGE_SIZE, MAX_RESULTS_VALUE)
                    .appendQueryParameter(KEY, KEY_VALUE)
                    .build();

            URL requestURL = new URL(builtURI.toString());

            return requestURL;

        }
        catch (Exception e){
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return null;
    }

    private List<VideogameInfo> getVideogameInfoJson(String queryString){

        URL requestURL = builtUrl(queryString);
        HttpURLConnection conn = null;
        InputStream is = null;

        try{
            conn = (HttpURLConnection) requestURL.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();

            int response = conn.getResponseCode();
            is = conn.getInputStream();
            String contentAsString = convertIsToString(is);

            return fromJsonResponse(contentAsString);

        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {

            if(conn != null){
                conn.disconnect();
            }
            if(is != null){
                try{
                    is.close();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }

        }

        return null;

    }

    @Override
    public List<VideogameInfo> loadInBackground(){
        return getVideogameInfoJson(queryString);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }


}
