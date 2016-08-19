package hu.gergelyszaz.blackswanandroidtestapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hu.gergelyszaz.blackswanandroidtestapp.ItemFragment.OnListFragmentInteractionListener;
import hu.gergelyszaz.blackswanandroidtestapp.R;


public abstract class RecyclerViewAdapter extends RecyclerView.Adapter<CardViewHolder> {

    private final OnListFragmentInteractionListener listFragmentInteractionListener;

    public RecyclerViewAdapter(OnListFragmentInteractionListener listener) {
        listFragmentInteractionListener = listener;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CardViewHolder holder, int position) {
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listFragmentInteractionListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    listFragmentInteractionListener.onListFragmentInteraction(holder.item);
                }
            }
        });
    }
}
