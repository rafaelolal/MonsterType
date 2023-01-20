package com.asd.monstertype;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Locale;


public class MainMenuScreen implements Screen {

    private GameClass parent;

    //screen

    private Camera camera;
    private Viewport viewport;

    //graphics

    private SpriteBatch batch;

    private TextureAtlas textureAtlas;

    private TextureRegion[] backgrounds;

    //timing

    private float[] backgroundOffsets = {0, 0, 0, 0, 0, 0, 0, 0};
    private float backgroundMaxScrollingSpeed;

    // world parameters

    private final int WORLD_WIDTH = 1280;
    private final int WORLD_HEIGHT = 720;

    private boolean playWithAI = true;

    // HUD

    BitmapFont font1;
    BitmapFont font2;

    // Font Generators

    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;

    MainMenuScreen(GameClass gameClass) {

        // GameClass Setup

        parent = gameClass;

        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        initializeFonts();

        batch = new SpriteBatch();

    }

    private void initializeFonts() {

        // create bitmap fonts from file

        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("BreakingBad.otf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        fontParameter.size = 80;
        fontParameter.borderWidth = 4;
        fontParameter.color = new Color(0, 255, 0, 1);
        fontParameter.borderColor = new Color(0, 0, 0, 3);

        font1 = fontGenerator.generateFont(fontParameter);

        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("EdgeFont.otf"));

        font2 = fontGenerator.generateFont(fontParameter);

    }

    @Override
    public void render(float deltaTime) {

        batch.begin();

        detectInput(deltaTime);

        batch.draw(Assets.manager.get(Assets.mainMenuBackground, Texture.class), 0, 0, WORLD_WIDTH, WORLD_HEIGHT);

        font1.draw(batch, "Breaking Bad Gme", WORLD_WIDTH * 1/4, WORLD_HEIGHT * 1/2 + font2.getXHeight(), (float)WORLD_WIDTH * 1/2, Align.center, false);

        batch.end();

    }

    protected boolean getPlayWithAI() {return playWithAI;}

    private void detectInput(float deltaTime) {

        if (Gdx.input.isKeyPressed(Input.Keys.P)) {

            parent.changeScreen(GameClass.GAME);
            dispose();

        }

        // escape

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {

            System.exit(0);

        }

    }

    @Override
    public void resize(int width, int height) {

        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {


    }

    @Override
    public void dispose() {

        batch.dispose();
        // Assets.manager.unload(Assets.mainMenuBackground); TO UNLOAD BACKGROUND WHEN REPLACED

    }

}
