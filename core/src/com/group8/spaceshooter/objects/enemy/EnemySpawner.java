package com.group8.spaceshooter.objects.enemy;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.group8.spaceshooter.SpaceShooter;

import java.util.ArrayList;

public class EnemySpawner {
    private final SpaceShooter game;

    private static ArrayList<Enemy> enemies;
    private static long lastEnemy;

    public EnemySpawner(SpaceShooter game) {
        this.game = game;

        enemies = new ArrayList<>();
        lastEnemy = 0;
    }

    public void update(float delta) {
        if (TimeUtils.millis() - lastEnemy > 5000) spawnEnemies();

        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);

            enemy.update(delta);
            if (enemy.getPosition().x < -32)
                enemies.remove(enemy);
        }
    }

    public void render() {
        for (Enemy enemy : enemies) {
            enemy.render();
        }
    }

    private void spawnEnemies() {
        EnemyTypes type;

        int random = MathUtils.random(0, 2);
        if (random == 0) type = EnemyTypes.BACTERIA;
        else if  (random == 1) type = EnemyTypes.SHELL;
        else type = EnemyTypes.CANDY;

        Enemy enemy = new Enemy(type, this.game);

        enemies.add(enemy);
        lastEnemy = TimeUtils.millis();
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void dispose() {
        for (Enemy enemy : enemies) {
            enemy.dispose();
        }
    }
}
