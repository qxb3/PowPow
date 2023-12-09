package com.group8.spaceshooter.objects;

import com.group8.spaceshooter.SpaceShooter;
import com.group8.spaceshooter.lib.SpaceShooterObject;

public class Enemy implements SpaceShooterObject {
    private final SpaceShooter game;

    public Enemy(SpaceShooter game) {
        this.game = game;
    }

    // Update stuff for the enemy
    @Override
    public void update(float delta) {}

    // Render enemy stuff
    @Override
    public void render(float delta) {}

    @Override
    public void renderShape(float delta) {}

    // Dispose enemy assets
    @Override
    public void dispose() {}
}
