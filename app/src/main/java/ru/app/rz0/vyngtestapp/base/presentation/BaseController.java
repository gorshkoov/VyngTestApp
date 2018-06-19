package ru.app.rz0.vyngtestapp.base.presentation;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.ControllerChangeType;

public abstract class BaseController extends Controller {

  private final int TITLE_NOT_SET = -1;

  private Unbinder unbinder;

  protected BaseController() {
  }

  protected BaseController(Bundle args) {
    super(args);
  }

  @NonNull
  @Override
  protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
    View root = inflater.inflate(layoutRes(), container, false);
    unbinder = ButterKnife.bind(this, root);
    onViewBound(root);
    return root;
  }

  protected abstract @LayoutRes
  int layoutRes();

  protected @StringRes
  int titleRes() {
    return TITLE_NOT_SET;
  }

  protected void onViewBound(@NonNull View view) {
  }

  @Override
  protected void onDestroyView(@NonNull View view) {
    super.onDestroyView(view);
    if (unbinder != null) {
      unbinder.unbind();
      unbinder = null;
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        // Set the default behavior to be back navigation.
        getRouter().handleBack();
        return true;
    }
    return false;
  }

  @Override
  protected void onChangeStarted(@NonNull ControllerChangeHandler changeHandler,
      @NonNull ControllerChangeType changeType) {
    if (changeType.isEnter && titleRes() != TITLE_NOT_SET) {
      setTitle(titleRes());
    }
  }

  @Nullable
  private ActionBar getActionBar() {
    if (getActivity() == null) {
      return null;
    }
    return ((AppCompatActivity) getActivity()).getSupportActionBar();
  }

  protected void setTitle(@StringRes int titleRes) {
    ActionBar actionBar = getActionBar();
    if (actionBar == null) {
      return;
    }

    actionBar.setTitle(titleRes);
  }

  protected void setArrowVisible(boolean arrowVisible) {
    ActionBar actionBar = getActionBar();
    if (actionBar == null) {
      return;
    }

    actionBar.setDisplayHomeAsUpEnabled(arrowVisible);
  }
}
