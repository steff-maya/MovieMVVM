/**
 * Created by Irene on 12-05-17.
 */
package ics.jappy.com.moviemvvm.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ics.jappy.com.moviemvvm.model.Movie;

public class MovieResponse {

  @SerializedName("results") private List<Movie> movieList;

  public List<Movie> getMovieList() {
    return movieList;
  }

  public void setMovieList(List<Movie> mMovieList) {
    this.movieList = mMovieList;
  }
}
