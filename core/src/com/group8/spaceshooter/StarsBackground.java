package com.group8.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.group8.spaceshooter.SpaceShooter;

import java.util.ArrayList;
import java.util.Iterator;

public class StarsBackground {
    private final SpaceShooter game;

    // Stars Background
    private static ArrayList<Rectangle> stars;
    private static long lastStar;

    public StarsBackground(SpaceShooter game) {
        this.game = game;

        // Initialize stars ArrayList and spawn a star
        stars = new ArrayList<>();
    }

    public void update() {
        // Spawn a star every 10 milliseconds
        if (TimeUtils.millis() - lastStar > 20) spawnStar();

        // Update the stars, move it down and remove it if it goes off the screen
        for (int i = 0; i < stars.size(); i++) {
            Rectangle star = stars.get(i); // Get the star object in the index

            star.y -= (star.getHeight() * 120) * Gdx.graphics.getDeltaTime(); // Move the star down
            if (star.y < 0) stars.remove(star); // If the star is below & is offscreen remove it to not consume too much memory

        }
    }

    public void render() {
        this.game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled); // Begin shape renderer
        this.game.shapeRenderer.setColor(Color.WHITE); // Set shape renderer color to white

        // Render the star using the ShapeRender libgdx gives us
        for (Rectangle star : stars) {
            this.game.shapeRenderer.rect(star.x, star.y, star.width, star.height);
        }

        this.game.shapeRenderer.end(); // End shape renderer
    }

    // The Function to spawn the star
    private void spawnStar() {
        Rectangle star = new Rectangle();

        // Set the size
        star.setSize(2, MathUtils.random(4, 10));

        // Set the x position randomly in the screen, and set the y to the top of the screen offscreen
        star.setPosition(MathUtils.random(0, SpaceShooter.GAME_WIDTH), SpaceShooter.GAME_HEIGHT);

        // Add this star to the array
        stars.add(star);

        // Keep track of the time the last star has spawned
        lastStar = TimeUtils.millis();
    }
}
