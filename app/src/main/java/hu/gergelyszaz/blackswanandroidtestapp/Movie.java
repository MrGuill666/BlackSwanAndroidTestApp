package hu.gergelyszaz.blackswanandroidtestapp;

import android.graphics.Bitmap;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mad on 2016. 08. 18..
 */
public class Movie {
    private String imageURL;

    public static Movie FromJSONObject(JSONObject jsonobject){
        Movie movie=new Movie();
        try {
            movie.title=jsonobject.getString("title");
            movie.description=jsonobject.getString("overview");
            movie.imageURL=jsonobject.getString("poster_path");
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

    private String title="title";
    private String description="description";


    public void setImageView(ImageView imageView, String imageURL) {


    }
}
