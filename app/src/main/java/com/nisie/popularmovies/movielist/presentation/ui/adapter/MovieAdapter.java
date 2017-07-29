package com.nisie.popularmovies.movielist.presentation.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nisie.popularmovies.R;
import com.nisie.popularmovies.movielist.presentation.model.MovieItem;
import com.nisie.popularmovies.movielist.presentation.presenter.MovieListPresenter;
import com.nisie.popularmovies.util.BaseRecyclerViewAdapter;
import com.nisie.popularmovies.util.ImageHandler;

import java.util.ArrayList;

/**
 * @author by natha on 6/24/2017.
 */

public class MovieAdapter extends BaseRecyclerViewAdapter {

    private static final int TYPE_MOVIE = 102;

    private final MovieListPresenter.View listener;
    private ArrayList<MovieItem> listMovie;

    public void clearList() {
        this.listMovie.clear();
        notifyDataSetChanged();
    }


    public ArrayList<MovieItem> getList() {
        return listMovie;
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
            case TYPE_MOVIE:
                view = inflater.inflate(R.layout.item_movie, parent, false);
                return new ViewHolder(view);
            default:
                return super.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_MOVIE) {
            ViewHolder movieHolder = (ViewHolder) holder;
            ImageHandler.loadImageFromUrl(movieHolder.ivMovie, listMovie.get(position).getImgUrl());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoading() && position == listMovie.size())
            return super.getItemViewType(position);
        else
            return TYPE_MOVIE;
    }

    @Override
    public int getItemCount() {
        return listMovie.size() + super.getItemCount();
    }

    public void addList(ArrayList<MovieItem> listMovie) {
        this.listMovie.addAll(listMovie);
        notifyDataSetChanged();
    }
}
