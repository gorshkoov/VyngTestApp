package ru.app.rz0.vyngtestapp.features.videolist;

import io.reactivex.Single;
import java.util.List;
import ru.app.rz0.vyngtestapp.models.search.VideoItem;

public interface VideoListRepository {

  Single<List<VideoItem>> search(String query);

}
