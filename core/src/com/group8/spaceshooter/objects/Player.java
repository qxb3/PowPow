package com.group8.spaceshooter.objects;

import com.group8.spaceshooter.SpaceShooter;
import com.group8.spaceshooter.lib.SpaceShooterObject;

public class Player implements  SpaceShooterObject {
    private final SpaceShooter game;

    public Player(SpaceShooter game) {
        this.game = game;
    }

    @Override
    public void update() {}

    @Override
    public void render() {}

    @Override
    public void dispose() {}
}
