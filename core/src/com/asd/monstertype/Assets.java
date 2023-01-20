package com.asd.monstertype;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Assets {

    public static AssetManager manager = new AssetManager();

    // textures

    public static final String mainMenuBackground = "walter.png";
    public static final String textureAtlas = "images.atlas";
    public static final String explosionTexture = "explosion.png";

    // music

    public static final String saulMusic = "saulMusic.mp3";
    public static final String saulSound = "saulSound.mp3";
    public static final String vineBoom = "vineBoom.mp3";
    public static final String endLevelTheme = "endLevelTheme.mp3";

    // sounds

    public static final String hectorHurt = "hectorHurt.mp3";
    public static final String mikeHurt = "mikeHurt.mp3";
    public static final String explosionSound = "explosion.mp3";

    public static final String bigExplosionSound = "bigExplosion.mp3";

    // fonts

    public static final String font1generator = "BreakingBad.otf";
    public static final String font2generator = "EdgeFont.otf";

    public static void load() {

        // textures

        manager.load(mainMenuBackground, Texture.class);
        manager.load(textureAtlas, TextureAtlas.class);
        manager.load(explosionTexture, Texture.class);

        // music

        manager.load(saulMusic, Music.class);
        manager.load(saulSound, Music.class);
        manager.load(vineBoom, Music.class);
        manager.load(endLevelTheme, Music.class);

        // sounds

        manager.load(hectorHurt, Sound.class);
        manager.load(mikeHurt, Sound.class);
        manager.load(explosionSound, Sound.class);
        manager.load(bigExplosionSound, Sound.class);

        // fonts

        //manager.load(font1generator, FreeTypeFontGenerator.class);
        //manager.load(font2generator, FreeTypeFontGenerator.class);


    }

    public static void dispose() {

        manager.dispose();

    }


}

