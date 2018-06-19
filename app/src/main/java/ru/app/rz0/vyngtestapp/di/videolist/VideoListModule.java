package ru.app.rz0.vyngtestapp.di.videolist;

import dagger.Module;
import dagger.Provides;
import ru.app.rz0.vyngtestapp.base.AppSchedulers;
import ru.app.rz0.vyngtestapp.data.network.GiphyApiService;
import ru.app.rz0.vyngtestapp.di.ControllerScope;
import ru.app.rz0.vyngtestapp.features.videolist.VideoListInteractor;
import ru.app.rz0.vyngtestapp.features.videolist.VideoListPresenter;
import ru.app.rz0.vyngtestapp.features.videolist.VideoListRepository;
import ru.app.rz0.vyngtestapp.features.videolist.VideoListRepositoryImpl;

@Module
public class VideoListModule {

  @Provides
  @ControllerScope
  VideoListRepository providesVideoListRepository(GiphyApiService service,
      AppSchedulers schedulers) {
    return new VideoListRepositoryImpl(service, schedulers);
  }

  @Provides
  @ControllerScope
  VideoListInteractor providesVideoListInteractor(VideoListRepository repository) {
    return new VideoListInteractor(repository);
  }

  @Provides
  @ControllerScope
  VideoListPresenter providesVideoListPresenter(VideoListInteractor interactor) {
    return new VideoListPresenter(interactor);
  }
}
