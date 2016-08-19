package hu.gergelyszaz.blackswanandroidtestapp.adapter;

import android.content.res.Resources;
import android.view.View;

import java.util.List;

import hu.gergelyszaz.blackswanandroidtestapp.ListViewFragment.OnListFragmentInteractionListener;
import hu.gergelyszaz.blackswanandroidtestapp.R;
import hu.gergelyszaz.blackswanandroidtestapp.model.Person;
import hu.gergelyszaz.blackswanandroidtestapp.network.DownloadImageTask;


public class PersonRecyclerViewAdapter extends RecyclerViewAdapter {
    private final List<Person> people;

    public PersonRecyclerViewAdapter(List<Person> items, OnListFragmentInteractionListener listener) {
        super(listener);
        people = items;
    }

    @Override
    public void onBindViewHolder(final CardViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        Person person = people.get(position);
        holder.title.setText(person.getTitle());
        holder.description.setText(person.getDescription());
        holder.rating.setVisibility(View.GONE);
        holder.date.setVisibility(View.GONE);
        holder.more.setVisibility(View.GONE);
        holder.item = person;

        if (person.getImageURL() != "null") {
            Resources resources = holder.view.getResources();
            String postersURL = resources.getString(R.string.url_posters);
            String api_key = resources.getString(R.string.api_key);
            String url = postersURL + person.getImageURL() + "?api_key=" + api_key;
            new DownloadImageTask(holder.image).execute(url);
        } else {
            holder.image.setImageBitmap(null);
        }
    }

    @Override
    public int getItemCount() {
        return people.size();
    }


}
