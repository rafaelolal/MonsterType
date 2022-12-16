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

    Explosion(Texture texture, Rectangle boundingBox, float animationTime) {

        this.boundingBox = boundingBox;

        // split explosion texture

        TextureRegion[][] textureRegion2D = TextureRegion.split(texture, texture.getWidth() / 4, texture.getHeight() / 4);

        // convert to 1D array

        TextureRegion[] textureRegion1D = new TextureRegion[16];

        int index = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                textureRegion1D[index] = textureRegion2D[i][j];
                index++;

            }

        }

        explosionAnimation = new Animation<TextureRegion>(animationTime / 16, textureRegion1D);

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
