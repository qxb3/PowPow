package com.group8.spaceshooter.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.group8.spaceshooter.SpaceShooter;
import com.group8.spaceshooter.objects.Player;
import com.group8.spaceshooter.objects.enemy.Enemy;
import com.group8.spaceshooter.objects.enemy.EnemySpawner;
import com.group8.spaceshooter.objects.enemy.EnemyTypes;

public class GameScreen implements Screen {
    private final SpaceShooter game;

    private static Player player;
    private static EnemySpawner enemySpawner;

    public GameScreen(SpaceShooter game) {
        this.game = game;

        player = new Player(this.game);
        enemySpawner = new EnemySpawner(this.game);
    }

    private void update(float delta) {
        player.update(delta);
        enemySpawner.update(delta);
    }

    @Override
    public void render(float delta) {
        this.update(delta);

        this.game.batch.begin();

        player.render();
        enemySpawner.render();

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
