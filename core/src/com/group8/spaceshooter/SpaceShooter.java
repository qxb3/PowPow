package com.group8.spaceshooter;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.group8.spaceshooter.screens.MenuScreen;

public class SpaceShooter extends Game implements ApplicationListener {
	// Game Dimension
	public static final float GAME_WIDTH = 400;
	public static final float GAME_HEIGHT = 600;

	// Spritebatch for our game, This is used to draw stuff on the screen
	public SpriteBatch batch;

	@Override
	public void create() {
		// Set the window to our game dimensions
		Gdx.graphics.setWindowedMode((int) GAME_WIDTH, (int) GAME_HEIGHT);

		// Initialized SpriteBatch
		batch = new SpriteBatch();

		// Set the screen to the MenuScreen
		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void render() {
		// Clear the background to black
		ScreenUtils.clear(0, 0, 0, 1);

		// Render Screens
		super.render();
	}
	
	@Override
	public void dispose() {
		// Dispose assets to avoid memory leaks
		batch.dispose();
	}
}
