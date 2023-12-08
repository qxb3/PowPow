package com.group8.spaceshooter.screens;

import com.group8.spaceshooter.SpaceShooter;
import com.group8.spaceshooter.lib.SpaceShooterScreen;

public class GameScreen implements SpaceShooterScreen {
    private final SpaceShooter game;

    public GameScreen(SpaceShooter game) {
        this.game = game;
    }

    @Override
    public void update() {}

    @Override
    public void render(float delta) {}

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
