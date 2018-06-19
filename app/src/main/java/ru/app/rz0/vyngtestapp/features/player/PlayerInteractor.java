package ru.app.rz0.vyngtestapp.features.player;

import io.reactivex.Single;
import javax.inject.Inject;
import ru.app.rz0.vyngtestapp.models.search.VideoItem;

public class PlayerInteractor {

  private PlayerRepository repository;

  @Inject
  public PlayerInteractor(PlayerRepository repository) {
    this.repository = repository;
  }

  public Single<VideoItem> checkVideo(String key) {
    return repository.checkVideo(key);
  }

  public Single<VideoItem> thumbUp(VideoItem videoItem) {
    videoItem.thumbUpCount++;
    return repository.thumbChanges(videoItem);
  }

  public Single<VideoItem> thumbDown(VideoItem videoItem) {
    videoItem.thumbDownCount++;
    return repository.thumbChanges(videoItem);
  }
}
