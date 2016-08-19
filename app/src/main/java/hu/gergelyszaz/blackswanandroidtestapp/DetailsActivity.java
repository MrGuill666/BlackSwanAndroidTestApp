package hu.gergelyszaz.blackswanandroidtestapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

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
        new DetailsConnection().execute(url);


    }


    public class DetailsConnection extends AsyncTask<String, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(String... strings) {
            String address = strings[0];
            String response = HTTPConnectionHelper.getHttpResponseMessage(address);
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(response);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject s) {
            super.onPostExecute(s);
            TextView textView = (TextView) findViewById(R.id.text);
            TextView titleView = (TextView) findViewById(R.id.title);
            try {
                textView.setText(s.getString("overview"));
                titleView.setText(s.getString("title"));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
