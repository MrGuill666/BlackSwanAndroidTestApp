package hu.gergelyszaz.blackswanandroidtestapp;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mad on 2016. 08. 18..
 */
public class Movie {
    public static Movie FromJSONObject(JSONObject jsonobject){
        Movie movie=new Movie();
        try {
            movie.title=jsonobject.getString("title");
            movie.description=jsonobject.getString("overview");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movie;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    private String title="title";
    private String description="description";



}
