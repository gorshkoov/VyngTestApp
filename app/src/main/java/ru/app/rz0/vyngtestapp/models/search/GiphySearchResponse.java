package ru.app.rz0.vyngtestapp.models.search;

import java.util.ArrayList;
import java.util.List;

public class GiphySearchResponse {

  public List<VideoItem> items = new ArrayList<>();

  public void add(VideoItem item) {
    items.add(item);
  }
}
