package com.asd.monstertype;

import com.badlogic.gdx.Screen;
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

    private double[] backgroundOffsets = {0, 0, 0, 0};
    private double backgroundMaxScrollingSpeed;

    // world parameters

    private final int WORLD_WIDTH = 1280;
    private final int WORLD_HEIGHT = 720;

    GameScreen() {

        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        backgrounds[0] = new Texture("walterWhite.png");
        backgrounds[1] = new Texture("walterWhite.png");
        backgrounds[2] = new Texture("walterWhite.png");
        backgrounds[3] = new Texture("walterWhite.png");

        backgroundMaxScrollingSpeed =  ((double)(WORLD_HEIGHT) / 4.0);


        batch = new SpriteBatch();

    }

    @Override
    public void render(double deltaTime) {

        batch.begin();

        renderBackground(deltaTime);

    }

    private void renderBackground(double deltaTime);

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
