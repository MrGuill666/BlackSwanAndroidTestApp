package hu.gergelyszaz.blackswanandroidtestapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.text.method.MovementMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ModelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModelFragment extends Fragment implements ItemList {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private List<Movie> movies = new ArrayList<>();

    public ModelFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ModelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ModelFragment newInstance(String param1, String param2) {
        ModelFragment fragment = new ModelFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        setRetainInstance(true);


        new TheMovieDB(this,TheMovieDB.MOVIES).getResponse(getString(R.string.url_movies_popular) + "?api_key=" + getString(R.string.api_key));
        new TheMovieDB(this,TheMovieDB.PEOPLE).getResponse(getString(R.string.url_people_popular) + "?api_key=" + getString(R.string.api_key));
        new TheMovieDB(this,TheMovieDB.TV).getResponse(getString(R.string.url_tv_popular) + "?api_key=" + getString(R.string.api_key));

    }


    public List<Movie> getMovies() {
        return movies;
    }


    @Override
    public void UpdateMovies(List<Movie> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
        if (movieAdapter != null)
            movieAdapter.notifyDataSetChanged();
    }

    @Override
    public void UpdateTV(List<Movie> movies) {

    }

    @Override
    public void UpdatePeople(List<Movie> movies) {

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

    RecyclerView.Adapter movieAdapter = null;

    public void RegisterMovieAdapter(RecyclerView.Adapter adapter) {
        movieAdapter = adapter;
    }
}
