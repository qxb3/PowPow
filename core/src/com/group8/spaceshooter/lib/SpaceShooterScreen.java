package com.group8.spaceshooter.lib;

import com.badlogic.gdx.Screen;

public interface SpaceShooterScreen extends Screen {
    void update(float delta);
    void renderShape(float delta);
}