package com.group8.spaceshooter.lib;

public interface SpaceShooterObject {
    void update(float delta);
    void render(float delta);
    void renderShape(float delta);
    void dispose();
}
