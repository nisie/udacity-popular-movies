package com.nisie.popularmovies.movielist.presentation.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.nisie.popularmovies.R;
import com.nisie.popularmovies.main.domain.executor.JobExecutor;
import com.nisie.popularmovies.main.presentation.UIThread;
import com.nisie.popularmovies.movielist.domain.interactor.GetMovieListUseCase;
import com.nisie.popularmovies.movielist.domain.mapper.MovieListMapper;
import com.nisie.popularmovies.movielist.domain.network.service.MovieService;
import com.nisie.popularmovies.movielist.domain.repository.MovieListRepository;
import com.nisie.popularmovies.movielist.domain.repository.MovieListRepositoryImpl;
import com.nisie.popularmovies.movielist.presentation.model.MovieItem;
import com.nisie.popularmovies.movielist.presentation.presenter.MovieListPresenter;
import com.nisie.popularmovies.movielist.presentation.presenter.MovieListPresenterImpl;
import com.nisie.popularmovies.movielist.presentation.ui.adapter.MovieAdapter;

import java.util.ArrayList;

public class MovieListActivity extends AppCompatActivity
        implements MovieListPresenter.View {

    private static final int GRID_SPAN = 3;
    private static final int DEFAULT_NO_SORT = 0;
    RecyclerView rvMovie;
    MovieAdapter adapter;

    MovieListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initPresenter();
        initData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_movielist, menu);
        return true;
    }

    private void initPresenter() {

        MovieListRepository repository = new MovieListRepositoryImpl(
                new MovieService(),
                new MovieListMapper());
        GetMovieListUseCase getMovieListUseCase = new GetMovieListUseCase(
                new JobExecutor(),
                new UIThread(),
                repository);
        presenter = new MovieListPresenterImpl(
                this, getMovieListUseCase);
    }

    private void initData() {
        presenter.getMovieList();
    }

    private void initView() {
        setContentView(R.layout.activity_movie_list);
        rvMovie = (RecyclerView) findViewById(R.id.rv_movies);
        adapter = MovieAdapter.createInstance(this);
        rvMovie.setLayoutManager(new GridLayoutManager(this,
                GRID_SPAN,
                GridLayoutManager.VERTICAL,
                false));
        rvMovie.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_highest_rated:
                loadData(R.id.sort_highest_rated);
                return true;
            case R.id.sort_most_popular:
                loadData(R.id.sort_most_popular);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadData(int sort_id) {
        if (sort_id != DEFAULT_NO_SORT) {
            presenter.getMovieList();
        } else {

        }
    }

    @Override
    public void goToDetail(MovieItem movieItem) {
        startActivity(MovieDetailActivity.getCallingIntent(this, movieItem));
    }

    @Override
    public void onSuccessGetMovieList(ArrayList<MovieItem> list) {
        adapter.addList(list);
    }

    @Override
    public void onErrorGetMovieList(int resId) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show();
    }
}
