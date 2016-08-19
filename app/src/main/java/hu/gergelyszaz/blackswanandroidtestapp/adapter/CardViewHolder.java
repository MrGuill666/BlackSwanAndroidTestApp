package hu.gergelyszaz.blackswanandroidtestapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import hu.gergelyszaz.blackswanandroidtestapp.R;

/**
 * Created by mad on 2016. 08. 19..
 */
public class CardViewHolder extends RecyclerView.ViewHolder {

    public final View view;
    public final TextView title;
    public final TextView description;
    public final TextView rating;
    public final TextView date;
    public final ImageView image;
    public Object item;


    public CardViewHolder(View view) {
        super(view);
        this.view = view;

        title = (TextView) view.findViewById(R.id.title);
        description = (TextView) view.findViewById(R.id.content);
        date = (TextView) view.findViewById(R.id.date);
        rating = (TextView) view.findViewById(R.id.rating);
        image = (ImageView) view.findViewById(R.id.image);
    }

    @Override
    public String toString() {
        return super.toString() + " '" + description.getText() + "'";
    }
}