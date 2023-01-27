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

    public static final String textureAtlas = "images.atlas";

    // music

    public static final String mainMenuMusic = "audio/mainMenuMusic.mp3";
    public static final String saulMusic = "audio/saulMusic.mp3";
    public static final String saulSound = "audio/saulSound.mp3";
    public static final String vineBoom = "audio/vineBoom.mp3";
    public static final String endLevelTheme = "audio/endLevelTheme.mp3";

    // sounds

    public static final String hectorHurt = "audio/hectorHurt.mp3";
    public static final String mikeHurt = "audio/mikeHurt.mp3";
    public static final String explosionSound = "audio/explosion.mp3";
    public static final String bigExplosionSound = "audio/bigExplosion.mp3";

    // ui skin

    public static final AssetDescriptor<Skin> SKIN = new AssetDescriptor<Skin>("uiskin/uiskin.json", Skin.class, new SkinLoader.SkinParameter("uiskin/uiskin.atlas"));

    public static void loadTextures() {

        // textures

        manager.load(textureAtlas, TextureAtlas.class);

        // uiSkin

        manager.load(SKIN);

    }

    public static void loadMusic() {

        manager.load(mainMenuMusic, Music.class);
        manager.load(saulMusic, Music.class);
        manager.load(saulSound, Music.class);
        manager.load(vineBoom, Music.class);
        manager.load(endLevelTheme, Music.class);

    }

    public static void loadSounds() {

        manager.load(hectorHurt, Sound.class);
        manager.load(mikeHurt, Sound.class);
        manager.load(explosionSound, Sound.class);
        manager.load(bigExplosionSound, Sound.class);

        manager.finishLoading();

    }

    public static void unloadSounds() {

        manager.unload(hectorHurt);
        manager.unload(mikeHurt);
        manager.unload(explosionSound);
        manager.unload(bigExplosionSound);

    }

    public static void dispose() {

        manager.dispose();

    }


}

