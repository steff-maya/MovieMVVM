/**
 * Created by Irene on 12-05-17.
 */
package ics.jappy.com.moviemvvm.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {

  @SerializedName("poster_path") public String poster_path;

  @SerializedName("title") public String title;

  @SerializedName("vote_average") public String vote_average;






}
