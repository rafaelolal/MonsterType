package com.asd.monstertype;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public abstract class Character {

    // characteristics

    protected float movementSpeed;

    // position & dimensions

    protected Rectangle boundingBox;

    // graphics

    protected TextureRegion characterTextureRegion, projectileTextureRegion;

    // projectile information

    protected float projectileWidth = 50, projectileHeight = 50;
    protected float projectileMovementSpeed = 500;
    protected float timeBetweenShots = 0.2f;
    protected float timeSinceLastShot = 0;


    public Character(float movementSpeed, float width, float height, float xCenter, float yCenter,
                     TextureRegion characterTextureRegion, TextureRegion projectileTextureRegion) {

        this.movementSpeed = movementSpeed;

        this.boundingBox = new Rectangle(xCenter - width / 2, yCenter - height / 2, width, height);

        this.characterTextureRegion = characterTextureRegion;
        this.projectileTextureRegion = projectileTextureRegion;

    }

    public void update(float deltaTime) {

        timeSinceLastShot += deltaTime;

    }

    public void setTimeBetweenShots(float timeBetweenShots) {

        this.timeBetweenShots = timeBetweenShots;

    }

    public boolean canFireProjectile() {

        return (timeSinceLastShot - timeBetweenShots >= 0);

    }

    public abstract Projectile[] fireProjectiles();

    public boolean intersects(Rectangle otherRectangle) {

        return boundingBox.overlaps(otherRectangle);

    }

    public boolean hitAndCheckDestroyed(Projectile projectile) {

        return true;

    }

    public void translate(float xChange, float yChange) {

        boundingBox.setPosition(boundingBox.getX() + xChange, boundingBox.getY() + yChange);

    }

    public void draw(Batch batch) {

        batch.draw(characterTextureRegion, boundingBox.getX(), boundingBox.getY(), boundingBox.getWidth(), boundingBox.getHeight());

    }

}
