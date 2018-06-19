package ru.app.rz0.vyngtestapp.features.player;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import javax.inject.Inject;
import ru.app.rz0.vyngtestapp.R;
import ru.app.rz0.vyngtestapp.VyngApp;
import ru.app.rz0.vyngtestapp.base.presentation.BaseController;
import ru.app.rz0.vyngtestapp.di.player.PlayerComponent;
import ru.app.rz0.vyngtestapp.di.player.PlayerModule;
import ru.app.rz0.vyngtestapp.models.search.VideoItem;
import ru.app.rz0.vyngtestapp.utils.BundleBuilder;
import ru.app.rz0.vyngtestapp.utils.VideoExoPlayer;

public class PlayerController extends BaseController implements PlayerView {

  private static final String KEY_ITEM = "PlayerController.item";

  @BindView(R.id.exoplayer_view)
  com.google.android.exoplayer2.ui.PlayerView exoPlayerView;
  @BindView(R.id.thumb_up_counter_view)
  TextView thumbUpCounterView;
  @BindView(R.id.thumb_down_counter_view)
  TextView thumbDownCounterView;
  @BindView(R.id.thumb_up_view)
  View thumbUpView;
  @BindView(R.id.thumb_down_view)
  View thumbDownView;

  @Inject
  PlayerPresenter presenter;

  private VideoItem videoItem;
  private VideoExoPlayer videoExoPlayer;

  public PlayerController(VideoItem videoItem) {
    this(new BundleBuilder(new Bundle())
        .putParcelable(KEY_ITEM, videoItem)
        .build());
  }

  public PlayerController(Bundle args) {
    super(args);
  }

  @Override
  protected void onViewBound(@NonNull View view) {
    updateCounters(getArgs().getParcelable(KEY_ITEM));

    provideComponent();
    startPlay();
    presenter.checkVideo(videoItem.key);

    thumbUpView.setOnClickListener(v -> presenter.thumbUpClicked(videoItem));
    thumbDownView.setOnClickListener(v -> presenter.thumbDownClicked(videoItem));
  }

  private void provideComponent() {
    PlayerComponent component = VyngApp.appComponent
        .plusPlayerComponent(new PlayerModule());

    component.inject(this);
  }

  @Override
  protected void onAttach(@NonNull View view) {
    super.onAttach(view);
    setArrowVisible(true);

    presenter.attach(this);
  }

  @Override
  protected void onDetach(@NonNull View view) {
    super.onDetach(view);
    videoExoPlayer.detach();
    presenter.detach();
  }

  private void startPlay() {
    videoExoPlayer = new VideoExoPlayer(getActivity());
    exoPlayerView.setPlayer(videoExoPlayer.getPlayer());
    videoExoPlayer.play(videoItem.videoUrl);
  }

  @Override
  protected int layoutRes() {
    return R.layout.controller_player;
  }

  @Override
  protected int titleRes() {
    return R.string.player_title;
  }

  @Override
  public void updateCounters(VideoItem item) {
    this.videoItem = item;
    thumbUpCounterView.setText(String.valueOf(item.thumbUpCount));
    thumbDownCounterView.setText(String.valueOf(item.thumbDownCount));
  }
}
