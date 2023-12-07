package com.group8.spaceshooter.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.group8.spaceshooter.SpaceShooter;
import com.group8.spaceshooter.objects.Player;

public class GameScreen implements Screen {
    private final SpaceShooter game;

    // GameScreen camera
    private static OrthographicCamera camera;

    public GameScreen(SpaceShooter game) {
        this.game = game;

        // Initialize camera & Set the width & height to the game dimensions
        camera = new OrthographicCamera();
        camera.setToOrtho(false, (int) SpaceShooter.GAME_WIDTH, (int) SpaceShooter.GAME_HEIGHT);
    }

    @Override
    public void render(float delta) {
        // Update the camera
        camera.update();

        // Render stuff on GameScreen
        this.game.batch.setProjectionMatrix(camera.combined);
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
