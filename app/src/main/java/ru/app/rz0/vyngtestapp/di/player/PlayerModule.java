package ru.app.rz0.vyngtestapp.di.player;

import dagger.Module;
import dagger.Provides;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import ru.app.rz0.vyngtestapp.base.AppSchedulers;
import ru.app.rz0.vyngtestapp.di.ControllerScope;
import ru.app.rz0.vyngtestapp.features.player.PlayerInteractor;
import ru.app.rz0.vyngtestapp.features.player.PlayerPresenter;
import ru.app.rz0.vyngtestapp.features.player.PlayerRepository;
import ru.app.rz0.vyngtestapp.features.player.PlayerRepositoryImpl;
import ru.app.rz0.vyngtestapp.models.search.VideoItem;

@Module
public class PlayerModule {

  @Provides
  @ControllerScope
  Box<VideoItem> providesBoxVideoStore(BoxStore store) {
    return store.boxFor(VideoItem.class);
  }

  @Provides
  @ControllerScope
  PlayerRepository providesPlayerRepository(Box<VideoItem> store, AppSchedulers schedulers) {
    return new PlayerRepositoryImpl(store, schedulers);
  }

  @Provides
  @ControllerScope
  PlayerInteractor providesPlayerInteractor(PlayerRepository repository) {
    return new PlayerInteractor(repository);
  }

  @Provides
  @ControllerScope
  PlayerPresenter providesPlayerPresenter(PlayerInteractor interactor) {
    return new PlayerPresenter(interactor);
  }

}
