package com.nisie.popularmovies.movielist.presentation.ui.adapter;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nisie.popularmovies.databinding.ItemReviewBinding;
import com.nisie.popularmovies.movielist.presentation.model.MovieReviewViewModel;
import com.nisie.popularmovies.util.BaseRecyclerViewAdapter;

import java.util.ArrayList;

/**
 * @author by nisie on 7/29/17.
 */

public class ReviewsAdapter extends BaseRecyclerViewAdapter {

    private static final int TYPE_REVIEW = 102;
    private final Context context;
    private ArrayList<MovieReviewViewModel> list;

    public ReviewsAdapter(Context context, ObservableArrayList<MovieReviewViewModel> list) {
        this.list = list;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemReviewBinding binding;

        public ViewHolder(ItemReviewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(MovieReviewViewModel item, ReviewsAdapter reviewsAdapter) {
            binding.setReview(item);
            binding.setReviewAdapter(reviewsAdapter);
            binding.executePendingBindings();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case TYPE_REVIEW:
                ItemReviewBinding itemBinding =
                        ItemReviewBinding.inflate(inflater, parent, false);
                return new ViewHolder(itemBinding);
            default:
                return super.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        MovieReviewViewModel item = list.get(position);
        viewHolder.bind(item, this);
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
            return TYPE_REVIEW;
    }

    public void onItemClick(MovieReviewViewModel item) {

    }
}
