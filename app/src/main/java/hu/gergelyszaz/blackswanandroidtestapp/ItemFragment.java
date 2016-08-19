package hu.gergelyszaz.blackswanandroidtestapp;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hu.gergelyszaz.blackswanandroidtestapp.adapter.MovieRecyclerViewAdapter;
import hu.gergelyszaz.blackswanandroidtestapp.adapter.PersonRecyclerViewAdapter;
import hu.gergelyszaz.blackswanandroidtestapp.adapter.TVRecyclerViewAdapter;
import hu.gergelyszaz.blackswanandroidtestapp.model.Item;
import hu.gergelyszaz.blackswanandroidtestapp.model.Movie;
import hu.gergelyszaz.blackswanandroidtestapp.model.Person;
import hu.gergelyszaz.blackswanandroidtestapp.model.TVShow;
import hu.gergelyszaz.blackswanandroidtestapp.network.TheMovieDB;


public class ItemFragment extends Fragment {
    private final static String CONTENT="list_content";
    private OnListFragmentInteractionListener onListFragmentInteractionListener;

    public ItemFragment() {    }

    public static ItemFragment newInstance(int type) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt("type", type);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            ModelFragment modelFragment = ModelFragment.getModelFragment(getFragmentManager());
            int type = getArguments().getInt("type", 0);
            RecyclerView.Adapter adapter = null;
            switch (type) {
                case TheMovieDB.MOVIES:
                    List<Movie> movies = modelFragment.getMovies();
                    adapter = new MovieRecyclerViewAdapter(movies, onListFragmentInteractionListener);
                    modelFragment.setMovieAdapter(adapter);
                    break;
                case TheMovieDB.PEOPLE:
                    List<Person> people = modelFragment.getPeople();
                    adapter = new PersonRecyclerViewAdapter(people, onListFragmentInteractionListener);
                    modelFragment.setPeopleAdapter(adapter);
                    break;
                case TheMovieDB.TV:
                    List<TVShow> tvshows = modelFragment.getTVShows();
                    adapter = new TVRecyclerViewAdapter(tvshows, onListFragmentInteractionListener);
                    modelFragment.setTVShowAdapter(adapter);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            recyclerView.setAdapter(adapter);
        }
        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            onListFragmentInteractionListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onListFragmentInteractionListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Item item);
    }
}
