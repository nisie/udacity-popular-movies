package com.nisie.popularmovies.movielist.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.nisie.popularmovies.R;
import com.nisie.popularmovies.databinding.ActivityMovieDetailBinding;
import com.nisie.popularmovies.main.domain.executor.JobExecutor;
import com.nisie.popularmovies.main.presentation.UIThread;
import com.nisie.popularmovies.movielist.domain.interactor.GetMovieReviewsUseCase;
import com.nisie.popularmovies.movielist.domain.interactor.GetMovieTrailerUseCase;
import com.nisie.popularmovies.movielist.domain.mapper.MovieListMapper;
import com.nisie.popularmovies.movielist.domain.mapper.MovieReviewMapper;
import com.nisie.popularmovies.movielist.domain.mapper.MovieTrailerMapper;
import com.nisie.popularmovies.movielist.domain.network.service.MovieService;
import com.nisie.popularmovies.movielist.domain.repository.MovieListRepository;
import com.nisie.popularmovies.movielist.domain.repository.MovieListRepositoryImpl;
import com.nisie.popularmovies.movielist.presentation.model.MovieItem;
import com.nisie.popularmovies.movielist.presentation.model.MovieReviewViewModel;
import com.nisie.popularmovies.movielist.presentation.model.MovieTrailerViewModel;
import com.nisie.popularmovies.movielist.presentation.presenter.MovieDetailPresenter;
import com.nisie.popularmovies.movielist.presentation.presenter.MovieDetailPresenterImpl;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailPresenter.View {

    private static final String ARGS_MOVIE = "ARGS_MOVIE";

    View reviewView;
    View trailerView;

    MovieItem movieItem;
    MovieDetailPresenter presenter;
    ActivityMovieDetailBinding binding;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initPresenter();
        initData(savedInstanceState);
    }

    private void initPresenter() {

        MovieListRepository repository = new MovieListRepositoryImpl(
                new MovieService(),
                new MovieListMapper(),
                new MovieTrailerMapper(),
                new MovieReviewMapper());
        GetMovieTrailerUseCase getMovieTrailerUseCase = new GetMovieTrailerUseCase(
                new JobExecutor(),
                new UIThread(),
                repository);

        GetMovieReviewsUseCase getMovieReviewsUseCase = new GetMovieReviewsUseCase(
                new JobExecutor(),
                new UIThread(),
                repository);

        presenter = new MovieDetailPresenterImpl(this,
                getMovieTrailerUseCase,
                getMovieReviewsUseCase);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unbind();
    }

    public int getLayoutId() {
        return R.layout.activity_movie_detail;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (movieItem != null)
            outState.putParcelable(ARGS_MOVIE, movieItem);
    }

    private void initData(Bundle savedInstanceState) {
        if (getIntent().getExtras() != null
                && getIntent().getExtras().getParcelable(ARGS_MOVIE) != null) {
            movieItem = getIntent().getExtras().getParcelable(ARGS_MOVIE);
        } else if (savedInstanceState.getParcelable(ARGS_MOVIE) != null) {
            movieItem = savedInstanceState.getParcelable(ARGS_MOVIE);
        }

        if (movieItem != null) {
            binding = DataBindingUtil.setContentView(this, getLayoutId());
            String date = getString(R.string.release_date) + movieItem.getReleaseDate();
            movieItem.setReleaseDate(date);
            binding.setMovie(movieItem);

            presenter.getTrailers(movieItem.getId());
            presenter.getReviews(movieItem.getId());
        }

    }

    private void initView() {
        setContentView(getLayoutId());

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        reviewView = findViewById(R.id.view_review);
        trailerView = findViewById(R.id.view_trailer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_movie_detail, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.favorite:
                onFavoriteClicked(item);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void onFavoriteClicked(MenuItem item) {
        if (item.getTitle().equals(getResources().getString(R.string.favorite))) {
            item.setTitle(getResources().getString(R.string.favorited));
            item.setIcon(getResources().getDrawable(R.drawable.ic_star_white_24dp));
        } else {
            item.setTitle(getResources().getString(R.string.favorite));
            item.setIcon(getResources().getDrawable(R.drawable.ic_star_border_white_24dp));
        }
    }

    public static Intent getCallingIntent(Context context, MovieItem movieItem) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(ARGS_MOVIE, movieItem);
        return intent;
    }

    @Override
    public void showLoadingTrailers() {
        trailerView.setVisibility(View.GONE);
    }

    @Override
    public void onErrorGetTrailer(int resId) {
        trailerView.setVisibility(View.GONE);
    }

    @Override
    public void onSuccessGetTrailer(ObservableArrayList<MovieTrailerViewModel> movieTrailerViewModels) {
        this.movieItem.setListTrailer(movieTrailerViewModels);
        binding.notifyPropertyChanged(R.id.rv_trailers);
    }

    @Override
    public void finishLoadingTrailer() {
        trailerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoadingReviews() {
        reviewView.setVisibility(View.GONE);

    }

    @Override
    public void onErrorGetReviews(int resId) {
        reviewView.setVisibility(View.GONE);
    }

    @Override
    public void onSuccessGetReviews(ObservableArrayList<MovieReviewViewModel> reviewViewModel) {
        this.movieItem.setListReview(reviewViewModel);
        binding.notifyPropertyChanged(R.id.rv_reviews);
    }

    @Override
    public void finishLoadingReviews() {
        reviewView.setVisibility(View.VISIBLE);
    }
}
