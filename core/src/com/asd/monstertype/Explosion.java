package com.asd.monstertype;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Explosion {

    private Animation<TextureRegion> explosionAnimation;
    private float explosionTimer = 0;

    private Rectangle boundingBox;

    Explosion(Texture texture, Rectangle boundingBox, int tileAmount, float animationTime) {

        this.boundingBox = boundingBox;

        // split explosion texture

        TextureRegion[][] textureRegion2D = TextureRegion.split(texture, texture.getWidth() / tileAmount, texture.getHeight() / tileAmount);

        // convert to 1D array

        TextureRegion[] textureRegion1D = new TextureRegion[tileAmount * tileAmount];

        int index = 0;

        for (int i = 0; i < tileAmount; i++) {
            for (int j = 0; j < tileAmount; j++) {

                textureRegion1D[index] = textureRegion2D[i][j];
                index++;

            }

        }

        explosionAnimation = new Animation<TextureRegion>(animationTime / (tileAmount * tileAmount), textureRegion1D);

    }

    public void update(float deltaTime) {

        explosionTimer += deltaTime;

    }

    public void draw(Batch batch) {

        batch.draw(explosionAnimation.getKeyFrame(explosionTimer),
                boundingBox.getX(), boundingBox.getY(),
                boundingBox.getWidth(), boundingBox.getHeight());

    }

    public boolean animationFinished() {

        return explosionAnimation.isAnimationFinished(explosionTimer);

    }

}
