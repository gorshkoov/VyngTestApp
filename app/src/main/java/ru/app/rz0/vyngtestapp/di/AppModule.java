package ru.app.rz0.vyngtestapp.di;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import ru.app.rz0.vyngtestapp.base.AppSchedulers;

@Module
public class AppModule {

  private Context context;

  public AppModule(Context context) {
    this.context = context.getApplicationContext();
  }

  @Provides
  @Singleton
  Context provideAppContext() {
    return context;
  }

  @Provides
  @Singleton
  AppSchedulers provideAppSchedulers() {
    return new AppSchedulers();
  }

}
