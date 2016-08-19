package hu.gergelyszaz.blackswanandroidtestapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hu.gergelyszaz.blackswanandroidtestapp.model.ItemList;
import hu.gergelyszaz.blackswanandroidtestapp.model.Movie;
import hu.gergelyszaz.blackswanandroidtestapp.model.Person;
import hu.gergelyszaz.blackswanandroidtestapp.model.TVShow;
import hu.gergelyszaz.blackswanandroidtestapp.network.TheMovieDB;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ModelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModelFragment extends Fragment implements ItemList {
    private List<Movie> movies = new ArrayList<>();
    private List<TVShow> tvshows = new ArrayList<>();
    private List<Person> people = new ArrayList<>();
    private RecyclerView.Adapter movieAdapter = null;
    private RecyclerView.Adapter personAdapter = null;
    private RecyclerView.Adapter tvshowAdapter = null;

    public ModelFragment() {
        // Required empty public constructor
    }

    public static ModelFragment newInstance() {
        ModelFragment fragment = new ModelFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public static ModelFragment getModelFragment(Fragment caller, FragmentManager fragmentManager) {
        FragmentTransaction fTransaction = fragmentManager.beginTransaction();
        ModelFragment modelFragment = (ModelFragment) fragmentManager.findFragmentByTag("ModelFragment");
        if (modelFragment == null) {
            modelFragment = new ModelFragment();
            modelFragment.setTargetFragment(caller, 0);
            fTransaction.add(modelFragment, "ModelFragment");
        }
        fTransaction.commit();
        return modelFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        new TheMovieDB(this, TheMovieDB.MOVIES).getResponse(getString(R.string.url_movies_popular) + "?api_key=" + getString(R.string.api_key));
        new TheMovieDB(this, TheMovieDB.PEOPLE).getResponse(getString(R.string.url_people_popular) + "?api_key=" + getString(R.string.api_key));
        new TheMovieDB(this, TheMovieDB.TV).getResponse(getString(R.string.url_tv_popular) + "?api_key=" + getString(R.string.api_key));

    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<TVShow> getTVShows() {
        return tvshows;
    }

    public List<Person> getPeople() {
        return people;
    }

    @Override
    public void UpdateMovies(List<Movie> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
        if (movieAdapter != null)
            movieAdapter.notifyDataSetChanged();
    }

    @Override
    public void UpdateTV(List<TVShow> tvshows) {
        this.tvshows.clear();
        this.tvshows.addAll(tvshows);
        if (tvshowAdapter != null)
            tvshowAdapter.notifyDataSetChanged();
    }

    @Override
    public void UpdatePeople(List<Person> people) {
        this.people.clear();
        this.people.addAll(people);
        if (personAdapter != null)
            personAdapter.notifyDataSetChanged();
    }

    public void RegisterMovieAdapter(RecyclerView.Adapter adapter) {
        movieAdapter = adapter;
    }

    public void RegisterTVShowAdapter(RecyclerView.Adapter adapter) {
        tvshowAdapter = adapter;
    }

    public void RegisterPeopleAdapter(RecyclerView.Adapter adapter) {
        personAdapter = adapter;
    }


}
