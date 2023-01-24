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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.*;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Locale;


public class SettingsScreen extends ScreenAdapter {

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

    private float[] backgroundOffsets = {0, 0, 0, 0, 0};
    private float backgroundMaxScrollingSpeed;

    // world parameters

    private final int WORLD_WIDTH = 1280;
    private final int WORLD_HEIGHT = 720;

    // HUD

    BitmapFont font1;
    BitmapFont font2;

    private final int MUSICVOLUME = 0;
    private final int SOUNDVOLUME = 1;

    private Slider musicSlider;
    private Slider soundSlider;

    // font Generators

    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;

    public SettingsScreen(GameClass gameClass) {

        // GameClass Setup

        parent = gameClass;

        // initialize backgrounds

        backgrounds = new Texture[5];

        backgrounds[0] = Assets.manager.get(Assets.mainMenuBackground0, Texture.class);
        backgrounds[1] = Assets.manager.get(Assets.mainMenuBackground1, Texture.class);
        backgrounds[2] = Assets.manager.get(Assets.mainMenuBackground2, Texture.class);
        backgrounds[3] = Assets.manager.get(Assets.mainMenuBackground3, Texture.class);
        backgrounds[4] = Assets.manager.get(Assets.mainMenuBackground4, Texture.class);

        backgroundMaxScrollingSpeed =  (float)(WORLD_HEIGHT) / 2;

        // FONTS & HUD

        initializeFonts();

        skin = Assets.manager.get(Assets.SKIN);

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

        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("EdgeFont.otf"));

        font2 = fontGenerator.generateFont(fontParameter);

        font2.getData().setScale(0.5f);

    }

    @Override
    public void render(float deltaTime) {

        batch.begin();

        detectInput(deltaTime);

        renderBackgrounds(deltaTime);

        font1.draw(batch, "Settings", WORLD_WIDTH * 1/4, WORLD_HEIGHT * 3/4 + font1.getXHeight(), (float)WORLD_WIDTH * 1/2, Align.center, false);

        font2.draw(batch, "Music Volume", musicSlider.getX() - 450, musicSlider.getY() + font2.getXHeight() + 15, (float)WORLD_WIDTH * 1/2, Align.center, false);

        font2.draw(batch, "Sound Volume", musicSlider.getX() - 450, soundSlider.getY() + font2.getXHeight() + 15, (float)WORLD_WIDTH * 1/2, Align.center, false);

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

        backgroundOffsets[1] += (deltaTime * backgroundMaxScrollingSpeed * (3/2));

        batch.draw(backgrounds[1], -backgroundOffsets[1], 60, WORLD_WIDTH / (5/2), WORLD_HEIGHT / (5/2));
        batch.draw(backgrounds[1], -backgroundOffsets[1] + WORLD_WIDTH, 60, WORLD_WIDTH / (5/2), WORLD_HEIGHT / (5/2));

        if (backgroundOffsets[1] > WORLD_WIDTH) {
            backgroundOffsets[1] = 0;
        }

        backgroundOffsets[2] += (deltaTime * backgroundMaxScrollingSpeed * (3/2));

        batch.draw(backgrounds[2], backgroundOffsets[2], 450, WORLD_WIDTH / 3, WORLD_HEIGHT / 3);
        batch.draw(backgrounds[2], backgroundOffsets[2] - WORLD_WIDTH, 450, WORLD_WIDTH / 3, WORLD_HEIGHT / 3);

        if (backgroundOffsets[2] > WORLD_WIDTH) {
            backgroundOffsets[2] = 0;
        }

        backgroundOffsets[3] += (deltaTime * backgroundMaxScrollingSpeed);

        batch.draw(backgrounds[3], 160, -backgroundOffsets[3], WORLD_WIDTH / 3, WORLD_HEIGHT / 3);
        batch.draw(backgrounds[3], 160, -backgroundOffsets[3] + WORLD_HEIGHT, WORLD_WIDTH / 3, WORLD_HEIGHT / 3);

        if (backgroundOffsets[3] > WORLD_HEIGHT) {
            backgroundOffsets[3] = 0;
        }

        backgroundOffsets[4] += (deltaTime * backgroundMaxScrollingSpeed);

        batch.draw(backgrounds[4], 650, backgroundOffsets[4], WORLD_WIDTH / 3, WORLD_HEIGHT / 3);
        batch.draw(backgrounds[4], 650, backgroundOffsets[4] - WORLD_HEIGHT, WORLD_WIDTH / 3, WORLD_HEIGHT / 3);

        if (backgroundOffsets[4] > WORLD_HEIGHT) {
            backgroundOffsets[4] = 0;
        }

    }

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

        addSlider(MUSICVOLUME).addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {

                parent.setMasterMusicVolume(musicSlider.getValue());
                parent.masterMusicVolume = musicSlider.getValue();

            }
        });

        addSlider(SOUNDVOLUME).addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {

                parent.masterSoundVolume = soundSlider.getValue();

            }
        });


        addTextButton("Back").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent even, float x, float y)
            {

                if (parent.screenReturn == parent.MENU) {
                    dispose();
                    parent.changeScreen(GameClass.MENU);
                } else {
                    parent.changeScreen(GameClass.GAME);
                }
            }
        });

        Gdx.input.setInputProcessor(stage);

    }

    private TextButton addTextButton(String name) {

        TextButton button = new TextButton(name, skin);

        mainTable.add(button).width(700).height(50).padBottom(25).setActorY(400);
        mainTable.row();
        return button;

    }

    private Slider addSlider(int slider) {

        if (slider == MUSICVOLUME) {

            musicSlider = new Slider(0f, 1f, 0.005f, false, skin);
            musicSlider.setValue(parent.masterMusicVolume);

            mainTable.add(musicSlider).width(400).height(50).padBottom(25);
            mainTable.row();

            return musicSlider;

        } else if (slider == SOUNDVOLUME) {

            soundSlider = new Slider(0f, 1f, 0.01f, false, skin);
            soundSlider.setValue(parent.masterSoundVolume);

            mainTable.add(soundSlider).width(400).height(50).padBottom(25);
            mainTable.row();

            return soundSlider;

        }

        return null;

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

        //mainMenuMusic.dispose();

        stage.dispose();

        //batch.dispose();


    }

}
