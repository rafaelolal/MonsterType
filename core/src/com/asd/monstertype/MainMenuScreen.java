package com.asd.monstertype;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.*;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Locale;


public class MainMenuScreen extends ScreenAdapter {

    private GameClass parent;

    //screen

    private Camera camera;
    private Viewport viewport;
    private Stage stage;
    private Skin skin;
    private Table mainTable;

    //graphics
    private SpriteBatch batch;

    private Texture[] backgrounds;

    // timing

    private float[] backgroundOffsets = {0, 0, 0};
    private float backgroundMaxScrollingSpeed;

    // world parameters

    private final int WORLD_WIDTH = 1280;
    private final int WORLD_HEIGHT = 720;

    private boolean playWithAI;

    // HUD

    BitmapFont font1;

    // font Generators

    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;

    // audio

    private Music mainMenuMusic;

    public MainMenuScreen(GameClass gameClass) {

        // GameClass Setup

        parent = gameClass;

        // initialize backgrounds

        backgrounds = new Texture[3];

        backgrounds[0] = Assets.manager.get(Assets.mainMenuBackground, Texture.class);
        backgrounds[1] = Assets.manager.get(Assets.mainMenuBackground2, Texture.class);
        backgrounds[2] = Assets.manager.get(Assets.mainMenuBackground3, Texture.class);

        backgroundMaxScrollingSpeed =  (float)(WORLD_HEIGHT) / 2;

        // FONTS & HUD

        initializeFonts();

        skin = Assets.manager.get(Assets.SKIN);

        // audio

        mainMenuMusic = Assets.manager.get(Assets.mainMenuMusic, Music.class);

        mainMenuMusic.setVolume(0.7f);
        mainMenuMusic.play();


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

        font1.getData().setScale(1.5f);

    }

    @Override
    public void render(float deltaTime) {

        batch.begin();

        detectInput(deltaTime);

        renderBackgrounds(deltaTime);

        font1.draw(batch, "Breaking Bad Gme", WORLD_WIDTH * 1/4, WORLD_HEIGHT * 2/3 + font1.getXHeight(), (float)WORLD_WIDTH * 1/2, Align.center, false);

        stage.act();
        stage.draw();

        batch.end();

    }

    private void renderBackgrounds(float deltaTime) {

        backgroundOffsets[0] += (deltaTime * backgroundMaxScrollingSpeed * 1/2);

        batch.draw(backgrounds[0], 0, -backgroundOffsets[0], WORLD_WIDTH, WORLD_HEIGHT);
        batch.draw(backgrounds[0], 0, -backgroundOffsets[0] + WORLD_HEIGHT, WORLD_WIDTH, WORLD_HEIGHT);

        if (backgroundOffsets[0] > WORLD_HEIGHT) {
            backgroundOffsets[0] = 0;
        }

        backgroundOffsets[1] += (deltaTime * backgroundMaxScrollingSpeed * 3/2);

        batch.draw(backgrounds[1], -backgroundOffsets[1], 60, WORLD_WIDTH / (5/2), WORLD_HEIGHT / (5/2));
        batch.draw(backgrounds[1], -backgroundOffsets[1] + WORLD_WIDTH, 60, WORLD_WIDTH / (5/2), WORLD_HEIGHT / (5/2));

        if (backgroundOffsets[1] > WORLD_WIDTH) {
            backgroundOffsets[1] = 0;
        }

        backgroundOffsets[2] += (deltaTime * backgroundMaxScrollingSpeed * 3/2);

        batch.draw(backgrounds[2], backgroundOffsets[2], 450, WORLD_WIDTH / 3, WORLD_HEIGHT / 3);
        batch.draw(backgrounds[2], backgroundOffsets[2] - WORLD_WIDTH, 450, WORLD_WIDTH / 3, WORLD_HEIGHT / 3);

        if (backgroundOffsets[2] > WORLD_WIDTH) {
            backgroundOffsets[2] = 0;
        }



    }

    protected boolean getPlayWithAI() {return playWithAI;}

    private void detectInput(float deltaTime) {

        // escape

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {

            System.exit(0);

        }

    }

    @Override
    public void show() {

        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        stage = new Stage(viewport);

        mainTable = new Table();
        mainTable.setFillParent(true);

        stage.addActor(mainTable);

        mainTable.setPosition(0, -100);

        addButton("Play With AI").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent even, float x, float y)
            {
                playWithAI = true;
                dispose();
                parent.changeScreen(GameClass.GAME);
            }
        });
        addButton("Play With Two Players").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent even, float x, float y)
            {
                playWithAI = false;
                dispose();
                parent.changeScreen(GameClass.GAME);
            }
        });

        Gdx.input.setInputProcessor(stage);

    }

    private TextButton addButton(String name) {

        TextButton button = new TextButton(name, skin);

        mainTable.add(button).width(700).height(50).padBottom(25);
        mainTable.row();
        return button;

    }

    @Override
    public void resize(int width, int height) {

        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);

    }

    @Override
    public void dispose() {

        // fonts

        font1.dispose();

        // audio

        mainMenuMusic.dispose();

        stage.dispose();

        //batch.dispose();


    }

}
