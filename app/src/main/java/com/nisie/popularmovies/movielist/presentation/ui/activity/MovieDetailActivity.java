package com.nisie.popularmovies.movielist.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

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
import com.nisie.popularmovies.movielist.presentation.model.MovieTrailerViewModel;
import com.nisie.popularmovies.movielist.presentation.model.ReviewViewModel;
import com.nisie.popularmovies.movielist.presentation.presenter.MovieDetailPresenter;
import com.nisie.popularmovies.movielist.presentation.presenter.MovieDetailPresenterImpl;
import com.nisie.popularmovies.movielist.presentation.ui.adapter.ReviewsAdapter;
import com.nisie.popularmovies.movielist.presentation.ui.adapter.TrailerAdapter;

import java.util.ArrayList;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailPresenter.View {

    private static final String ARGS_MOVIE = "ARGS_MOVIE";

    MovieItem movieItem;
    MovieDetailPresenter presenter;
    ActivityMovieDetailBinding binding;
    ReviewViewModel reviewViewModel = new ReviewViewModel();

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
            binding.setReviewList(reviewViewModel);

            presenter.getTrailers(movieItem.getId());
            presenter.getReviews(movieItem.getId());
        }

    }

    private void initView() {
        setContentView(getLayoutId());

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
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

    @Override
    public void showLoadingTrailers() {
    }

    @Override
    public void onErrorGetTrailer(int resId) {

    }

    @Override
    public void onSuccessGetTrailer(ArrayList<MovieTrailerViewModel> movieTrailerViewModels) {

    }

    @Override
    public void finishLoadingTrailer() {
    }

    @Override
    public void showLoadingReviews() {

    }

    @Override
    public void onErrorGetReviews(int resId) {

    }

    @Override
    public void onSuccessGetReviews(ReviewViewModel reviewViewModel) {
        this.reviewViewModel.setReviewList(reviewViewModel.getListReview());
        binding.notifyPropertyChanged(R.id.rv_reviews);
    }

    @Override
    public void finishLoadingReviews() {
    }
}
