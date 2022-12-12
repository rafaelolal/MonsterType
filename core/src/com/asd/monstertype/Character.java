package com.asd.monstertype;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public abstract class Character {

    // characteristics

    protected float movementSpeed;

    // position & dimensions

    protected float xPosition, yPosition; // lower-left corner
    protected float width, height;

    // graphics

    protected TextureRegion characterTextureRegion, projectileTextureRegion;

    // projectile information

    protected float projectileWidth = 50, projectileHeight = 50;
    protected float projectileMovementSpeed = 400;
    protected float timeBetweenShots = 0.5f;
    protected float timeSinceLastShot = 0;


    public Character(float movementSpeed, float width, float height, float xCenter, float yCenter,
                     TextureRegion characterTextureRegion, TextureRegion projectileTextureRegion) {

        this.movementSpeed = movementSpeed;
        this.width = width;
        this.height = height;
        this.xPosition = xCenter - width / 2;
        this.yPosition = yCenter - height / 2;
        this.characterTextureRegion = characterTextureRegion;
        this.projectileTextureRegion = projectileTextureRegion;

    }

    public void update(float deltaTime) {

        timeSinceLastShot += deltaTime;

    }

    public void changeProjectileCharacteristics(float projectileWidth, float projectileHeight, float projectileMovementSpeed, float timeBetweenShots) {

        this.projectileWidth = projectileWidth;
        this.projectileHeight = projectileHeight;
        this.projectileMovementSpeed = projectileMovementSpeed;
        this.timeBetweenShots = timeBetweenShots;

    }

    public boolean canFireProjectile() {

        return (timeSinceLastShot - timeBetweenShots >= 0);

    }

    public abstract Projectile[] fireProjectiles();

    public boolean intersects(Rectangle otherRectangle) {

        Rectangle thisRectangle = new Rectangle(xPosition, yPosition, width, height);
        return thisRectangle.overlaps(otherRectangle);

    }

    public void draw(Batch batch) {

        batch.draw(characterTextureRegion, xPosition, yPosition, width, height);

    }

}
