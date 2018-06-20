package ru.app.rz0.vyngtestapp.features.videolist;

import java.util.List;
import ru.app.rz0.vyngtestapp.base.presentation.BaseView;
import ru.app.rz0.vyngtestapp.models.search.VideoItem;

public interface VideoListView extends BaseView {

  void onItemsLoaded(List<VideoItem> items, int page);

  void setLoading(boolean loading);
}
