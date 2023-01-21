package com.asd.monstertype;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {

    public static AssetManager manager = new AssetManager();

    // textures

    public static final String mainMenuBackground = "backgrounds/walter.png";
    public static final String mainMenuBackground2 = "backgrounds/walterJunior.png";
    public static final String mainMenuBackground3 = "backgrounds/saulGoodman.png";
    public static final String textureAtlas = "images.atlas";
    public static final String explosionTexture = "explosion.png";

    // music

    public static final String mainMenuMusic = "mainMenuMusic.mp3";
    public static final String saulMusic = "saulMusic.mp3";
    public static final String saulSound = "saulSound.mp3";
    public static final String vineBoom = "vineBoom.mp3";
    public static final String endLevelTheme = "endLevelTheme.mp3";

    // sounds

    public static final String hectorHurt = "hectorHurt.mp3";
    public static final String mikeHurt = "mikeHurt.mp3";
    public static final String explosionSound = "explosion.mp3";
    public static final String bigExplosionSound = "bigExplosion.mp3";

    // ui skin

    public static final AssetDescriptor<Skin> SKIN = new AssetDescriptor<Skin>("uiskin/uiskin.json", Skin.class, new SkinLoader.SkinParameter("uiskin/uiskin.atlas"));

    public static void load() {

        // textures

        manager.load(mainMenuBackground, Texture.class);
        manager.load(mainMenuBackground2, Texture.class);
        manager.load(mainMenuBackground3, Texture.class);
        manager.load(textureAtlas, TextureAtlas.class);
        manager.load(explosionTexture, Texture.class);

        // music

        manager.load(mainMenuMusic, Music.class);
        manager.load(saulMusic, Music.class);
        manager.load(saulSound, Music.class);
        manager.load(vineBoom, Music.class);
        manager.load(endLevelTheme, Music.class);

        // sounds

        manager.load(hectorHurt, Sound.class);
        manager.load(mikeHurt, Sound.class);
        manager.load(explosionSound, Sound.class);
        manager.load(bigExplosionSound, Sound.class);

        // uiSkin

        manager.load(SKIN);

    }

    public static void dispose() {

        manager.dispose();

    }


}

