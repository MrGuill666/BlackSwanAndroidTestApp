package hu.gergelyszaz.blackswanandroidtestapp;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by mad on 2016. 08. 18..
 */
public interface ItemList {
    public void UpdateMovies(List<Movie> movies);
    public void UpdateTV(List<Movie> movies);
    public void UpdatePeople(List<Movie> movies);
}
