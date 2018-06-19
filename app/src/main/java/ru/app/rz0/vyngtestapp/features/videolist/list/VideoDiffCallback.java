package ru.app.rz0.vyngtestapp.features.videolist.list;

import android.support.v7.util.DiffUtil;
import android.text.TextUtils;
import java.util.List;
import ru.app.rz0.vyngtestapp.models.search.VideoItem;

public class VideoDiffCallback extends DiffUtil.Callback {

  private List<VideoItem> oldItems;
  private List<VideoItem> newItems;

  public VideoDiffCallback(List<VideoItem> oldItems, List<VideoItem> newItems) {
    this.oldItems = oldItems;
    this.newItems = newItems;
  }

  @Override
  public int getOldListSize() {
    return oldItems.size();
  }

  @Override
  public int getNewListSize() {
    return newItems.size();
  }

  @Override
  public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
    VideoItem oldItem = oldItems.get(oldItemPosition);
    VideoItem newItem = newItems.get(newItemPosition);
    return TextUtils.equals(oldItem.key, newItem.key);
  }

  @Override
  public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
    VideoItem oldItem = oldItems.get(oldItemPosition);
    VideoItem newItem = newItems.get(newItemPosition);
    return TextUtils.equals(oldItem.imageUrl, newItem.imageUrl);
  }
}
