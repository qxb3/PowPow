package com.group8.spaceshooter.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.group8.spaceshooter.SpaceShooter;

public class MenuScreen implements Screen {
    private final SpaceShooter game;

    public MenuScreen(SpaceShooter game) {
        this.game = game;
    }

    private void update(float delta) {}

    public void render(float delta) {
        this.update(delta);

        this.game.batch.begin();
        this.game.batch.end();
    }

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