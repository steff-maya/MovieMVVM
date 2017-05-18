package ics.jappy.com.moviemvvm.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Observable;
import java.util.Observer;

import ics.jappy.com.moviemvvm.R;
import ics.jappy.com.moviemvvm.databinding.MovieActivityBinding;
import ics.jappy.com.moviemvvm.viewmodel.MovieViewModel;

/**
 * Created by Irene on 12-05-17.
 */

public class MovieActivity  extends AppCompatActivity implements Observer {

    private MovieActivityBinding movieActivityBinding;
    private MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();

        setupListMovieView(movieActivityBinding.listMovie);
        setupObserver(movieViewModel);
    }


    private void initDataBinding() {
        movieActivityBinding = DataBindingUtil.setContentView(this, R.layout.movie_activity);
        movieViewModel = new MovieViewModel(this);
        movieActivityBinding.setMainViewModel(movieViewModel);
        movieViewModel.fetchMovieList();
    }

    private void setupListMovieView(RecyclerView listmovie) {

        MovieAdapter adapter = new MovieAdapter();
        listmovie.setAdapter(adapter);
        listmovie.setLayoutManager(new GridLayoutManager(this, 2));

    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        movieViewModel.reset();
    }







    @Override public void update(Observable observable, Object data) {
        if (observable instanceof MovieViewModel) {
            MovieAdapter movieAdapter = (MovieAdapter) movieActivityBinding.listMovie.getAdapter();
            MovieViewModel movieViewModel = (MovieViewModel) observable;
            movieAdapter.setMovieList(movieViewModel.getMovieList());
        }
    }
}
