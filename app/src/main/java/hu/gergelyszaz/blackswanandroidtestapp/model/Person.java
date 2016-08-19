package hu.gergelyszaz.blackswanandroidtestapp.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mad on 2016. 08. 19..
 */
public class Person extends Item {

    private String title = "title";
    private String description = "description";
    private String imageURL;

    public static Person FromJSONObject(JSONObject jsonobject) {
        Person person = new Person();
        try {
            person.title = jsonobject.getString("name");
            person.description = jsonobject.optString("biography");
            person.imageURL = jsonobject.getString("profile_path");
            person.id = jsonobject.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return person;
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


}
