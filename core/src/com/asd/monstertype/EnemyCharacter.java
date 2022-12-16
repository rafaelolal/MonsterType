package com.asd.monstertype;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class EnemyCharacter extends Character {

    //
    Vector2 directionVector;
    float timeSinceLastDirectionChange = 0;
    float directionChangeFrequency = 0.8f;

    public EnemyCharacter(float movementSpeed, float width, float height, float xCenter, float yCenter,
                           TextureRegion characterTextureRegion, TextureRegion projectileTextureRegion) {

        super(movementSpeed, width, height, xCenter, yCenter,
                characterTextureRegion, projectileTextureRegion);

        directionVector = new Vector2(0, -1);

    }

    public Vector2 getDirectionVector() {

        return directionVector;

    }

    private void randomizeDirectionVector() {

        double bearing = Math.random() * 6.283185; // 0 to 2 PI
        directionVector.x = (float)Math.sin(bearing);
        directionVector.y = (float)Math.cos(bearing);

    }

    @Override
    public void update(float deltaTime) {

        super.update(deltaTime);
        timeSinceLastDirectionChange += deltaTime;

        if (timeSinceLastDirectionChange > directionChangeFrequency) {

            randomizeDirectionVector();
            timeSinceLastDirectionChange -= directionChangeFrequency;

        }

    }

    @Override
    public Projectile[] fireProjectiles() {

        Projectile[] projectiles = new Projectile[1];
        projectiles[0] = new Projectile(projectileMovementSpeed, projectileWidth, projectileHeight, boundingBox.getX() + boundingBox.getWidth() / 2, boundingBox.getY() + boundingBox.getHeight() / 2, projectileTextureRegion);

        timeSinceLastShot = 0;

        return projectiles;

    }


}
