package ru.app.rz0.vyngtestapp.utils;

import android.content.Context;
import android.net.Uri;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.LoopingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


public class VideoExoPlayer {

  private SimpleExoPlayer exoPlayer;
  private Context context;

  public VideoExoPlayer(Context context) {
    this.context = context;
    BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
    TrackSelection.Factory factory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
    TrackSelector trackSelector = new DefaultTrackSelector(factory);

    exoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector);
  }

  public SimpleExoPlayer getPlayer() {
    return exoPlayer;
  }

  public void play(String url) {
    exoPlayer.setPlayWhenReady(true);
    DefaultBandwidthMeter meter = new DefaultBandwidthMeter();
    DefaultDataSourceFactory factory = new DefaultDataSourceFactory(context, Util
        .getUserAgent(context, "VyngAppSample"), meter);

    MediaSource mediaSource = new ExtractorMediaSource.Factory(factory)
        .createMediaSource(Uri.parse(url), null, null);

    LoopingMediaSource loopingMediaSource = new LoopingMediaSource(mediaSource);

    exoPlayer.prepare(loopingMediaSource);
  }

  public void detach() {
    exoPlayer.stop();
  }
}
