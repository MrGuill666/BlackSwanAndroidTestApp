package hu.gergelyszaz.blackswanandroidtestapp.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mad on 2016. 08. 19..
 */
public class Person extends Item {
    public static final String IMAGE = "profile_path";
    public static final String TITLE = "name";
    public static final String DESCRIPTION = "biography";
    public static final String ID = "id";

    private String title = "title";
    private String description = "description";
    private String imageURL;

    public static Person FromJSONObject(JSONObject jsonobject) {
        Person person = new Person();
        try {
            person.title = jsonobject.getString(TITLE);
            person.description = jsonobject.optString(DESCRIPTION);
            person.imageURL = jsonobject.getString(IMAGE);
            person.id = jsonobject.getInt(ID);
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
