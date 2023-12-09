package com.group8.spaceshooter.lib;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Utils {
    public static TextureRegion[] getRegions(Texture texture, int cols, int rows) {
        TextureRegion[][] spriteSheet = TextureRegion.split(texture,
                texture.getWidth() / cols,
                texture.getHeight() / rows);

        TextureRegion[] regions = new TextureRegion[cols * rows];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                regions[index++] = spriteSheet[i][j];
            }
        }

        return regions;
    }

    public static Animation<TextureRegion> createAnimation(Texture texture, int cols, int rows, float speed) {
        TextureRegion[] frames = getRegions(texture, cols, rows);

        return new Animation<>(speed, frames);
    }

}