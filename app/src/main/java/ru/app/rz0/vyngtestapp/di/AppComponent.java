package ru.app.rz0.vyngtestapp.di;

import dagger.Component;
import javax.inject.Singleton;
import ru.app.rz0.vyngtestapp.di.player.PlayerComponent;
import ru.app.rz0.vyngtestapp.di.player.PlayerModule;
import ru.app.rz0.vyngtestapp.di.videolist.VideoListComponent;
import ru.app.rz0.vyngtestapp.di.videolist.VideoListModule;

@Component(modules = {AppModule.class, RetrofitModule.class, ObjectBoxModule.class})
@Singleton
public interface AppComponent {

  VideoListComponent plusVideoListComponent(VideoListModule module);

  PlayerComponent plusPlayerComponent(PlayerModule module);
}
