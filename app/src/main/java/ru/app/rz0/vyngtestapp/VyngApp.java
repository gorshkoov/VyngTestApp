package ru.app.rz0.vyngtestapp;

import android.app.Application;
import ru.app.rz0.vyngtestapp.di.AppComponent;
import ru.app.rz0.vyngtestapp.di.AppModule;
import ru.app.rz0.vyngtestapp.di.DaggerAppComponent;

public class VyngApp extends Application {

  public static AppComponent appComponent;

  @Override
  public void onCreate() {
    super.onCreate();
    initAppComponents();
  }

  private void initAppComponents() {
    appComponent = DaggerAppComponent
        .builder()
        .appModule(new AppModule(this))
        .build();
  }
}
