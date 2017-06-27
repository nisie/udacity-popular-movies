package com.nisie.popularmovies.movielist.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nisie.popularmovies.R;
import com.nisie.popularmovies.main.domain.executor.ThreadExecutor;
import com.nisie.popularmovies.movielist.presentation.model.MovieItem;
import com.nisie.popularmovies.movielist.presentation.presenter.MovieListPresenter;
import com.nisie.popularmovies.movielist.presentation.presenter.MovieListPresenterImpl;
import com.nisie.popularmovies.util.ImageHandler;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String ARGS_MOVIE = "ARGS_MOVIE";

    ImageView ivMovie;
    TextView tvTitle;
    TextView tvDate;
    TextView tvSynopsis;
    RatingBar rating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        if (getIntent().getExtras() != null
                && getIntent().getExtras().getParcelable(ARGS_MOVIE) != null) {
            MovieItem movieItem = getIntent().getExtras().getParcelable(ARGS_MOVIE);
            ImageHandler.loadImageFromUrl(ivMovie,
                    movieItem != null ? movieItem.getImgUrl() : "");
            tvTitle.setText(movieItem != null ? movieItem.getTitle() : "");
            tvSynopsis.setText(movieItem != null ? movieItem.getSynopsis() : "");
            tvDate.setText(movieItem != null ? movieItem.getReleaseDate() : "");
            rating.setRating(movieItem != null ? movieItem.getRating() : 0);
        }
    }

    private void initView() {
        setContentView(R.layout.activity_movie_detail);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ivMovie = (ImageView) findViewById(R.id.iv_movie);
        tvDate = (TextView) findViewById(R.id.tv_date);
        tvSynopsis = (TextView) findViewById(R.id.tv_synopsis);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        rating = (RatingBar) findViewById(R.id.rating);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static Intent getCallingIntent(Context context, MovieItem movieItem) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(ARGS_MOVIE, movieItem);
        return intent;
    }
}
