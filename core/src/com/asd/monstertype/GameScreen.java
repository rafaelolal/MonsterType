package com.asd.monstertype;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.LinkedList;
import java.util.ListIterator;


public class GameScreen implements Screen {

    //screen

    private Camera camera;
    private Viewport viewport;

    //graphics

    private SpriteBatch batch;
    private TextureAtlas textureAtlas;

    private TextureRegion[] backgrounds;
    private float backgroundHeight;

    private TextureRegion playerCharacterTextureRegion, enemyCharacterTextureRegion, playerProjectileTextureRegion, enemyProjectileTextureRegion;

    //timing

    private float[] backgroundOffsets = {0, 0, 0, 0};
    private float backgroundMaxScrollingSpeed;

    // world parameters

    private final int WORLD_WIDTH = 1280;
    private final int WORLD_HEIGHT = 720;

    // game objects

    private Character playerCharacter;
    private Character enemyCharacter;
    private LinkedList<Projectile> playerProjectileList;
    private LinkedList<Projectile> enemyProjectileList;


    // audio

    private Music music;


    GameScreen() {

        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        // texture atlas setup

        textureAtlas = new TextureAtlas("images.atlas");

        // initialize backgrounds

        backgrounds = new TextureRegion[4];
        backgrounds[0] = textureAtlas.findRegion("gameBackground(TEMP)");
        backgrounds[1] = textureAtlas.findRegion("walterWhite");
        backgrounds[2] = textureAtlas.findRegion("jessePinkman");
        backgrounds[3] = textureAtlas.findRegion("saulGoodman");

        backgroundHeight = WORLD_HEIGHT * 2;
        backgroundMaxScrollingSpeed =  (float)(WORLD_HEIGHT) / 2;

        // initialize texture regions

        playerCharacterTextureRegion = textureAtlas.findRegion("mikeEhrm");
        enemyCharacterTextureRegion = textureAtlas.findRegion("hectorSalamanca");

        playerProjectileTextureRegion = textureAtlas.findRegion("saulGoodman");
        enemyProjectileTextureRegion = textureAtlas.findRegion("walterWhite");

        // game objects set up

        playerCharacter = new PlayerCharacter(2, 200, 140,
                WORLD_WIDTH / 2, WORLD_HEIGHT * 1/4,
                playerCharacterTextureRegion, playerProjectileTextureRegion);
        enemyCharacter = new EnemyCharacter(2, 200, 140,
                WORLD_WIDTH / 2, WORLD_HEIGHT * 3/4,
                enemyCharacterTextureRegion, enemyProjectileTextureRegion);

        playerProjectileList = new LinkedList<>();
        enemyProjectileList = new LinkedList<>();

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

        // update characters

        playerCharacter.update(deltaTime);
        enemyCharacter.update(deltaTime);

        // draw background


        renderBackground(deltaTime);


        // draw playerCharacter

        playerCharacter.draw(batch);

        // draw enemyCharacter

        enemyCharacter.draw(batch);

        // projectiles

        // create projectiles

        if (playerCharacter.canFireProjectile()) {

            Projectile[] projectiles = playerCharacter.fireProjectiles();

            for (Projectile projectile: projectiles) {
                playerProjectileList.add(projectile);
            }

        }

        if (enemyCharacter.canFireProjectile()) {

            Projectile[] projectiles = enemyCharacter.fireProjectiles();

            for (Projectile projectile: projectiles) {
                enemyProjectileList.add(projectile);
            }

        }


        // draw projectiles

        // draw & remove old projectiles

        ListIterator<Projectile> iterator = playerProjectileList.listIterator();

        while (iterator.hasNext()) {

            Projectile projectile = iterator.next();
            projectile.draw(batch);
            projectile.yPosition += (projectile.movementSpeed * deltaTime);

            if (projectile.yPosition > WORLD_HEIGHT) {
                iterator.remove();
            }

        }

        iterator = enemyProjectileList.listIterator();

        while (iterator.hasNext()) {

            Projectile projectile = iterator.next();
            projectile.draw(batch);
            projectile.yPosition -= projectile.movementSpeed * deltaTime;

            if (projectile.yPosition + projectile.height < 0) {
                iterator.remove();
            }

        }




        batch.end();

    }

    /*private void detectInput(float deltaTime) {

        // keyboard input

        float leftLimit, rightLimit, upLimit, downLimit;

        //leftLimit = playerCharacter


        // mouse input

    }*/

    private void renderBackground(float deltaTime) {

        backgroundOffsets[0] += (deltaTime * backgroundMaxScrollingSpeed * 1/8);
        backgroundOffsets[1] += (deltaTime * backgroundMaxScrollingSpeed * 1);
        backgroundOffsets[2] += (deltaTime * backgroundMaxScrollingSpeed * 1);
        backgroundOffsets[3] += (deltaTime * backgroundMaxScrollingSpeed * 2);

        // FOR PARALLAX BACKGROUND WHEN REPLACED IN THE FUTURE

        /*for (int layer = 0; layer < backgroundOffsets.length; layer++) {

            if (backgroundOffsets[layer] > WORLD_HEIGHT) {
                backgroundOffsets[layer] = 0;
            }

            batch.draw(backgrounds[layer], 0, -backgroundOffsets[layer], WORLD_WIDTH, WORLD_HEIGHT);
            batch.draw(backgrounds[layer], 0, -backgroundOffsets[layer] + WORLD_HEIGHT, WORLD_WIDTH, WORLD_HEIGHT);

        }*/

        // TEMP CODE FOR BREAKING BAD BACKGROUND CHARACTERS

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
