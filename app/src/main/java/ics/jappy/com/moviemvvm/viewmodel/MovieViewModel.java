/**
 * Created by Irene on 12-05-17.
 */

package ics.jappy.com.moviemvvm.viewmodel;

import android.content.Context;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import ics.jappy.com.moviemvvm.MovieApplications;
import ics.jappy.com.moviemvvm.data.MovieFactory;
import ics.jappy.com.moviemvvm.data.MovieResponse;
import ics.jappy.com.moviemvvm.data.MovieService;
import ics.jappy.com.moviemvvm.model.Movie;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MovieViewModel extends Observable {

 // public ObservableInt peopleProgress;
  public ObservableInt peopleRecycler;
 // public ObservableInt peopleLabel;
 // public ObservableField<String> messageLabel;

  private List<Movie> movieList;
  private Context context;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();

  public MovieViewModel(@NonNull Context context) {

    this.context = context;
    this.movieList = new ArrayList<>();
   // peopleProgress = new ObservableInt(View.GONE);
    peopleRecycler = new ObservableInt(View.GONE);
    //peopleLabel = new ObservableInt(View.VISIBLE);
  }

  public void onClickFabLoad(View view) {
    initializeViews();
    fetchMovieList();
  }

  //It is "public" to show an example of test
  public void initializeViews() {
   // peopleLabel.set(View.GONE);
    peopleRecycler.set(View.GONE);
   // peopleProgress.set(View.VISIBLE);
  }

  public void fetchMovieList() {

    MovieApplications peopleApplication = MovieApplications.create(context);
    MovieService movieService = peopleApplication.getMovieService();

    Disposable disposable = movieService.fetchMovie(MovieFactory.MOVIE_URL)
        .subscribeOn(peopleApplication.subscribeScheduler())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<MovieResponse>() {
          @Override public void accept(MovieResponse movieResponse) throws Exception {
              Log.e("good", movieResponse.getMovieList().toString());
              changeMovieDataSet(movieResponse.getMovieList());

            peopleRecycler.set(View.VISIBLE);
          }
        }, new Consumer<Throwable>() {
          @Override public void accept(Throwable throwable) throws Exception {
              Log.e("throwable", throwable.getMessage());


            peopleRecycler.set(View.GONE);
          }
        });

    compositeDisposable.add(disposable);
  }

  private void changeMovieDataSet(List<Movie> movies) {
    movieList.addAll(movies);
    setChanged();
    notifyObservers();
  }

  public List<Movie> getMovieList() {
    return movieList;
  }

  private void unSubscribeFromObservable() {
    if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
      compositeDisposable.dispose();
    }
  }

  public void reset() {
    unSubscribeFromObservable();
    compositeDisposable = null;
    context = null;
  }
}
