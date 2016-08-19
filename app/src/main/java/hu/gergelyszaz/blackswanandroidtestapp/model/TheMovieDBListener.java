package hu.gergelyszaz.blackswanandroidtestapp.model;

import java.util.List;

/**
 * Created by mad on 2016. 08. 18..
 */
public interface TheMovieDBListener {
    void UpdateMovies(List<Movie> movies);
    void UpdateTV(List<TVShow> movies);
    void UpdatePeople(List<Person> movies);


}
