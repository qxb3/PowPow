package com.group8.spaceshooter;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.group8.spaceshooter.screens.GameScreen;
import com.group8.spaceshooter.screens.MenuScreen;

public class SpaceShooter extends Game implements ApplicationListener {
	// Game Dimension
	public static final float GAME_WIDTH = 400f;
	public static final float GAME_HEIGHT = 600f;

	// Our Renderers that will be used throughout the game
	public SpriteBatch batch;
	public ShapeRenderer shapeRenderer;

	// The camera that will be used throughout the game
	public OrthographicCamera camera;

	// The stars background on the back
	private StarsBackground starsBackground;

	@Override
	public void create() {
		// Set the window to our game dimensions
		Gdx.graphics.setWindowedMode((int) GAME_WIDTH, (int) GAME_HEIGHT);

		// Initialize SpriteBatch & shapeRenderer
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();

		// Initialize camera & Set the width & height to the game dimensions
		camera = new OrthographicCamera();
		camera.setToOrtho(false, (int) SpaceShooter.GAME_WIDTH, (int) SpaceShooter.GAME_HEIGHT);

		// Initialize Background Stars
		starsBackground = new StarsBackground(this);

		// Create the Background Music
		Sound backgroundMusic = Gdx.audio.newSound(Gdx.files.internal("sfx/bg.mp3"));

		long id = backgroundMusic.play(); // Play the background music
		backgroundMusic.setVolume(id, 0.2f); // Set the volume
		backgroundMusic.setLooping(id, true); // Make it loop

		// Set the screen to the MenuScreen
		this.setScreen(new MenuScreen(this));
	}

	// All the updates will happen here
	private void update() {
		// Update the camera
		camera.update();

		// Update stars
		starsBackground.update();
	}

	@Override
	public void render() {
		// Clear the background to black
		ScreenUtils.clear(0, 0, 0, 1);

		// Call the update function
		this.update();

		// Render stars
		starsBackground.render();

		// All render will happen here
		batch.setProjectionMatrix(camera.combined); // Set the Sprite renderer align to camera
		shapeRenderer.setProjectionMatrix(camera.combined); // Set the shape renderer align to camera

		// Render the current screen
		this.screen.render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void dispose() {
		// Dispose assets to avoid memory leaks
		batch.dispose();
		shapeRenderer.dispose();
		this.screen.dispose();
	}
}