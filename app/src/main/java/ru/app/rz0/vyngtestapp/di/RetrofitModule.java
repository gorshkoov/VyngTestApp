package ru.app.rz0.vyngtestapp.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.app.rz0.vyngtestapp.data.network.GiphyApiService;
import ru.app.rz0.vyngtestapp.data.network.GiphySearchDeserializer;
import ru.app.rz0.vyngtestapp.models.search.GiphySearchResponse;

@Module
public class RetrofitModule {

  private static final String BASE_URL = "https://api.giphy.com/";
  private static final int TIMEOUT = 20;

  @Provides
  @Singleton
  Gson providesGson() {
    return new GsonBuilder()
        .registerTypeAdapter(GiphySearchResponse.class, new GiphySearchDeserializer())
        .create();
  }

  @Provides
  @Singleton
  OkHttpClient providesOkHttp() {
    OkHttpClient.Builder builder = new Builder();

    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(Level.BODY);

    builder.addInterceptor(interceptor)
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS);
    return builder.build();
  }

  @Provides
  @Singleton
  Retrofit providesRetrofit(OkHttpClient client, Gson gson) {
    Retrofit.Builder builder = new Retrofit.Builder();
    builder.baseUrl(BASE_URL)
        .client(client)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson));

    return builder.build();
  }

  @Provides
  @Singleton
  GiphyApiService providesGiphyApiService(Retrofit retrofit) {
    return retrofit.create(GiphyApiService.class);
  }

}
