package com.nisie.popularmovies.movielist.presentation.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nisie.popularmovies.databinding.ItemTrailerBinding;
import com.nisie.popularmovies.movielist.presentation.model.MovieReviewViewModel;
import com.nisie.popularmovies.movielist.presentation.model.MovieTrailerViewModel;
import com.nisie.popularmovies.util.BaseRecyclerViewAdapter;

import java.util.ArrayList;

/**
 * @author by nisie on 7/29/17.
 */

public class TrailerAdapter extends BaseRecyclerViewAdapter {

    private static final int TYPE_TRAILER = 102;
    private final Context context;
    private ArrayList<MovieTrailerViewModel> list;

    public TrailerAdapter(Context context, ObservableArrayList<MovieTrailerViewModel> list) {
        this.list = list;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemTrailerBinding binding;

        public ViewHolder(ItemTrailerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(TrailerAdapter trailerAdapter, MovieTrailerViewModel item) {
            binding.setTrailer(item);
            binding.setTrailerAdapter(trailerAdapter);
            binding.executePendingBindings();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case TYPE_TRAILER:
                ItemTrailerBinding itemBinding =
                        ItemTrailerBinding.inflate(inflater, parent, false);
                return new ViewHolder(itemBinding);
            default:
                return super.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        MovieTrailerViewModel item = list.get(position);
        viewHolder.bind(this, item);
    }

    @Override
    public int getItemCount() {
        return list.size() + super.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoading() && position == list.size())
            return super.getItemViewType(position);
        else
            return TYPE_TRAILER;
    }

    public void setList(ArrayList<MovieTrailerViewModel> list) {
        this.list.clear();
        this.list.addAll(list);
    }

    public void onItemClick(MovieTrailerViewModel item) {
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(item.getVideoUrl())));
    }

    public void onShareClick(MovieTrailerViewModel item) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, item.getVideoUrl());
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
    }
}
