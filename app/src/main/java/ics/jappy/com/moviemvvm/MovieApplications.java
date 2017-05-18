package ics.jappy.com.moviemvvm;

import android.app.Application;
import android.content.Context;

import ics.jappy.com.moviemvvm.data.MovieFactory;
import ics.jappy.com.moviemvvm.data.MovieService;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Irene on 12-05-17.
 */

public class MovieApplications extends Application {

    private MovieService movieService;
    private Scheduler scheduler;

    private static MovieApplications get(Context context) {
        return (MovieApplications) context.getApplicationContext();
    }

    public static MovieApplications create(Context context) {
        return MovieApplications.get(context);
    }

    public MovieService getMovieService() {
        if (movieService == null) {
            movieService = MovieFactory.create();
        }

        return movieService;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }

    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }



}
