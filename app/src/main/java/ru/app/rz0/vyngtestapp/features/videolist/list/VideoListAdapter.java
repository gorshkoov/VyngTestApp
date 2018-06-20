package ru.app.rz0.vyngtestapp.features.videolist.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import ru.app.rz0.vyngtestapp.R;
import ru.app.rz0.vyngtestapp.models.search.VideoItem;
import ru.app.rz0.vyngtestapp.utils.GlideLoader;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListHolder> {

  private List<VideoItem> items;
  private LayoutInflater inflater;
  private View.OnClickListener listener;
  private GlideLoader loader;

  public VideoListAdapter(Context context, List<VideoItem> items, GlideLoader loader,
      View.OnClickListener listener) {
    this.items = items;
    this.listener = listener;
    this.loader = loader;
    inflater = LayoutInflater.from(context);
  }

  @NonNull
  @Override
  public VideoListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = inflater.inflate(R.layout.video_list_item, parent, false);

    VideoListHolder holder = new VideoListHolder(view, loader);
    holder.itemView.setOnClickListener(listener);

    return holder;
  }

  @Override
  public void onBindViewHolder(@NonNull VideoListHolder holder, int position) {
    holder.bind(items.get(position), position);
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  public void updateList(List<VideoItem> items) {
    DiffUtil.DiffResult result = DiffUtil.calculateDiff(new VideoDiffCallback(this.items, items));

    this.items.clear();
    this.items.addAll(items);
    result.dispatchUpdatesTo(this);
  }

  public void addToList(List<VideoItem> items) {
    List<VideoItem> newItems = new ArrayList<>(this.items);
    newItems.addAll(items);
    DiffUtil.DiffResult result = DiffUtil.calculateDiff(new VideoDiffCallback(this.items, newItems));

    this.items.clear();
    this.items.addAll(newItems);
    result.dispatchUpdatesTo(this);
  }
}
