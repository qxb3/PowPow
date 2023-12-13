package com.group8.spaceshooter.screens;

import com.badlogic.gdx.Screen;
import com.group8.spaceshooter.SpaceShooter;
import com.group8.spaceshooter.objects.enemy.Enemy;
import com.group8.spaceshooter.objects.player.Player;
import com.group8.spaceshooter.objects.enemy.EnemySpawner;
import com.group8.spaceshooter.objects.player.PlayerBullet;

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

        // Handle collision between the player bullets and the enemies
        for (int i = 0; i < player.getBullets().size(); i++) { // Loop the player bullets
            PlayerBullet bullet = player.getBullets().get(i); // Get the current player bullet

            for (int j = 0; j < enemySpawner.getEnemies().size(); j++) { // Loop the enemies
                Enemy enemy = enemySpawner.getEnemies().get(j); // Get the current enemy

                if (bullet.getRectangle().overlaps(enemy.getRectangle())) { // Check if the current bullet and the enemy overlaps
                    player.getBullets().remove(bullet); // Remove the player bullet

                    // Handle enemy hit, remove the enemy if the hp is 0
                    boolean isEnemyDead = enemy.hit();
                    if (isEnemyDead)
                        enemySpawner.getEnemies().remove(enemy);
                }
            }
        }
    }

    @Override
    public void render(float delta) {
        this.update(delta); // Call the update function

        this.game.batch.begin(); // Begin the sprite renderer

        player.render(); // Render player
        enemySpawner.render(); // Render enemies from the spawner

        this.game.batch.end(); // End the sprite renderer
    }

    @Override
    public void dispose() {
        player.dispose();
        enemySpawner.dispose();
    }

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
