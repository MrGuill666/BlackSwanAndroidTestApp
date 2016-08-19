package hu.gergelyszaz.blackswanandroidtestapp.adapter;

import android.content.res.Resources;

import java.util.List;

import hu.gergelyszaz.blackswanandroidtestapp.ItemFragment.OnListFragmentInteractionListener;
import hu.gergelyszaz.blackswanandroidtestapp.R;
import hu.gergelyszaz.blackswanandroidtestapp.model.TVShow;
import hu.gergelyszaz.blackswanandroidtestapp.network.DownloadImageTask;


public class TVRecyclerViewAdapter extends RecyclerViewAdapter {
    private final List<TVShow> tvshows;

    public TVRecyclerViewAdapter(List<TVShow> items, OnListFragmentInteractionListener listener) {
        super(listener);
        tvshows = items;
    }

    @Override
    public void onBindViewHolder(final CardViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        TVShow tvshow = tvshows.get(position);
        holder.title.setText(tvshow.getTitle());
        holder.description.setText(tvshow.getDescription());
        holder.rating.setText(tvshow.getRating());
        holder.date.setText(tvshow.getDate());
        if (tvshow.getImageURL() != "null") {
            Resources resources = holder.view.getResources();
            String postersURL = resources.getString(R.string.url_posters);
            String api_key = resources.getString(R.string.api_key);
            String url = postersURL + tvshow.getImageURL() + "?api_key=" + api_key;
            new DownloadImageTask(holder.image).execute(url);
        } else {
            holder.image.setImageBitmap(null);
        }
    }

    @Override
    public int getItemCount() {
        return tvshows.size();
    }


}
