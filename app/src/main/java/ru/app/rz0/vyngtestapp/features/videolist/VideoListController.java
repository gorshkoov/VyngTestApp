package ru.app.rz0.vyngtestapp.features.videolist;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import ru.app.rz0.vyngtestapp.R;
import ru.app.rz0.vyngtestapp.VyngApp;
import ru.app.rz0.vyngtestapp.base.presentation.BaseController;
import ru.app.rz0.vyngtestapp.di.videolist.VideoListComponent;
import ru.app.rz0.vyngtestapp.di.videolist.VideoListModule;
import ru.app.rz0.vyngtestapp.features.player.PlayerController;
import ru.app.rz0.vyngtestapp.features.videolist.list.PaginationScrollListener;
import ru.app.rz0.vyngtestapp.features.videolist.list.VideoListAdapter;
import ru.app.rz0.vyngtestapp.models.search.VideoItem;
import ru.app.rz0.vyngtestapp.utils.GlideLoader;

public class VideoListController extends BaseController implements VideoListView {

  @BindView(R.id.recycler_view)
  RecyclerView recyclerView;

  @BindView(R.id.placeholder_view)
  TextView placeholderView;

  @BindView(R.id.progress_view)
  View progressView;

  @Inject
  VideoListPresenter presenter;

  private CompositeDisposable compositeDisposable;
  private String searchQuery;

  @Override
  protected void onViewBound(@NonNull View view) {
    LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.addOnScrollListener(new PaginationScrollListener(layoutManager) {
      @Override
      public void onLoadMore(int currentPage) {
        Log.d("LOAD_MORE ", " " + currentPage);
        search(currentPage);
      }
    });

    setHasOptionsMenu(true);
    provideComponent();
  }

  private void provideComponent() {
    VideoListComponent component = VyngApp.appComponent
        .plusVideoListComponent(new VideoListModule());

    component.inject(this);
  }

  @Override
  protected void onAttach(@NonNull View view) {
    super.onAttach(view);
    setArrowVisible(false);
    compositeDisposable = new CompositeDisposable();
    presenter.attach(this);
  }

  @Override
  protected void onDetach(@NonNull View view) {
    super.onDetach(view);
    compositeDisposable.dispose();
    presenter.detach();
  }

  @Override
  protected int layoutRes() {
    return R.layout.controller_video_list;
  }

  @Override
  protected int titleRes() {
    return R.string.search_title;
  }

  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.video_list_menu, menu);
  }

  @Override
  public void onPrepareOptionsMenu(@NonNull Menu menu) {
    MenuItem item = menu.findItem(R.id.search_menu_item);
    SearchView searchView = (SearchView) item.getActionView();

    compositeDisposable.add(
        RxSearchView.queryTextChanges(searchView)
            .debounce(500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .filter(query -> !TextUtils.isEmpty(query))
            .map(CharSequence::toString)
            .subscribe(query -> {
              searchQuery = query;
              search(0);
            }));

    super.onPrepareOptionsMenu(menu);
  }

  private void search(int page) {
    presenter.searchStarted(searchQuery, page);
  }

  @Override
  public void onItemsLoaded(List<VideoItem> items, int page) {
    if (items.size() == 0) {
      showPlaceholder(true);
      return;
    }
    showPlaceholder(false);
    if (recyclerView.getAdapter() == null) {
      recyclerView.setAdapter(new VideoListAdapter(getActivity(), items, new GlideLoader(),
          view -> openVideoPlayController((VideoItem) view.getTag(R.id.tag_object))));
    } else {
      VideoListAdapter videoListAdapter = (VideoListAdapter) recyclerView.getAdapter();
      if (page == 0) {
        videoListAdapter.updateList(items);
      } else {
        videoListAdapter.addToList(items);
      }
    }
  }

  @Override
  public void setLoading(boolean loading) {
    progressView.setVisibility(loading ? View.VISIBLE : View.GONE);
  }

  private void showPlaceholder(boolean show) {
    placeholderView.setVisibility(show ? View.VISIBLE : View.GONE);
    recyclerView.setVisibility(show ? View.GONE : View.VISIBLE);
    placeholderView.setText(R.string.search_empty);
  }

  private void openVideoPlayController(VideoItem videoItem) {
    setRetainViewMode(RetainViewMode.RETAIN_DETACH);

    PlayerController controller = new PlayerController(videoItem);

    getRouter().pushController(RouterTransaction.with(controller)
        .pushChangeHandler(new HorizontalChangeHandler())
        .popChangeHandler(new HorizontalChangeHandler()));
  }
}
