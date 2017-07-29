package com.nisie.popularmovies.util;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nisie.popularmovies.R;

/**
 * @author by nisie on 7/29/17.
 */

public abstract class BaseRecyclerViewAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String ERROR_UNHANDLED_TYPE = "Unhandled view type";
    private int loading = 0;
    private static final int TYPE_LOADING = 101;

    class LoadingViewHolder extends RecyclerView.ViewHolder {

        LoadingViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void showLoading() {
        this.loading = 1;
        notifyDataSetChanged();
    }

    public void finishLoading() {
        this.loading = 0;
        notifyDataSetChanged();
    }

    public boolean isLoading() {
        return loading == 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        switch (viewType) {
            case TYPE_LOADING:
                view = inflater.inflate(R.layout.item_loading, parent, false);
                return new LoadingViewHolder(view);
            default:
                throw new RuntimeException(ERROR_UNHANDLED_TYPE);

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoading())
            return TYPE_LOADING;
        else
            return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return loading;
    }
}
