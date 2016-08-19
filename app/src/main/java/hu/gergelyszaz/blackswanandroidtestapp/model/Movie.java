package hu.gergelyszaz.blackswanandroidtestapp.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mad on 2016. 08. 18..
 */
public class Movie extends Item {
    public static final String IMAGE = "poster_path";
    public static final String VOTE = "vote_average";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "overview";
    public static final String DATE = "release_date";
    public static final String ID = "id";

    private String title = "title";
    private String description = "description";
    private String imageURL;
    private String rating;
    private String date;

    public static Movie FromJSONObject(JSONObject jsonobject){
        Movie movie=new Movie();
        try {
            movie.title = jsonobject.getString(TITLE);
            movie.description = jsonobject.getString(DESCRIPTION);
            movie.imageURL = jsonobject.getString(IMAGE);
            movie.rating = jsonobject.getString(VOTE);
            movie.date = jsonobject.getString(DATE);
            movie.id = jsonobject.getInt(ID);
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
