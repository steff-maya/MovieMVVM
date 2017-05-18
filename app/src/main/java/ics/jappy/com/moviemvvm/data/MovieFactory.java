/**
 * Created by Irene on 12-05-17.
 */
package ics.jappy.com.moviemvvm.data;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieFactory {

  private final static String BASE_URL = "https://api.themoviedb.org/3/";
  public final static String MOVIE_URL = "https://api.themoviedb.org/3/discover/movie?api_key=f0b75461862daed138ba708c036f5863";
  public final static String MOVIE_IMG_URL = "https://image.tmdb.org/t/p/w370_and_h556_bestv2";

  public static MovieService create() {
    Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();
    return retrofit.create(MovieService.class);
  }
}
