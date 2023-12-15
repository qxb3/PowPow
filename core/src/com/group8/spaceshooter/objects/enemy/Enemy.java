package com.group8.spaceshooter.objects.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.group8.spaceshooter.SpaceShooter;
import com.group8.spaceshooter.Utils;

public class Enemy {
    private final SpaceShooter game;

    public Texture texture; // Animation texture
    public Animation<TextureRegion> animation; // Enemy Animation
    public float animationTime; // For tracking the animation time

    public Vector2 position; // Enemy position
    public int health; // Enemy HP
    public Rectangle rectangle; // Rectangle for the enemy, will be used for the collision

    public Enemy(EnemyTypes enemyType, SpaceShooter game) {
        this.game = game;

        // Initialize animation variables
        this.texture = new Texture(enemyType.filename);
        this.animation = Utils.createAnimation(this.texture, enemyType.animationCols, enemyType.animationRows, 0.1f);
        this.animationTime = 0f;

        // Set the x position to random, and the y to the top of the screen
        this.position = new Vector2(MathUtils.random(0, SpaceShooter.GAME_WIDTH - 32), SpaceShooter.GAME_HEIGHT + 32);

        // Set the health based on the enemy type
        this.health = enemyType.health;

        // Initialize the rectangle
        this.rectangle = new Rectangle();
        this.rectangle.setSize(32, 32);
    }

    // Update enemy
    public void update(float delta) {
        this.animationTime += delta; // Update the animation time

        this.rectangle.setPosition(this.position); // Also update the rectangle
    }

    // Render enemy
    public void render() {
        this.game.batch.draw(
                this.animation.getKeyFrame(animationTime, true),
                this.position.x, this.position.y,
                32, 32
        );
    }

    // Decrease the hp everytime the enemy is hit and return if the enemy is dead or not
    public boolean hit() {
        this.health -= 1;

        return this.health <= 0;
    }

    // Dispose enemy assets
    public void dispose() {
        this.texture.dispose();
    }
}
