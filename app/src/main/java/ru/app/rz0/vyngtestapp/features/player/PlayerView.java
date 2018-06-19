package ru.app.rz0.vyngtestapp.features.player;

import ru.app.rz0.vyngtestapp.base.presentation.BaseView;
import ru.app.rz0.vyngtestapp.models.search.VideoItem;

public interface PlayerView extends BaseView {

  void updateCounters(VideoItem videoItem);
}
