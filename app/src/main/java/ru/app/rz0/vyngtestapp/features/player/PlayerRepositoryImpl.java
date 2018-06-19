package ru.app.rz0.vyngtestapp.features.player;

import io.objectbox.Box;
import io.reactivex.Single;
import javax.inject.Inject;
import ru.app.rz0.vyngtestapp.base.AppSchedulers;
import ru.app.rz0.vyngtestapp.models.search.VideoItem;
import ru.app.rz0.vyngtestapp.models.search.VideoItem_;

public class PlayerRepositoryImpl implements PlayerRepository {

  private Box<VideoItem> box;
  private AppSchedulers schedulers;

  @Inject
  public PlayerRepositoryImpl(Box<VideoItem> box, AppSchedulers schedulers) {
    this.box = box;
    this.schedulers = schedulers;
  }

  @Override
  public Single<VideoItem> checkVideo(String key) {
    return Single.fromCallable(() -> box.query().equal(VideoItem_.key, key).build().find())
        .observeOn(schedulers.ui())
        .subscribeOn(schedulers.io())
        .flatMap(videoItems ->
            videoItems.size() > 0 ? Single.just(videoItems.get(0))
                : Single.error(new Throwable("Not found")));
  }

  @Override
  public Single<VideoItem> thumbChanges(VideoItem item) {
    return Single.fromCallable(() -> box.put(item))
        .observeOn(schedulers.ui())
        .subscribeOn(schedulers.io())
        .map(value -> item);
  }
}
