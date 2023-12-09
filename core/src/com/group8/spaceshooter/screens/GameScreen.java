package com.group8.spaceshooter.screens;

import com.group8.spaceshooter.SpaceShooter;
import com.group8.spaceshooter.lib.SpaceShooterScreen;
import com.group8.spaceshooter.objects.Player;

public class GameScreen implements SpaceShooterScreen {
    private final SpaceShooter game;

    private static Player player;

    public GameScreen(SpaceShooter game) {
        this.game = game;

        player = new Player(this.game);
    }

    @Override
    public void update(float delta) {
        player.update(delta);
    }

    @Override
    public void render(float delta) {
        player.render(delta);
    }

    @Override
    public void renderShape(float delta) {}

    @Override
    public void dispose() {}

    @Override
    public void show() {}

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

}
