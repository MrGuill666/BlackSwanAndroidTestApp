package hu.gergelyszaz.blackswanandroidtestapp.adapter;

import android.content.res.Resources;

import java.util.List;

import hu.gergelyszaz.blackswanandroidtestapp.ItemFragment.OnListFragmentInteractionListener;
import hu.gergelyszaz.blackswanandroidtestapp.R;
import hu.gergelyszaz.blackswanandroidtestapp.model.Movie;
import hu.gergelyszaz.blackswanandroidtestapp.network.DownloadImageTask;


public class MovieRecyclerViewAdapter extends RecyclerViewAdapter {
    private final List<Movie> movies;

    public MovieRecyclerViewAdapter(List<Movie> items, OnListFragmentInteractionListener listener) {
        super(listener);
        movies = items;
    }

    @Override
    public void onBindViewHolder(final CardViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        Movie movie = movies.get(position);
        holder.description.setText(movie.getDescription());
        holder.item = movie;
        holder.title.setText(movie.getTitle());
        holder.rating.setText(movie.getRating());
        holder.date.setText(movie.getDate());

        if (movie.getImageURL() != "null") {
            Resources resources = holder.view.getResources();
            String postersURL = resources.getString(R.string.url_posters);
            String api_key = resources.getString(R.string.api_key);
            String url = postersURL + movie.getImageURL() + "?api_key=" + api_key;
            new DownloadImageTask(holder.image).execute(url);
        } else {
            holder.image.setImageBitmap(null);
        }

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


}
