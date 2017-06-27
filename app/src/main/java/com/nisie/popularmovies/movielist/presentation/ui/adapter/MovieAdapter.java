package com.nisie.popularmovies.movielist.presentation.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nisie.popularmovies.R;
import com.nisie.popularmovies.movielist.presentation.model.MovieItem;
import com.nisie.popularmovies.movielist.presentation.presenter.MovieListPresenter;
import com.nisie.popularmovies.util.ImageHandler;

import java.util.ArrayList;

/**
 * @author by natha on 6/24/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private final MovieListPresenter.View listener;
    private ArrayList<MovieItem> listMovie;

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivMovie;

        ViewHolder(View itemView) {
            super(itemView);
            ivMovie = itemView.findViewById(R.id.iv_movie);
            ivMovie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.goToDetail(listMovie.get(getAdapterPosition()));
                }
            });
        }
    }

    public static MovieAdapter createInstance(MovieListPresenter.View movieListView) {
        return new MovieAdapter(movieListView);
    }

    private MovieAdapter(MovieListPresenter.View listener) {
        this.listMovie = new ArrayList<>();
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageHandler.loadImageFromUrl(holder.ivMovie, listMovie.get(position).getImgUrl());
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    public void addList(ArrayList<MovieItem> listMovie) {
        this.listMovie.addAll(listMovie);
        notifyDataSetChanged();
    }
}
