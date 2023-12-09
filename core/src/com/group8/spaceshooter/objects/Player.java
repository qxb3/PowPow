package com.group8.spaceshooter.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.group8.spaceshooter.SpaceShooter;
import com.group8.spaceshooter.lib.SpaceShooterObject;
import com.group8.spaceshooter.lib.Utils;

import java.util.Arrays;

public class Player implements  SpaceShooterObject {
    private final SpaceShooter game;

    private static TextureRegion[] playerRegions; // For specific region in the player sheet
    private static Sprite sprite; // The player sprite

    private static Animation<TextureRegion> boosterAnimation; // The booster animation below the player
    private static float boosterTime; // For tracking the animation time

    private static Vector2 position; // Player's position

    public Player(SpaceShooter game) {
        this.game = game;

        // Initialized player regions
        playerRegions = Utils.getRegions(new Texture("player/player.png"), 3, 1);

        // Set the first player sprite will be the middle of the player sheet
        sprite = new Sprite(playerRegions[1]);
        sprite.setSize(32, 32); // Set size to 32x32 pixels

        // Create the booster animation and set the speed to 0.2f
        boosterAnimation = Utils.createAnimation(new Texture("player/booster.png"), 2, 1, 0.2f);
        boosterTime = 0f;

        // Set the x position to the middle of the screen and y to player height * 2
        position = new Vector2((SpaceShooter.GAME_WIDTH / 2) - (sprite.getWidth() / 2), sprite.getHeight() * 2);
    }

    @Override
    public void update(float delta) {
        boosterTime += delta; // Increase the booster time as the game goes on

        // Makes sure the player is in the world boundaries
        if (position.x < 0) position.x = 0;
        if (position.x > SpaceShooter.GAME_WIDTH - sprite.getWidth())
            position.x = SpaceShooter.GAME_WIDTH - sprite.getWidth();

        if (Gdx.input.isKeyPressed(Input.Keys.A)) { // If pressed A move left
            sprite.setRegion(playerRegions[0]); // set the player sprite to the first region in player sprite
            position.x -= 200 * delta; // Move the player x position to the left
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) { // If pressed D move right
            sprite.setRegion(playerRegions[2]); // set the player sprite to the last region in player sprite
            position.x += 200 * delta; // Move the player x position to the right
        } else {
            sprite.setRegion(playerRegions[1]); // If there is no keys being pressed set the player sprite to the default region
        }
    }

    @Override
    public void render(float delta) {
        // Render the player
        this.game.batch.draw(sprite, position.x, position.y, sprite.getWidth(), sprite.getHeight());

        // Render the booster animation
        this.game.batch.draw(boosterAnimation.getKeyFrame(boosterTime, true), position.x, position.y - sprite.getWidth() - 2, sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void renderShape(float delta) {}

    @Override
    public void dispose() {
        sprite.getTexture().dispose();
    }
}