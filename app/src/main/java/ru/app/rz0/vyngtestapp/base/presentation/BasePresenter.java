package ru.app.rz0.vyngtestapp.base.presentation;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<T extends BaseView> {

  private T view;
  private CompositeDisposable compositeDisposable;

  public void detach() {
    if (compositeDisposable != null) {
      compositeDisposable.clear();
    }
    view = null;
  }

  public void attach(T view) {
    this.view = view;
  }

  public T getView() {
    return view;
  }

  protected void addSubscription(Disposable subscription) {
    if (subscription == null) {
      return;
    }
    if (compositeDisposable == null || compositeDisposable.isDisposed()) {
      compositeDisposable = new CompositeDisposable();
    }

    compositeDisposable.add(subscription);
  }

}
