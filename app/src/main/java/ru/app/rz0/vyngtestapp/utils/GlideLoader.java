package ru.app.rz0.vyngtestapp.utils;

import static android.support.v4.content.ContextCompat.getColor;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;
import java.util.List;
import ru.app.rz0.vyngtestapp.R;

public class GlideLoader {

  public void loadUrl(ImageView imageView, String url, int position) {
    List<ColorDrawable> placeholders = getPlaceholderColors(imageView.getContext());
    GlideApp.with(imageView.getContext())
        .load(url)
        .apply(new RequestOptions()
            .placeholder(placeholders.get(position % placeholders.size()))
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .centerCrop())
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imageView);
    imageView.setHasTransientState(true);
    imageView.setBackgroundResource(R.color.transparent);
  }

  private List<ColorDrawable> getPlaceholderColors(Context context) {
    int[] colorsArray = new int[]{R.color.loading_placeholder_first,
        R.color.loading_placeholder_second, R.color.loading_placeholder_third};
    return new ArrayList<ColorDrawable>() {{
      add(new ColorDrawable(getColor(context, colorsArray[0])));
      add(new ColorDrawable(getColor(context, colorsArray[1])));
      add(new ColorDrawable(getColor(context, colorsArray[2])));
    }};
  }
}
