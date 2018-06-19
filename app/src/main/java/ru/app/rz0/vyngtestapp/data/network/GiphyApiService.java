package ru.app.rz0.vyngtestapp.data.network;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.app.rz0.vyngtestapp.models.search.GiphySearchResponse;

public interface GiphyApiService {

  @GET("v1/gifs/search")
  Single<GiphySearchResponse> search(@Query("api_key") String key, @Query("q") String query,
      @Query("limit") int limit);
}
