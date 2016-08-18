package hu.gergelyszaz.blackswanandroidtestapp;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by mad on 2016. 08. 17..
 */



public class TheMovieDB extends AsyncTask<String, Void, String> {
        private static String API_KEY = "?api_key=0a08e38b874d0aa2d426ffc04357069d";
        private static String ADDRESS = "http://api.themoviedb.org/3";
        private static String SEARCH = "/search";
        private static String PERSON = "/person";
        private static String MOVIE = "/movie";
        private static String TV = "/tv";
        private static String POPULAR = "/popular";

        private List<Movie> movies=new ArrayList<>();

        public List<String> getTop20Movies() {
            execute(ADDRESS+MOVIE+POPULAR+API_KEY);
            List<String> movies = new ArrayList<>();
            return movies;
        }

        @Override
        protected String doInBackground(String... params) {
            String response=GetHttpResponse(params[0]);
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            List<Movie> movies=new ArrayList<>();
            try {
                JSONObject jsonObject=new JSONObject(result);
                JSONArray moviesArray=jsonObject.getJSONArray("results");
                for (int i = 0; i < moviesArray.length(); i++) {
                    Movie movie=Movie.FromJSONObject(moviesArray.getJSONObject(i));
                    movies.add(movie);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}

        private String GetHttpResponse(String address){

            URL url = null;
            String response="";
            HttpURLConnection urlConnection=null;
            try {
                url = new URL(address);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                response=StreamToStringConverter.convertStreamToString(in);
                System.out.println(response);
                in.close();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
            return response;
        }


    }


