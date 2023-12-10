package com.group8.spaceshooter.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.group8.spaceshooter.SpaceShooter;
import com.group8.spaceshooter.objects.Player;
import com.group8.spaceshooter.objects.enemy.Enemy;
import com.group8.spaceshooter.objects.enemy.EnemyTypes;

import java.util.ArrayList;

public class GameScreen implements Screen {
    private final SpaceShooter game;

    private static Player player;

    private static ArrayList<Enemy> enemies;
    private static long lastEnemy;

    public GameScreen(SpaceShooter game) {
        this.game = game;

        player = new Player(this.game);

        enemies = new ArrayList<>();
        lastEnemy = 0;
    }

    private void update(float delta) {
        player.update(delta);

        if (TimeUtils.millis() - lastEnemy > 5000) spawnEnemies();
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);

            enemy.update(delta);
            if (enemy.getPosition().x < -32)
                enemies.remove(enemy);
        }
    }

    @Override
    public void render(float delta) {
        this.update(delta);

        this.game.batch.begin();

        player.render(delta);

        for (Enemy enemy : enemies) {
            enemy.render(delta);
        }

        this.game.batch.end();
    }

    private void spawnEnemies() {
        EnemyTypes type;

        int random = MathUtils.random(0, 1);
        if (random == 1) type = EnemyTypes.BACTERIA;
        else if  (random == 2) type = EnemyTypes.SHELL;
        else type = EnemyTypes.CANDY;

        Enemy enemy = new Enemy(type, this.game);

        enemies.add(enemy);
        lastEnemy = TimeUtils.millis();
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
