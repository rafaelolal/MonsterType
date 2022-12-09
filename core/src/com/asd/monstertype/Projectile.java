package com.asd.monstertype;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Projectile {

    // characteristics

    private float movementSpeed;

    // position & dimensions

    private float xPosition, yPosition; // lower center
    private float width, height;

    // graphics

    TextureRegion projectileTextureRegion;

    public Projectile(float movementSpeed, float width, float height, float xCenter, float yCenter, TextureRegion projectileTextureRegion) {

        this.movementSpeed = movementSpeed;
        this.width = width;
        this.height = height;
        this.xPosition = xCenter - width/2;
        this.yPosition = yCenter - height/2;
        this.projectileTextureRegion = projectileTextureRegion;

    }

    public void draw(Batch batch) {

        batch.draw(projectileTextureRegion, xPosition, yPosition, width, height);

    }

}
