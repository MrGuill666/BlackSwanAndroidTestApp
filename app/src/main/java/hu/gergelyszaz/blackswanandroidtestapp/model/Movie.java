package hu.gergelyszaz.blackswanandroidtestapp.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mad on 2016. 08. 18..
 */
public class Movie {


    private String title = "title";
    private String description = "description";
    private String imageURL;
    private String rating;
    private String date;

    public static Movie FromJSONObject(JSONObject jsonobject){
        Movie movie=new Movie();
        try {
            movie.title=jsonobject.getString("title");
            movie.description=jsonobject.getString("overview");
            movie.imageURL=jsonobject.getString("poster_path");
            movie.rating = jsonobject.getString("vote_average");
            movie.date = jsonobject.getString("release_date");
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

    public String getImageURL() {
        return imageURL;
    }

    public String getDate() {
        return date;
    }

    public String getRating() {
        return rating;
    }
}
