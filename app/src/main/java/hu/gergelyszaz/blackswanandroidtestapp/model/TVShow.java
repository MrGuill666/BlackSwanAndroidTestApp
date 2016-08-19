package hu.gergelyszaz.blackswanandroidtestapp.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mad on 2016. 08. 19..
 */
public class TVShow extends Item {

    public static final String IMAGE = "poster_path";
    public static final String VOTE = "vote_average";
    public static final String TITLE = "name";
    public static final String DESCRIPTION = "overview";
    public static final String DATE = "first_air_date";
    public static final String ID = "id";

    private String title = "title";
    private String description = "description";
    private String imageURL;
    private String rating;
    private String date;
    private String category;

    public static TVShow FromJSONObject(JSONObject jsonobject) {
        TVShow tvShow = new TVShow();
        try {
            tvShow.description = jsonobject.getString(DESCRIPTION);
            tvShow.imageURL = jsonobject.getString(IMAGE);
            tvShow.rating = jsonobject.getString(VOTE);
            tvShow.title = jsonobject.getString(TITLE);
            tvShow.date = jsonobject.getString(DATE);
            tvShow.id = jsonobject.getInt(ID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tvShow;
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
