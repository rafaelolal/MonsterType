package com.asd.monstertype;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class GameScreen implements Screen {

    //screen

    private Camera camera;
    private Viewport viewport;

    //graphics

    private SpriteBatch batch;

    private Texture[] backgrounds;

    //timing

    private float[] backgroundOffsets = {0, 0, 0, 0};
    private float backgroundMaxScrollingSpeed;

    // world parameters

    private final int WORLD_WIDTH = 1280;
    private final int WORLD_HEIGHT = 720;

    // audio

    private Music music;


    GameScreen() {

        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        backgrounds = new Texture[4];
        backgrounds[0] = new Texture("gameBackground(TEMP).jpg");
        backgrounds[1] = new Texture("walterWhite.png");
        backgrounds[2] = new Texture("jessePinkman.png");
        backgrounds[3] = new Texture("saulGoodman.png");

        backgroundMaxScrollingSpeed =  (float)(WORLD_HEIGHT) / 2;

        batch = new SpriteBatch();

        Music saulMusic = Gdx.audio.newMusic(Gdx.files.internal("saulMusic.mp3"));
        Music saulSound = Gdx.audio.newMusic(Gdx.files.internal("saulSound.mp3"));
        Music vineBoom = Gdx.audio.newMusic(Gdx.files.internal("vineBoom.mp3"));

        saulMusic.setVolume(0.5f);
        saulSound.setVolume(0.6f);
        vineBoom.setVolume(0.7f);

        saulMusic.setLooping(true);
        saulSound.setLooping(true);
        vineBoom.setLooping(true);

        saulMusic.play();
        saulSound.play();
        vineBoom.play();

    }

    @Override
    public void render(float deltaTime) {

        batch.begin();

        renderBackground(deltaTime);

        batch.end();

    }

    private void renderBackground(float deltaTime) {

        backgroundOffsets[0] += (deltaTime * backgroundMaxScrollingSpeed / 8);
        backgroundOffsets[1] += (deltaTime * backgroundMaxScrollingSpeed / 1);
        backgroundOffsets[2] += (deltaTime * backgroundMaxScrollingSpeed / 1);
        backgroundOffsets[3] += (deltaTime * backgroundMaxScrollingSpeed);

        /*for (int layer = 0; layer < backgroundOffsets.length; layer++) {

            if (backgroundOffsets[layer] > WORLD_HEIGHT) {
                backgroundOffsets[layer] = 0;
            }

            batch.draw(backgrounds[layer], 0, -backgroundOffsets[layer], WORLD_WIDTH, WORLD_HEIGHT);
            batch.draw(backgrounds[layer], 0, -backgroundOffsets[layer] + WORLD_HEIGHT, WORLD_WIDTH, WORLD_HEIGHT);

        }*/

        batch.draw(backgrounds[0], 0, -backgroundOffsets[0], WORLD_WIDTH, WORLD_HEIGHT);
        batch.draw(backgrounds[0], 0, -backgroundOffsets[0] + WORLD_HEIGHT, WORLD_WIDTH, WORLD_HEIGHT);

        if (backgroundOffsets[0] > WORLD_HEIGHT) {
            backgroundOffsets[0] = 0;
        }

        batch.draw(backgrounds[1], 500, -backgroundOffsets[1], WORLD_WIDTH, WORLD_HEIGHT);
        batch.draw(backgrounds[1], 500, -backgroundOffsets[1] + WORLD_HEIGHT, WORLD_WIDTH, WORLD_HEIGHT);

        if (backgroundOffsets[1] > WORLD_HEIGHT) {
            backgroundOffsets[1] = 0;
        }

        batch.draw(backgrounds[2], -500, backgroundOffsets[2], WORLD_WIDTH, WORLD_HEIGHT);
        batch.draw(backgrounds[2], -500, backgroundOffsets[2] - WORLD_HEIGHT, WORLD_WIDTH, WORLD_HEIGHT);

        if (backgroundOffsets[2] > WORLD_HEIGHT) {
            backgroundOffsets[2] = 0;
        }

        batch.draw(backgrounds[3], 0, -backgroundOffsets[3], WORLD_WIDTH, WORLD_HEIGHT);
        batch.draw(backgrounds[3], 0, -backgroundOffsets[3] + WORLD_HEIGHT, WORLD_WIDTH, WORLD_HEIGHT);

        if (backgroundOffsets[3] > WORLD_HEIGHT) {
            backgroundOffsets[3] = 0;
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
    public void dispose() {

    }

    @Override
    public void show() {

    }

}
