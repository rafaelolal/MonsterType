package com.asd.monstertype;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Character {

    // characteristics

    protected float movementSpeed;

    // position & dimensions

    protected float xPosition, yPosition; // lower-left corner
    protected float width, height;

    // graphics

    protected TextureRegion characterTextureRegion, projectileTextureRegion;

    // projectile information

    protected float projectileWidth = 100, projectileHeight = 100;
    protected float projectileMovementSpeed = 300;
    protected float timeBetweenShots = 10;
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

    public boolean canFireProjectile() {

        return (timeSinceLastShot - timeBetweenShots >= 0);

    }

    public abstract Projectile[] fireProjectiles();


    public void draw(Batch batch) {

        batch.draw(characterTextureRegion, xPosition, yPosition, width, height);

    }

}
