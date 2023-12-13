package com.group8.spaceshooter.objects.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.group8.spaceshooter.SpaceShooter;

public class PlayerBullet {
    private final SpaceShooter game;

    // The bullet speed
    private static final float BULLET_SPEED = 800f;

    private final Vector2 position; // Bullet position
    private final Sprite sprite; // Bullet sprite

    private final Rectangle rectangle; // Rectangle for the player, will be used for the collision

    public  PlayerBullet(Vector2 position, SpaceShooter game) {
        this.game = game;

        // Set the position to the position that is passed in the constructor
        this.position = position;

        // Initialize the sprite
        this.sprite = new Sprite(new Texture("player/beam-bullet.png"));
        this.sprite.setSize(32, 32);

        // Initialize the rectangle
        this.rectangle = new Rectangle();
        this.rectangle.setSize(sprite.getWidth(), sprite.getHeight());
    }

    // Update the bullet position
    public void update(float delta) {
        this.position.y +=  BULLET_SPEED * delta; // Move the bullet up
        this.rectangle.setPosition(this.position); // Also update the rectangle
    }

    // Render the bullet
    public void render() {
        this.game.batch.draw(sprite, position.x, position.y, sprite.getWidth(), sprite.getHeight());
    }

    // Getter for the bullet position
    public Vector2 getPosition() {
        return this.position;
    }

    public Rectangle getRectangle() {
        return this.rectangle;
    }

    public void dispose() {
        this.sprite.getTexture().dispose();
    }
}
