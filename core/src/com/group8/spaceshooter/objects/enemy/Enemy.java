package com.group8.spaceshooter.objects.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.group8.spaceshooter.SpaceShooter;
import com.group8.spaceshooter.Utils;

public class Enemy {
    private final SpaceShooter game;

    private static final float ENEMY_SPEED = 80;

    private final Animation<TextureRegion> animation;
    private float animationTime;

    private final Vector2 position;

    public Enemy(EnemyTypes enemyType, SpaceShooter game) {
        this.game = game;

        this.animation = Utils.createAnimation(new Texture(enemyType.getFilename()), enemyType.getAnimationCols(), enemyType.getAnimationRows(), 0.1f);
        this.animationTime = 0f;

        this.position = new Vector2(MathUtils.random(0, SpaceShooter.GAME_WIDTH - 32), SpaceShooter.GAME_HEIGHT + 32);
    }

    // Update enemy
    public void update(float delta) {
        this.animationTime += delta;

        this.position.y -= ENEMY_SPEED * delta;
    }

    // Render enemy
    public void render(float delta) {
        this.game.batch.draw(
                this.animation.getKeyFrame(animationTime, true),
                this.position.x, this.position.y,
                32, 32
        );
    }

    public Vector2 getPosition() {
        return position;
    }

    // Dispose enemy assets
    public void dispose() {}
}
