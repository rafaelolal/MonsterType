package com.asd.monstertype;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Character {

    // characteristics

    float movementSpeed;

    // position & dimensions

    float xPosition, yPosition; // lower-left corner
    float width, height;

    // graphics

    TextureRegion characterTexture;

    public Character(float movementSpeed, float width, float height, float xCenter, float yCenter, TextureRegion characterTexture) {

        this.movementSpeed = movementSpeed;
        this.width = width;
        this.height = height;
        this.xPosition = xCenter - width/2;
        this.yPosition = yCenter - height/2;
        this.characterTexture = characterTexture;

    }

    public void draw(Batch batch) {

        batch.draw(characterTexture, xPosition, yPosition, width, height);

    }

}
