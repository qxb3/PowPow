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

public class CreditsScreen implements Screen {
    private final SpaceShooter game;

    private BitmapFont font; // Our game's font
    private Stage stage; // UI Stage

    public CreditsScreen(final SpaceShooter game) {
        this.game = game;
    }

    @Override
    public void show() {
        // Create font
        font = new BitmapFont(Gdx.files.internal("font/font.fnt"));
        font.setUseIntegerPositions(true);

        // Make stage
        stage = new Stage();

        // Make table to align our ui elements properly
        Table table = new Table();
        table.setFillParent(true);
        table.setBounds(0, 0, SpaceShooter.GAME_WIDTH, SpaceShooter.GAME_HEIGHT);

        // Make a style label for title
        LabelStyle titleStyle = new LabelStyle();
        titleStyle.font = font;

        // Create a label
        Label creditsLabel = new Label("Credits", titleStyle);
        creditsLabel.setAlignment(Align.center);
        table.add(creditsLabel).center(); // Add this label to the table

        table.row().padTop(64); // Next row and add a space on the top

        table.add(makeMember("Adam Nuevo"));
        table.row().padTop(16);
        table.add(makeMember("Chael Mendoza"));
        table.row().padTop(16);
        table.add(makeMember("Christian Joe Lumido"));
        table.row().padTop(16);
        table.add(makeMember("Efren Dave Cahilig"));
        table.row().padTop(16);
        table.add(makeMember("Justin Karl Salimbagat"));
        table.row().padTop(16);
        table.add(makeMember("Lance Ortega"));
        table.row().padTop(16);
        table.add(makeMember("Leigh Moreno"));
        table.row().padTop(16);
        table.add(makeMember("Mark Salupan"));

        table.row(); // New row

        // Make a button style for back button
        TextButton.TextButtonStyle backButtonStyle = new TextButton.TextButtonStyle();
        backButtonStyle.font = font;
        backButtonStyle.fontColor = Color.RED;

        // Make a back button
        TextButton backButton = new TextButton("Back", backButtonStyle);
        table.add(backButton).center().padTop(32);

        // Handle clicks for this button and set the screen to menu screen
        backButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MenuScreen(game));

                return true;
            }
        });

        stage.addActor(table); // Add the table to the stage
        Gdx.input.setInputProcessor(stage); // Set the input processor to the stage so the buttons can work
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

        stage.draw(); // Draw the stage
    }

    // A function to make a member so it can be added to the ui
    private Label makeMember(String name) {
        LabelStyle memberStyle = new LabelStyle();
        memberStyle.font = font;
        memberStyle.fontColor = Color.BLUE;

        Label memberLabel = new Label(name, memberStyle);
        memberLabel.setAlignment(Align.center);

        return memberLabel;
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