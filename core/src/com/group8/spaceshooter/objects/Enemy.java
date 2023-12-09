package com.group8.spaceshooter.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.group8.spaceshooter.SpaceShooter;
import com.group8.spaceshooter.lib.SpaceShooterObject;
import com.group8.spaceshooter.lib.Utils;

public class Enemy implements SpaceShooterObject {
    private final SpaceShooter game;

    private static final float ENEMY_SPEED = 15;

    private static Animation<TextureRegion> animation;
    private static float animationTime;

    private static Vector2 position;

    public Enemy(SpaceShooter game) {
        this.game = game;

        animation = Utils.createAnimation(new Texture("enemies/candy.png"), 4, 1, 0.1f);
        animationTime = 0f;

        position = new Vector2(MathUtils.random(0, SpaceShooter.GAME_WIDTH - 32), SpaceShooter.GAME_HEIGHT);
    }

    // Update enemy
    @Override
    public void update(float delta) {
        animationTime += delta;

        position.y -= ENEMY_SPEED * delta;
    }

    // Render enemy
    @Override
    public void render(float delta) {
        this.game.batch.draw(animation.getKeyFrame(animationTime, true), position.x, position.y, 32, 32);
    }

    @Override
    public void renderShape(float delta) {}

    // Dispose enemy assets
    @Override
    public void dispose() {}
}
