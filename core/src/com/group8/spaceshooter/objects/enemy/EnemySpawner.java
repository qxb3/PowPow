package com.group8.spaceshooter.objects.enemy;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.group8.spaceshooter.SpaceShooter;

import java.util.ArrayList;

public class EnemySpawner {
    private final SpaceShooter game;

    public ArrayList<Enemy> enemies; // Enemies array
    private long lastEnemy; // Keep track of the time of last spawned enemy

    public EnemySpawner(SpaceShooter game) {
        this.game = game;

        // Initialize enemy variables
        enemies = new ArrayList<>();
        lastEnemy = 0;
    }

    public void update(float delta) {
        // If its past 5000ms (5seconds) spawn another enemy
        if (TimeUtils.millis() - lastEnemy > 5000) spawnEnemies();

        // Update the enemy
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);

            enemy.update(delta);

            // Remove the enemy if it goes past the screen
            if (enemy.position.x < -32)
                enemies.remove(enemy);
        }
    }

    public void render() {
        // Render enemies
        for (Enemy enemy : enemies) {
            enemy.render();
        }
    }

    // A function for spawning the enemy
    private void spawnEnemies() {
        EnemyTypes type;

        // Choose a random enemy type (BACTERIA, SHELL, CANDY
        int random = MathUtils.random(0, 2);
        if (random == 0) type = EnemyTypes.BACTERIA;
        else if  (random == 1) type = EnemyTypes.SHELL;
        else type = EnemyTypes.CANDY;

        // Create enemy object
        Enemy enemy = new Enemy(type, this.game);

        enemies.add(enemy); // Add enemy
        lastEnemy = TimeUtils.millis(); // Set the time to the current
    }

    public void dispose() {
        for (Enemy enemy : enemies) {
            enemy.dispose();
        }
    }
}
