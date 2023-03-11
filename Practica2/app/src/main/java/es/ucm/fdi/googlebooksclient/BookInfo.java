package es.ucm.fdi.googlebooksclient;

import org.json.JSONArray;

import java.net.URL;

public class BookInfo {

    private String title;
    private String authors;
    private URL infoLink;

    public BookInfo(String title, String authors, URL infoLink) {
        this.title = title;
        this.authors = authors;
        this.infoLink = infoLink;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public String getAuthorsSeparadosPorComas() {

        try{
            JSONArray jsonArray = new JSONArray(authors);

            String autoresString = jsonArray.getString(0);

            for (int i = 1; i < jsonArray.length(); i++){
                autoresString += ", " + jsonArray.getString(i);
            }

            return autoresString;

        }
        catch(Exception e){
            return "";
        }
    }

    public URL getInfoLink() {
        return infoLink;
    }

    @Override
    public String toString() {
        return
                "title='" + title + '\'' +
                ", authors='" + authors + '\'';
    }
}
