package ru.app.rz0.vyngtestapp.di.videolist;

import dagger.Subcomponent;
import ru.app.rz0.vyngtestapp.di.ControllerScope;
import ru.app.rz0.vyngtestapp.features.videolist.VideoListController;

@Subcomponent(modules = {VideoListModule.class})
@ControllerScope
public interface VideoListComponent {

  void inject(VideoListController controller);
}

