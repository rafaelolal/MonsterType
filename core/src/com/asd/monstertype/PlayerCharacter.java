package com.asd.monstertype;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerCharacter extends Character {

    public PlayerCharacter(float movementSpeed, float width, float height, float xCenter, float yCenter,
                           TextureRegion characterTextureRegion, TextureRegion projectileTextureRegion) {

        super(movementSpeed, width, height, xCenter, yCenter,
                characterTextureRegion, projectileTextureRegion);

    }

    @Override
    public Projectile[] fireProjectiles() {

        Projectile[] projectiles = new Projectile[1];
        projectiles[0] = new Projectile(projectileMovementSpeed, projectileWidth, projectileHeight, boundingBox.getX() + boundingBox.getWidth() / 2, boundingBox.getY() + boundingBox.getHeight() / 2, projectileTextureRegion);

        timeSinceLastShot = 0;

        return projectiles;

    }


}
