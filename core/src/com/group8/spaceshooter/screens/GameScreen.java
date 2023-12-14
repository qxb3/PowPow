package com.group8.spaceshooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.group8.spaceshooter.SpaceShooter;
import com.group8.spaceshooter.objects.enemy.Enemy;
import com.group8.spaceshooter.objects.player.Player;
import com.group8.spaceshooter.objects.enemy.EnemySpawner;
import com.group8.spaceshooter.objects.player.PlayerBullet;

public class GameScreen implements Screen {
    private final SpaceShooter game;

    private Player player;
    private EnemySpawner enemySpawner;

    private Sound enemyHitSfx;
    private Sprite playerHeart;

    public GameScreen(SpaceShooter game) {
        this.game = game;
    }

    @Override
    public void show() {
        player = new Player(this.game);
        enemySpawner = new EnemySpawner(this.game);

        // Create enemy hit sfx
        enemyHitSfx = Gdx.audio.newSound(Gdx.files.internal("sfx/enemy-hit.wav"));

        // Initialize heart sprite
        playerHeart = new Sprite(new Texture("player/heart.png"));
        playerHeart.setSize(32, 32);
    }

    private void update(float delta) {
        player.update(delta); // Update player
        enemySpawner.update(delta); // Update enemy spawner

        // Handle collision between the player bullets and the enemies
        for (int i = 0; i < player.bullets.size(); i++) { // Loop the player bullets
            PlayerBullet bullet = player.bullets.get(i); // Get the current player bullet

            for (int j = 0; j < enemySpawner.enemies.size(); j++) { // Loop the enemies
                Enemy enemy = enemySpawner.enemies.get(j); // Get the current enemy

                if (bullet.rectangle.overlaps(enemy.rectangle)) { // Check if the current bullet and the enemy overlaps
                    player.bullets.remove(bullet); // Remove the player bullet

                    // Play the enemy hit sfx
                    long id = enemyHitSfx.play();
                    enemyHitSfx.setVolume(id, 0.2f); // Set this sfx volume to 20%

                    // Handle enemy hit, remove the enemy if the hp is 0
                    boolean isEnemyDead = enemy.hit();
                    if (isEnemyDead)
                        enemySpawner.enemies.remove(enemy);
                }
            }
        }

        // Handle collision between the player and the enemies
        for (int i = 0; i < enemySpawner.enemies.size(); i++) {
            Enemy enemy = enemySpawner.enemies.get(i); // Get the enemy

            // Check if the enemy & player rectangles overlaps / collide
            if (player.rectangle.overlaps(enemy.rectangle)) {
                enemySpawner.enemies.remove(enemy); // Remove the enemy that collided with the player

                // Call the hit function on the player that will return a boolean if the player is dead
                boolean isPlayerDead = player.hit();
                if (isPlayerDead)
                    this.game.setScreen(new GameOverScreen(this.game)); // Set the screen to game over screen
            }
        }
    }

    @Override
    public void render(float delta) {
        this.update(delta); // Call the update function

        this.game.batch.begin(); // Begin the sprite renderer

        for (int i = 0; i < player.health; i++) {
            this.game.batch.draw(playerHeart, (8 + playerHeart.getWidth()) * i, SpaceShooter.GAME_HEIGHT - playerHeart.getHeight() - 8, playerHeart.getWidth(), playerHeart.getHeight());
        }

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
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

}
