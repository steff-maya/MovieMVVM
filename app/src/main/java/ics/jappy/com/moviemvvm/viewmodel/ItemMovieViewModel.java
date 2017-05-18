/**
 * Created by Irene on 12-05-17.
 */

package ics.jappy.com.moviemvvm.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import ics.jappy.com.moviemvvm.data.MovieFactory;
import ics.jappy.com.moviemvvm.model.Movie;


public class ItemMovieViewModel extends BaseObservable {

  private Movie movie;
  private Context context;

  public ItemMovieViewModel(Movie movie, Context context) {
    this.movie = movie;
    this.context = context;
  }



  public String getTittle() {
    return movie.title;
  }

  public String getPictureProfile() {return MovieFactory.MOVIE_IMG_URL+movie.poster_path;}

  public String getVote() {
    return movie.vote_average;
  }

  @BindingAdapter("imageUrl") public static void setImageUrl(ImageView imageView, String url) {
    Glide.with(imageView.getContext()).load(url).into(imageView);
  }

  public void onItemClick(View view) {
  }

  public void setMovie(Movie movie) {
    this.movie = movie;
    notifyChange();
  }
}
