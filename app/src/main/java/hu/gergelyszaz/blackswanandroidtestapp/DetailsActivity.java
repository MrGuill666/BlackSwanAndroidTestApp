package hu.gergelyszaz.blackswanandroidtestapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import hu.gergelyszaz.blackswanandroidtestapp.model.Movie;
import hu.gergelyszaz.blackswanandroidtestapp.model.TVShow;
import hu.gergelyszaz.blackswanandroidtestapp.network.HTTPConnectionHelper;
import hu.gergelyszaz.blackswanandroidtestapp.network.TheMovieDB;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        int type = intent.getIntExtra("type", 0);
        String url;
        switch (type) {
            case TheMovieDB.MOVIES:
                url = getString(R.string.url_movie_details) + "/" + id + "?api_key=" + getString(R.string.api_key);
                break;
            case TheMovieDB.TV:
                url = getString(R.string.url_tv_details) + "/" + id + "?api_key=" + getString(R.string.api_key);
                break;
            default:
                throw new IllegalArgumentException();
        }
        new DetailsConnection(type).execute(url);


    }


    public class DetailsConnection extends AsyncTask<String, Void, JSONObject> {
        int type;

        public DetailsConnection(int type) {
            this.type = type;
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            JSONObject jsonObject = new JSONObject();
            try {
                String address = strings[0];
                String response = HTTPConnectionHelper.getHttpResponseMessage(address);
                jsonObject = new JSONObject(response);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject details) {
            super.onPostExecute(details);
            try {

                TextView textView = (TextView) findViewById(R.id.text);
                TextView titleView = (TextView) findViewById(R.id.title);
                TextView subtitleView = (TextView) findViewById(R.id.subtitle);

                String description, title;
                switch (type) {
                    case TheMovieDB.MOVIES:
                        description = Movie.DESCRIPTION;
                        title = Movie.TITLE;
                        break;
                    case TheMovieDB.TV:
                        description = TVShow.DESCRIPTION;
                        title = TVShow.TITLE;
                        break;
                    default:
                        throw new IllegalArgumentException();
                }

                JSONArray genreArray = details.getJSONArray("genres");
                StringBuilder stringBuilder = new StringBuilder("");
                for (int i = 0; i < genreArray.length(); i++) {
                    JSONObject genreObject = genreArray.getJSONObject(i);
                    String genreString = genreObject.optString("name");
                    stringBuilder.append(genreString);
                    if (i < genreArray.length() - 1) {
                        stringBuilder.append(", ");
                    }
                }
                subtitleView.setText(stringBuilder.toString());
                textView.setText(details.getString(description));
                titleView.setText(details.getString(title));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
