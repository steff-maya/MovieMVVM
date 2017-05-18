/**
 * Created by Irene on 12-05-17.
 */
package ics.jappy.com.moviemvvm.data;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface MovieService {

  @GET Observable<MovieResponse> fetchMovie(@Url String url);

}
