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
import com.nisie.popularmovies.main.presentation.util.SpacesItemDecoration;
import com.nisie.popularmovies.movielist.domain.interactor.GetMovieListUseCase;
import com.nisie.popularmovies.movielist.domain.mapper.MovieListMapper;
import com.nisie.popularmovies.movielist.domain.mapper.MovieReviewMapper;
import com.nisie.popularmovies.movielist.domain.mapper.MovieTrailerMapper;
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
    private static final String ARGS_DATA = "ARGS_DATA";
    RecyclerView rvMovie;
    GridLayoutManager layoutManager;
    MovieAdapter adapter;

    MovieListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initPresenter();
        initData(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(ARGS_DATA, adapter.getList());
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
                new MovieListMapper(),
                new MovieTrailerMapper(),
                new MovieReviewMapper());
        GetMovieListUseCase getMovieListUseCase = new GetMovieListUseCase(
                new JobExecutor(),
                new UIThread(),
                repository);
        presenter = new MovieListPresenterImpl(
                this, getMovieListUseCase);
    }

    private void initData(Bundle savedInstanceState) {
        if (savedInstanceState != null
                && savedInstanceState.getParcelableArrayList(ARGS_DATA) != null) {
            onSuccessGetMovieList(savedInstanceState.<MovieItem>getParcelableArrayList(ARGS_DATA));
        } else {
            presenter.getMovieList();
        }
    }

    private void initView() {
        setContentView(R.layout.activity_movie_list);
        rvMovie = (RecyclerView) findViewById(R.id.rv_movies);
        adapter = MovieAdapter.createInstance(this);
        layoutManager = new GridLayoutManager(this,
                GRID_SPAN,
                GridLayoutManager.VERTICAL,
                false);
        rvMovie.setLayoutManager(layoutManager);
        rvMovie.setAdapter(adapter);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.gap_movie);
        rvMovie.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        rvMovie.addOnScrollListener(onScroll());
    }

    private RecyclerView.OnScrollListener onScroll() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                int lastItemPosition = layoutManager.findLastVisibleItemPosition();
//                int visibleItem = layoutManager.getItemCount() - 1;
//                if (!adapter.isLoading())
//                    presenter.loadMore(lastItemPosition, visibleItem);
            }
        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_highest_rated:
                adapter.clearList();
                presenter.getHighestRated();
                return true;
            case R.id.sort_most_popular:
                adapter.clearList();
                presenter.getMostPopular();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void goToDetail(MovieItem movieItem) {
        startActivity(MovieDetailActivity.getCallingIntent(this, movieItem));
    }

    @Override
    public void onSuccessGetMovieList(ArrayList<MovieItem> list) {
        adapter.finishLoading();
        adapter.addList(list);
    }

    @Override
    public void onErrorGetMovieList(int resId) {
        adapter.finishLoading();
        Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        adapter.showLoading();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unbind();
    }
}
