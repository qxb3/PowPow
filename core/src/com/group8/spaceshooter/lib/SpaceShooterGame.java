package com.group8.spaceshooter.lib;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public abstract class SpaceShooterGame extends Game {
    protected SpaceShooterScreen screen;

    @Override
    public void dispose () {
        if (screen != null) screen.hide();
    }

    @Override
    public void pause() {
        if (screen != null) screen.pause();
    }

    @Override
    public void resume() {
        if (screen != null) screen.resume();
    }

    @Override
    public void render() {
        if (screen != null) screen.render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void resize(int width, int height) {
        if (screen != null) screen.resize(width, height);
    }

    /** Sets the current screen. {@link SpaceShooterScreen#hide()} is called on any old screen, and {@link SpaceShooterScreen#show()} is called on the new
     * screen, if any.
     * @param screen may be {@code null} */
    public void setScreen(SpaceShooterScreen screen) {
        if (this.screen != null) this.screen.hide();
        this.screen = screen;
        if (this.screen != null) {
            this.screen.show();
            this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
    }

    /** @return the currently active {@link SpaceShooterScreen}. */
    public SpaceShooterScreen getScreen() {
        return screen;
    }
}
