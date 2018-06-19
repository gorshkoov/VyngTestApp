package ru.app.rz0.vyngtestapp.features.videolist;

import io.reactivex.Single;
import java.util.List;
import javax.inject.Inject;
import ru.app.rz0.vyngtestapp.models.search.VideoItem;

public class VideoListInteractor {

  private VideoListRepository repository;

  @Inject
  public VideoListInteractor(VideoListRepository repository) {
    this.repository = repository;
  }

  public Single<List<VideoItem>> search(String query) {
    return repository.search(query);
  }

}
