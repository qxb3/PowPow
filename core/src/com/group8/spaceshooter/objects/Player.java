package com.group8.spaceshooter.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.group8.spaceshooter.SpaceShooter;
import com.group8.spaceshooter.lib.SpaceShooterObject;

public class Player implements  SpaceShooterObject {
    private final SpaceShooter game;

    private static Sprite sprite;
    private static Vector2 position;

    public Player(SpaceShooter game) {
        this.game = game;

        sprite = new Sprite(new Texture("badlogic.jpg"));
        sprite.setSize(17, 16);

        position = new Vector2(SpaceShooter.GAME_WIDTH / 2 + sprite.getWidth() / 2, 16);;
    }

    @Override
    public void update() {}

    @Override
    public void render() {
        this.game.batch.draw(sprite, position.x, position.y, sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void dispose() {}
}