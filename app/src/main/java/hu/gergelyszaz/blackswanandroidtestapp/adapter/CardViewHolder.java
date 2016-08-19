package hu.gergelyszaz.blackswanandroidtestapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import hu.gergelyszaz.blackswanandroidtestapp.R;
import hu.gergelyszaz.blackswanandroidtestapp.model.Item;

/**
 * Created by mad on 2016. 08. 19..
 */
public class CardViewHolder extends RecyclerView.ViewHolder {

    public final TextView description;
    public final TextView subtitle;
    public final TextView rating;
    public final ImageView image;
    public final TextView title;
    public final TextView date;
    public final TextView more;
    public final View view;
    public Item item;


    public CardViewHolder(View view) {
        super(view);
        this.view = view;

        description = (TextView) view.findViewById(R.id.description);
        subtitle = (TextView) view.findViewById(R.id.subtitle);
        rating = (TextView) view.findViewById(R.id.rating);
        image = (ImageView) view.findViewById(R.id.image);
        title = (TextView) view.findViewById(R.id.title);
        date = (TextView) view.findViewById(R.id.date);
        more = (TextView) view.findViewById(R.id.more);
    }

    @Override
    public String toString() {
        return super.toString() + " '" + description.getText() + "'";
    }
}