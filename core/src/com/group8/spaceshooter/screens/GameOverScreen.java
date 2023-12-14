package com.group8.spaceshooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.group8.spaceshooter.SpaceShooter;

public class GameOverScreen implements Screen {
    private final SpaceShooter game;

    private BitmapFont font; // The games font
    private Stage stage; // For ui

    public GameOverScreen(final SpaceShooter game) {
        this.game = game;
    }

    @Override
    public void show() {
        // Create the font
        font = new BitmapFont(Gdx.files.internal("font/font.fnt"));
        font.setUseIntegerPositions(true);

        // Initialize stage
        stage = new Stage();

        // Create a table so our ui elements can align easily
        Table table = new Table();
        table.setFillParent(true);
        table.setBounds(0, 0, SpaceShooter.GAME_WIDTH, SpaceShooter.GAME_HEIGHT);

        // Label style for game over
        LabelStyle gameOverStyle = new LabelStyle();
        gameOverStyle.font = font;
        gameOverStyle.fontColor = Color.RED;

        // Create a label for "Game Over
        Label gameOver = new Label("Game Over", gameOverStyle);
        gameOver.setAlignment(Align.center);
        table.add(gameOver).center(); // Add this label to the screen

        table.row().padTop(64); // New row and add spacing to the top

        // Button style for play again button
        TextButton.TextButtonStyle playAgainButtonStyle = new TextButton.TextButtonStyle();
        playAgainButtonStyle.font = font;
        playAgainButtonStyle.fontColor = Color.GREEN;

        // Create play again button
        TextButton playAgain = new TextButton("Play Again", playAgainButtonStyle);
        table.add(playAgain).center();

        // Handle clicks for the play again button
        playAgain.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game)); // Set the screen to game screen if clicked

                return true;
            }
        });

        table.row().padTop(16); // New row and add space to the top

        // Button style for menu button
        TextButton.TextButtonStyle menuButtonStyle = new TextButton.TextButtonStyle();
        menuButtonStyle.font = font;
        menuButtonStyle.fontColor = Color.BLUE;

        // Create menu button
        TextButton menuButton = new TextButton("Game Menu", menuButtonStyle);
        table.add(menuButton).center();

        // Handle clicks for menu button
        menuButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MenuScreen(game)); // Set the screen to menu screen if clicked

                return true;
            }
        });

        stage.addActor(table); // Add the table to the stage (ui)
        Gdx.input.setInputProcessor(stage); // Set the input processor to the stage so buttons work
    }

    @Override
    public void hide() {
        stage.clear();
    }

    private void update(float delta) {
        stage.act(); // Update the stage
    }

    public void render(float delta) {
        this.update(delta);

        stage.draw(); // Render the screen
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

}