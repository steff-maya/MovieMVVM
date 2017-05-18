package ics.jappy.com.moviemvvm.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import ics.jappy.com.moviemvvm.R;
import ics.jappy.com.moviemvvm.databinding.ItemMovieBinding;
import ics.jappy.com.moviemvvm.model.Movie;
import ics.jappy.com.moviemvvm.viewmodel.ItemMovieViewModel;
/**
 * Created by Irene on 12-05-17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {

  private List<Movie> movieList;

  public MovieAdapter() {
    this.movieList = Collections.emptyList();
  }

  @Override public MovieAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    ItemMovieBinding itemMovieBinding =
        DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_movie,
            parent, false);
    return new MovieAdapterViewHolder(itemMovieBinding);
  }

  @Override public void onBindViewHolder(MovieAdapterViewHolder holder, int position) {
    holder.bindMovie(movieList.get(position));
  }

  @Override public int getItemCount() {
    return movieList.size();
  }

  public void setMovieList(List<Movie> movieList) {
    this.movieList = movieList;
    notifyDataSetChanged();
  }

  public static class MovieAdapterViewHolder extends RecyclerView.ViewHolder {
    ItemMovieBinding mItemMovieBinding;

    public MovieAdapterViewHolder(ItemMovieBinding itemMovieBinding) {
      super(itemMovieBinding.itemMovie);
      this.mItemMovieBinding = itemMovieBinding;
    }

    void bindMovie(Movie people) {
      if (mItemMovieBinding.getMovieViewModel() == null) {
        mItemMovieBinding.setMovieViewModel(
            new ItemMovieViewModel(people, itemView.getContext()));
      } else {
        mItemMovieBinding.getMovieViewModel().setMovie(people);
      }
    }
  }
}
