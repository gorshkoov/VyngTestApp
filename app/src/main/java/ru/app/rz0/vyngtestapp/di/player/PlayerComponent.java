package ru.app.rz0.vyngtestapp.di.player;

import dagger.Subcomponent;
import ru.app.rz0.vyngtestapp.di.ControllerScope;
import ru.app.rz0.vyngtestapp.features.player.PlayerController;

@Subcomponent(modules = {PlayerModule.class})
@ControllerScope
public interface PlayerComponent {

  void inject(PlayerController controller);
}
