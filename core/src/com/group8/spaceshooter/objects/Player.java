package com.group8.spaceshooter.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.group8.spaceshooter.SpaceShooter;
import com.group8.spaceshooter.Utils;

public class Player {
    private final SpaceShooter game;

    private final TextureRegion[] playerRegions; // For specific region in the player sheet
    private final Sprite sprite; // The player sprite

    private final Animation<TextureRegion> boosterAnimation; // The booster animation below the player
    private float boosterTime; // For tracking the animation time

    private final Vector2 position; // Player's position

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
    }

    public void update(float delta) {
        this.boosterTime += delta; // Increase the booster time as the game goes on

        // Makes sure the player is in the world boundaries
        if (this.position.x < 0) this.position.x = 0;
        if (this.position.x > SpaceShooter.GAME_WIDTH - this.sprite.getWidth())
            this.position.x = SpaceShooter.GAME_WIDTH - this.sprite.getWidth();

        if (Gdx.input.isKeyPressed(Input.Keys.A)) { // If pressed A move left
            this.sprite.setRegion(this.playerRegions[0]); // set the player sprite to the first region in player sprite
            this.position.x -= 200 * delta; // Move the player x position to the left
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) { // If pressed D move right
            this.sprite.setRegion(this.playerRegions[2]); // set the player sprite to the last region in player sprite
            this.position.x += 200 * delta; // Move the player x position to the right
        } else {
            this.sprite.setRegion(this.playerRegions[1]); // If there is no keys being pressed set the player sprite to the default region
        }
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
    }

    public void dispose() {
        sprite.getTexture().dispose();
    }
}