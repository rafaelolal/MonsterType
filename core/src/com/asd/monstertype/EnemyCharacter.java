package com.asd.monstertype;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EnemyCharacter extends Character {

    public EnemyCharacter(float movementSpeed, float width, float height, float xCenter, float yCenter,
                           TextureRegion characterTextureRegion, TextureRegion projectileTextureRegion) {

        super(movementSpeed, width, height, xCenter, yCenter,
                characterTextureRegion, projectileTextureRegion);

    }

    @Override
    public Projectile[] fireProjectiles() {

        Projectile[] projectiles = new Projectile[1];
        projectiles[0] = new Projectile(projectileMovementSpeed, projectileWidth, projectileHeight, xPosition + width / 2, yPosition + height / 2, projectileTextureRegion);

        return projectiles;

    }


}
