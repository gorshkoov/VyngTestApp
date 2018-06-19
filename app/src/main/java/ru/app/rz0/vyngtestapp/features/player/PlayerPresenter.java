package ru.app.rz0.vyngtestapp.features.player;

import javax.inject.Inject;
import ru.app.rz0.vyngtestapp.base.presentation.BasePresenter;
import ru.app.rz0.vyngtestapp.models.search.VideoItem;

public class PlayerPresenter extends BasePresenter<PlayerView> {

  private PlayerInteractor interactor;

  @Inject
  public PlayerPresenter(PlayerInteractor interactor) {
    this.interactor = interactor;
  }

  public void checkVideo(String key) {
    addSubscription(interactor.checkVideo(key)
        .subscribe(item -> getView().updateCounters(item), Throwable::printStackTrace));
  }

  public void thumbUpClicked(VideoItem videoItem) {
    addSubscription(interactor.thumbUp(videoItem)
        .subscribe(item -> getView().updateCounters(item)));
  }

  public void thumbDownClicked(VideoItem videoItem) {
    addSubscription(interactor.thumbDown(videoItem)
        .subscribe(item -> getView().updateCounters(item)));
  }
}
