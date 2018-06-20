package ru.app.rz0.vyngtestapp.features.videolist;

import io.reactivex.Single;
import java.util.List;
import javax.inject.Inject;
import ru.app.rz0.vyngtestapp.base.AppSchedulers;
import ru.app.rz0.vyngtestapp.data.network.GiphyApiService;
import ru.app.rz0.vyngtestapp.models.search.VideoItem;
import ru.app.rz0.vyngtestapp.utils.Const;

public class VideoListRepositoryImpl implements VideoListRepository {

  private GiphyApiService service;
  private AppSchedulers schedulers;

  @Inject
  public VideoListRepositoryImpl(GiphyApiService service,
      AppSchedulers schedulers) {
    this.service = service;
    this.schedulers = schedulers;
  }

  @Override
  public Single<List<VideoItem>> search(String query, int page) {
    return service.search(Const.GIPHY_KEY, query, Const.GIPHY_LIMIT, page * Const.GIPHY_LIMIT)
        .observeOn(schedulers.ui())
        .subscribeOn(schedulers.io())
        .map(response -> response.items);
  }
}
