package ru.app.rz0.vyngtestapp.features.videolist.list;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;

public abstract class PaginationScrollListener extends RecyclerView.OnScrollListener {

  private int previousTotal = 0; // The total number of items in the dataset after the last load
  private boolean loading = true; // True if we are still waiting for the last set of data to load.
  private final int visibleThreshold = 5; // The minimum amount of items to have below your current scroll position before loading more.
  private int firstVisibleItem, visibleItemCount, totalItemCount;

  private int currentPage = 0;

  private LayoutManager layoutManager;

  public PaginationScrollListener(LayoutManager layoutManager) {
    this.layoutManager = layoutManager;
  }

  @Override
  public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
    super.onScrollStateChanged(recyclerView, newState);
  }

  @Override
  public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
    super.onScrolled(recyclerView, dx, dy);

    if (totalItemCount > layoutManager.getItemCount()) {
      currentPage = 0;
    }

    visibleItemCount = recyclerView.getChildCount();
    totalItemCount = layoutManager.getItemCount();

    if (layoutManager instanceof LinearLayoutManager) {
      firstVisibleItem = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
    }

    if (layoutManager instanceof GridLayoutManager) {
      firstVisibleItem = ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();
    }

    if (loading) {
      if (totalItemCount > previousTotal) {
        loading = false;
        previousTotal = totalItemCount;
      }
    }
    if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
      // Do something
      currentPage++;

      onLoadMore(currentPage);

      loading = true;
    }
  }

  public abstract void onLoadMore(int currentPage);
}