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

public class MenuScreen implements Screen {
    private final SpaceShooter game;

    private BitmapFont font; // Games font
    private Stage stage; // For UI

    public MenuScreen(final SpaceShooter game) {
        this.game = game;
    }

    @Override
    public void show() {
        // Create a font
        font = new BitmapFont(Gdx.files.internal("font/font.fnt"));
        font.setUseIntegerPositions(true);

        // Make a stage
        stage = new Stage();

        // Make a table to align ui elements easily
        Table table = new Table();
        table.setFillParent(true);
        table.setBounds(0, 0, SpaceShooter.GAME_WIDTH, SpaceShooter.GAME_HEIGHT);

        // Make label style for title
        LabelStyle titleStyle = new LabelStyle();
        titleStyle.font = font;

        // Create title label
        Label titleLabel = new Label("SpaceShooter", titleStyle);
        titleLabel.setAlignment(Align.center);
        table.add(titleLabel).center(); // Add this label to the screen

        table.row(); // New Row

        // Make style for start button
        TextButton.TextButtonStyle startButtonStyle = new TextButton.TextButtonStyle();
        startButtonStyle.font = font;
        startButtonStyle.fontColor = Color.GREEN;

        // Make start button
        TextButton startButton = new TextButton("Start Game", startButtonStyle);
        table.add(startButton).center().padTop(64); // Add this button to the screen

        // Handle clicks for the start button
        startButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game)); // Set the screen to the game screen

                return true;
            }
        });

        table.row(); // New row

        // Make button style for credits button
        TextButton.TextButtonStyle creditsButtonStyle = new TextButton.TextButtonStyle();
        creditsButtonStyle.font = font;
        creditsButtonStyle.fontColor = Color.BLUE;

        // Make credits button
        TextButton creditsButton = new TextButton("Credits", creditsButtonStyle);
        table.add(creditsButton).center().padTop(16); // Add this button to the screen

        // Handle
        creditsButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new CreditsScreen(game)); // Set the screen to the credits screen if clicked

                return true;
            }
        });

        table.row(); // New row

        // Make button style for exit button
        TextButton.TextButtonStyle exitButtonStyle = new TextButton.TextButtonStyle();
        exitButtonStyle.font = font;
        exitButtonStyle.fontColor = Color.RED;

        // Make exit button
        TextButton exitButton = new TextButton("Exit", exitButtonStyle);
        table.add(exitButton).center().padTop(16); // Add this button to the screen

        // Handle click inputs to the exit button
        exitButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit(); // Exit

                return true;
            }
        });

        stage.addActor(table); // Add the table to the stage
        Gdx.input.setInputProcessor(stage); // Set the input processor to the stage so buttons work
    }

    @Override
    public void hide() {
        stage.clear();
    }

    private void update(float delta) {
        stage.act();
    }

    public void render(float delta) {
        this.update(delta);

        stage.draw();
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