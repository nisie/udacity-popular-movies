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

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_LOADING = 101;
    private static final int TYPE_MOVIE = 102;

    private final MovieListPresenter.View listener;
    private ArrayList<MovieItem> listMovie;
    private int loading = 0;

    public void showLoading() {
        this.loading = 1;
        notifyDataSetChanged();
    }

    public void clearList() {
        this.listMovie.clear();
        notifyDataSetChanged();
    }

    public void finishLoading() {
        this.loading = 0;
        notifyDataSetChanged();
    }

    public boolean isLoading() {
        return loading == 1;
    }

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

    class LoadingViewHolder extends RecyclerView.ViewHolder {

        LoadingViewHolder(View itemView) {
            super(itemView);
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        switch (viewType) {
            case TYPE_LOADING:
                view = inflater.inflate(R.layout.item_loading, parent, false);
                return new LoadingViewHolder(view);
            default:
                view = inflater.inflate(R.layout.item_movie, parent, false);
                return new ViewHolder(view);

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) != TYPE_LOADING) {
            ViewHolder movieHolder = (ViewHolder) holder;
            ImageHandler.loadImageFromUrl(movieHolder.ivMovie, listMovie.get(position).getImgUrl());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (loading == 1 && position == listMovie.size())
            return TYPE_LOADING;
        else
            return TYPE_MOVIE;
    }

    @Override
    public int getItemCount() {
        return listMovie.size() + loading;
    }

    public void addList(ArrayList<MovieItem> listMovie) {
        this.listMovie.addAll(listMovie);
        notifyDataSetChanged();
    }
}
