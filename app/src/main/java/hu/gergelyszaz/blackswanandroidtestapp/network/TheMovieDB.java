package hu.gergelyszaz.blackswanandroidtestapp.network;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import hu.gergelyszaz.blackswanandroidtestapp.model.ModelUpdateListener;
import hu.gergelyszaz.blackswanandroidtestapp.model.Movie;
import hu.gergelyszaz.blackswanandroidtestapp.model.Person;
import hu.gergelyszaz.blackswanandroidtestapp.model.TVShow;


/**
 * Created by mad on 2016. 08. 17..
 */


public class TheMovieDB extends AsyncTask<String, Void, String> {
    public final static int MOVIES = 0;
    public final static int PEOPLE = 1;
    public final static int TV = 2;

    ModelUpdateListener listener = null;
    private int type;

    public TheMovieDB(ModelUpdateListener listener, int type) {
        this.listener = listener;
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
        String response = HTTPConnectionHelper.getHttpResponseMessage(address);
        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        List<Movie> movies = new ArrayList<>();
        List<TVShow> tvshows = new ArrayList<>();
        List<Person> people = new ArrayList<>();
        try {

            JSONArray jsonArray = new JSONObject(result).getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject item = jsonArray.getJSONObject(i);
                switch (type){
                    case MOVIES:
                        movies.add(Movie.FromJSONObject(item));
                        break;
                    case TV:
                        tvshows.add(TVShow.FromJSONObject(item));
                        break;
                    case PEOPLE:
                        people.add(Person.FromJSONObject(item));
                        break;
                    default: throw new IllegalArgumentException();
                }

            }
            switch (type){
                case MOVIES:
                    listener.UpdateMovies(movies);
                    break;
                case TV:
                    listener.UpdateTV(tvshows);
                    break;
                case PEOPLE:
                    listener.UpdatePeople(people);
                    break;
                default: throw new IllegalArgumentException();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }




}


