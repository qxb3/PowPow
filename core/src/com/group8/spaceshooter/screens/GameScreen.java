package com.group8.spaceshooter.screens;

import com.group8.spaceshooter.SpaceShooter;
import com.group8.spaceshooter.lib.SpaceShooterScreen;
import com.group8.spaceshooter.objects.Enemy;
import com.group8.spaceshooter.objects.Player;

import java.util.ArrayList;
import java.util.Iterator;

public class GameScreen implements SpaceShooterScreen {
    private final SpaceShooter game;

    private static Player player;
    private static ArrayList<Enemy> enemies;

    public GameScreen(SpaceShooter game) {
        this.game = game;

        player = new Player(this.game);

        enemies = new ArrayList<>();
    }

    @Override
    public void update(float delta) {
        player.update(delta);

        for (Iterator<Enemy> iterator = enemies.iterator(); iterator.hasNext(); ) {
            Enemy enemy = iterator.next();
            enemy.update(delta);
        }
    }

    @Override
    public void render(float delta) {
        player.render(delta);

        for (Enemy enemy : enemies) {
            enemy.render(delta);
        }
    }

    @Override
    public void renderShape(float delta) {
        for (Enemy enemy : enemies) {
            enemy.renderShape(delta);
        }
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
