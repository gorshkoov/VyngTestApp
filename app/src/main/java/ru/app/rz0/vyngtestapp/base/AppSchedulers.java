package ru.app.rz0.vyngtestapp.base;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AppSchedulers {

  public Scheduler io() {
    return Schedulers.io();
  }

  public Scheduler ui() {
    return AndroidSchedulers.mainThread();
  }
}
