package hu.gergelyszaz.blackswanandroidtestapp.model;

import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mad on 2016. 08. 19..
 */
public class TVShow {
    private String title = "title";
    private String description = "description";
    private String imageURL;
    private String rating;
    private String date;

    public static TVShow FromJSONObject(JSONObject jsonobject) {
        TVShow tvShow = new TVShow();
        try {
            tvShow.title = jsonobject.getString("name");
            tvShow.description = jsonobject.getString("overview");
            tvShow.imageURL = jsonobject.getString("poster_path");
            tvShow.rating = jsonobject.getString("vote_average");
            tvShow.date = jsonobject.getString("first_air_date");
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

    public void setImageView(ImageView imageView, String imageURL) {


    }

    public String getDate() {
        return date;
    }

    public String getRating() {
        return rating;
    }
}
