package ru.app.rz0.vyngtestapp.features.videolist.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.app.rz0.vyngtestapp.R;
import ru.app.rz0.vyngtestapp.models.search.VideoItem;
import ru.app.rz0.vyngtestapp.utils.GlideLoader;

public class VideoListHolder extends RecyclerView.ViewHolder {

  @BindView(R.id.video_holder_imageview)
  ImageView videoImageView;

  private GlideLoader loader;

  public VideoListHolder(View itemView, GlideLoader loader) {
    super(itemView);
    this.loader = loader;
    ButterKnife.bind(this, itemView);
  }

  public void bind(VideoItem item, int position) {
    loader.loadUrl(videoImageView, item.imageUrl, position);
    itemView.setTag(R.id.tag_object, item);
  }
}
