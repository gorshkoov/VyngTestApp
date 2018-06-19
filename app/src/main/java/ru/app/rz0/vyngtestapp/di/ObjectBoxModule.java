package ru.app.rz0.vyngtestapp.di;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import io.objectbox.BoxStore;
import javax.inject.Singleton;
import ru.app.rz0.vyngtestapp.models.search.MyObjectBox;

@Module
public class ObjectBoxModule {

  @Provides
  @Singleton
  BoxStore providesObjectBox(Context context) {
    return MyObjectBox.builder()
        .androidContext(context)
        .build();
  }
}
