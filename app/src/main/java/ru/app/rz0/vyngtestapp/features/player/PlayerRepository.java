package ru.app.rz0.vyngtestapp.features.player;

import io.reactivex.Single;
import ru.app.rz0.vyngtestapp.models.search.VideoItem;

public interface PlayerRepository {

  Single<VideoItem> checkVideo(String key);

  Single<VideoItem> thumbChanges(VideoItem item);
}
