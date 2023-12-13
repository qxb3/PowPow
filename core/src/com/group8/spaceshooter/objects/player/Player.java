package com.group8.spaceshooter.objects.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.group8.spaceshooter.SpaceShooter;
import com.group8.spaceshooter.Utils;

import java.nio.Buffer;
import java.util.ArrayList;

public class Player {
    private final SpaceShooter game;

    private final TextureRegion[] playerRegions; // For specific region in the player sheet
    private final Sprite sprite; // The player sprite

    private final Animation<TextureRegion> boosterAnimation; // The booster animation below the player
    private float boosterTime; // For tracking the animation time

    private final Vector2 position; // Player's position

    private ArrayList<PlayerBullet> bullets; // Player bullets array
    private long lastBullet; // For tracking the bullets time
    private boolean canShoot;

    public Player(SpaceShooter game) {
        this.game = game;

        // Initialized player regions
        this.playerRegions = Utils.getRegions(new Texture("player/player.png"), 3, 1);

        // Set the first player sprite will be the middle of the player sheet
        this.sprite = new Sprite(playerRegions[1]);
        this.sprite.setSize(32, 32); // Set size to 32x32 pixels

        // Create the booster animation and set the speed to 0.2f
        this.boosterAnimation = Utils.createAnimation(new Texture("player/booster.png"), 2, 1, 0.2f);
        this.boosterTime = 0f;

        // Set the x position to the middle of the screen and y to player height * 2
        this.position = new Vector2((SpaceShooter.GAME_WIDTH / 2) - (sprite.getWidth() / 2), sprite.getHeight() * 2);

        // Initialize bullets variables
        this.bullets = new ArrayList<>();
        this.lastBullet = 0;
        this.canShoot = true;
    }

    public void update(float delta) {
        this.boosterTime += delta; // Increase the booster time as the game goes on

        // Makes sure the player is in the world boundaries
        if (this.position.x < 0) this.position.x = 0;
        if (this.position.x > SpaceShooter.GAME_WIDTH - this.sprite.getWidth())
            this.position.x = SpaceShooter.GAME_WIDTH - this.sprite.getWidth();

        // If its passed 200ms then the player can shoot
        this.canShoot = TimeUtils.millis() - lastBullet > 200;

        // Update each bullets
        for (int i = 0; i < bullets.size(); i++) {
            PlayerBullet bullet = bullets.get(i); // Get the bullet in this index

            bullet.update(delta); // Update the current bullet

            // Remove the bullet if it goes off the screen to save memory
            if (bullet.getPosition().y > SpaceShooter.GAME_HEIGHT + 16)
                bullets.remove(bullet);
        }

        // Call playerMovement() function
        this.playerMovement(delta);
    }

    public void render() {
        // Render the player
        this.game.batch.draw(this.sprite, this.position.x, this.position.y, this.sprite.getWidth(), this.sprite.getHeight());

        // Render the booster animation
        this.game.batch.draw(
                this.boosterAnimation.getKeyFrame(this.boosterTime, true),
                this.position.x, this.position.y - this.sprite.getWidth() - 2,
                this.sprite.getWidth(), this.sprite.getHeight()
        );

        // Render all player bullets
        for (PlayerBullet bullet : bullets) {
            bullet.render();
        }
    }

    // A function for handling player movement & key inputs
    private void playerMovement(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) { // If pressed A move left
            this.sprite.setRegion(this.playerRegions[0]); // set the player sprite to the first region in player sprite
            this.position.x -= 200 * delta; // Move the player x position to the left
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) { // If pressed D move right
            this.sprite.setRegion(this.playerRegions[2]); // set the player sprite to the last region in player sprite
            this.position.x += 200 * delta; // Move the player x position to the right
        } else {
            this.sprite.setRegion(this.playerRegions[1]); // If there is no keys being pressed set the player sprite to the default region
        }

        // Player shooting
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && this.canShoot) { // Shoot if space is pressed
            // Create a player bullet
            PlayerBullet bullet = new PlayerBullet(
                    new Vector2((position.x + this.sprite.getWidth() / 2) - 16, position.y + this.sprite.getHeight() + 2),
                    this.game
            );

            bullets.add(bullet); // Add the bullet into array
            lastBullet = TimeUtils.millis(); // Keep track of the bullet time
        }
    }

    public ArrayList<PlayerBullet> getBullets() {
        return this.bullets;
    }

    public void dispose() {
        sprite.getTexture().dispose();

        for (PlayerBullet bullet : bullets) {
            bullet.dispose();
        }
    }
}