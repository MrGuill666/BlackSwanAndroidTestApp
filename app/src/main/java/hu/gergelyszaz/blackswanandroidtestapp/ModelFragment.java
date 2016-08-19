package hu.gergelyszaz.blackswanandroidtestapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hu.gergelyszaz.blackswanandroidtestapp.model.ModelUpdateListener;
import hu.gergelyszaz.blackswanandroidtestapp.model.Movie;
import hu.gergelyszaz.blackswanandroidtestapp.model.Person;
import hu.gergelyszaz.blackswanandroidtestapp.model.TVShow;


public class ModelFragment extends Fragment implements ModelUpdateListener {
    private List<Movie> movies = new ArrayList<>();
    private List<TVShow> tvshows = new ArrayList<>();
    private List<Person> people = new ArrayList<>();
    private RecyclerView.Adapter movieAdapter;
    private RecyclerView.Adapter personAdapter;
    private RecyclerView.Adapter tvshowAdapter;

    public ModelFragment() {
        // Required empty public constructor
    }

    public static ModelFragment newInstance() {
        ModelFragment fragment = new ModelFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragment.setRetainInstance(true);
        return fragment;
    }

    public static ModelFragment getModelFragment(FragmentManager fragmentManager) {
        FragmentTransaction fTransaction = fragmentManager.beginTransaction();
        ModelFragment modelFragment = (ModelFragment) fragmentManager.findFragmentByTag("ModelFragment");
        if (modelFragment == null) {
            modelFragment = new ModelFragment();

            fTransaction.add(modelFragment, "ModelFragment");
        }
        fTransaction.commit();
        return modelFragment;
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


    public void setMovieAdapter(RecyclerView.Adapter adapter) {
        movieAdapter = adapter;
    }

    public void setTVShowAdapter(RecyclerView.Adapter adapter) {
        tvshowAdapter = adapter;
    }

    public void setPeopleAdapter(RecyclerView.Adapter adapter) {
        personAdapter = adapter;
    }


}
