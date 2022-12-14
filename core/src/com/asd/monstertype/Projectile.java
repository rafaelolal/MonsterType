package com.asd.monstertype;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Projectile {

    // characteristics

    protected float movementSpeed;

    // position & dimensions

    protected Rectangle boundingBox;

    // graphics

    TextureRegion projectileTextureRegion;

    public Projectile(float movementSpeed, float width, float height, float xCenter, float yBottom, TextureRegion projectileTextureRegion) {

        this.movementSpeed = movementSpeed;

        this.boundingBox = new Rectangle(xCenter - width / 2, yBottom, width, height);

        this.projectileTextureRegion = projectileTextureRegion;

    }

    public void draw(Batch batch) {

        batch.draw(projectileTextureRegion, boundingBox.getX(), boundingBox.getY(), boundingBox.getWidth(), boundingBox.getHeight());

    }

}
