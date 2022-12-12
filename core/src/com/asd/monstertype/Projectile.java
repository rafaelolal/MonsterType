package com.asd.monstertype;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Projectile {

    // characteristics

    protected float movementSpeed;

    // position & dimensions

    protected float xPosition, yPosition; // lower center
    protected float width, height;

    // graphics

    TextureRegion projectileTextureRegion;

    public Projectile(float movementSpeed, float width, float height, float xCenter, float yCenter, TextureRegion projectileTextureRegion) {

        this.movementSpeed = movementSpeed;
        this.width = width;
        this.height = height;
        this.xPosition = xCenter - width / 2;
        this.yPosition = yCenter - height / 2;
        this.projectileTextureRegion = projectileTextureRegion;

    }

    public void draw(Batch batch) {

        batch.draw(projectileTextureRegion, xPosition, yPosition, width, height);

    }

    public Rectangle getBoundingBox() {

        return new Rectangle(xPosition, yPosition, width, height);

    }

}
