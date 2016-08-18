package hu.gergelyszaz.blackswanandroidtestapp;

import android.content.ClipData;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by mad on 2016. 08. 17..
 */


public class TheMovieDB extends AsyncTask<String, Void, String> {
    public final static String MOVIES = "movies";
    public final static String PEOPLE = "people";
    public final static String TV = "tv";

    ItemList ui = null;
    private String type = null;

    public TheMovieDB(ItemList ui, String type) {
        this.ui = ui;
        this.type=type;
    }

    public void getResponse(String url) {
        execute(url);
    }

    @Override
    protected String doInBackground(String... params) {
        String address = "";
        for (String param : params) {
            address += param;
        }
        String response = StreamToStringConverter.getHttpResponseMessage(address);
        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        List<Movie> movies = new ArrayList<>();
        List<Movie> tv = new ArrayList<>();
        List<Movie> people = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject movie = jsonArray.getJSONObject(i);
                switch (type){
                    case MOVIES: movies.add(Movie.FromJSONObject(movie)); break;
                    case TV: break;
                    case PEOPLE: break;
                    default: throw new IllegalArgumentException();
                }

            }
            switch (type){
                case MOVIES: ui.UpdateMovies(movies); break;
                case TV: break;
                case PEOPLE: break;
                default: throw new IllegalArgumentException();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onProgressUpdate(Void... values) {
    }


}


