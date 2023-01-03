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


public class GameScreen implements Screen {

    //screen

    private Camera camera;
    private Viewport viewport;

    //graphics

    private SpriteBatch batch;
    private TextureAtlas textureAtlas;

    private TextureRegion[] backgrounds;
    private float backgroundHeight;

    private Texture explosionTexture;

    private TextureRegion playerCharacterTextureRegion, enemyCharacterTextureRegion, playerProjectileTextureRegion, enemyProjectileTextureRegion;

    //timing

    private float[] backgroundOffsets = {0, 0, 0, 0, 0, 0, 0, 0};
    private float backgroundMaxScrollingSpeed;

    // world parameters

    private final int WORLD_WIDTH = 1280;
    private final int WORLD_HEIGHT = 720;
    private boolean explodeLevel = false;

    // game objects

    private PlayerCharacter playerCharacter;
    private EnemyCharacter enemyCharacter;
    private LinkedList<Projectile> playerProjectileList;
    private LinkedList<Projectile> enemyProjectileList;
    private LinkedList<Explosion> explosionList;

    private int playerScore = 0;
    private int enemyScore = 0;


    // audio

    private Music saulMusic;
    private Music saulSound;
    private Music vineBoom;

    private Sound explosion = Gdx.audio.newSound(Gdx.files.internal("explosion.mp3"));
    private Sound playerHurt = Gdx.audio.newSound(Gdx.files.internal("mikeHurt.mp3"));
    private Sound enemyHurt = Gdx.audio.newSound(Gdx.files.internal("hectorHurt.mp3"));

    // HUD

    BitmapFont font1;
    BitmapFont font2;
    float hudVerticalMargin, hudLeftX, hudRightX, hudCenterX, hudRow1Y, hudRow2Y, hudSectionWidth;

    GameScreen() {

        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        // texture atlas setup

        textureAtlas = new TextureAtlas("images.atlas");

        // initialize backgrounds

        backgrounds = new TextureRegion[8];

        backgrounds[0] = textureAtlas.findRegion("gameBackground(TEMP)");
        backgrounds[1] = textureAtlas.findRegion("walterWhite");
        backgrounds[2] = textureAtlas.findRegion("jessePinkman");
        backgrounds[3] = textureAtlas.findRegion("saulGoodman");

        backgrounds[4] = textureAtlas.findRegion("rain");
        backgrounds[5] = textureAtlas.findRegion("rain");
        backgrounds[6] = textureAtlas.findRegion("rain");
        backgrounds[7] = textureAtlas.findRegion("rain");

        backgroundHeight = WORLD_HEIGHT * 2;
        backgroundMaxScrollingSpeed =  (float)(WORLD_HEIGHT) / 2;

        // initialize texture regions

        playerCharacterTextureRegion = textureAtlas.findRegion("mikeEhrm");
        enemyCharacterTextureRegion = textureAtlas.findRegion("hectorSalamanca");

        playerProjectileTextureRegion = textureAtlas.findRegion("hectorBell");
        enemyProjectileTextureRegion = textureAtlas.findRegion("hectorBell");

        explosionTexture = new Texture("explosion.png");

        // game objects set up

        playerCharacter = new PlayerCharacter(500, 200, 140,
                WORLD_WIDTH / 2, WORLD_HEIGHT * 1/4,
                playerCharacterTextureRegion, playerProjectileTextureRegion);

        //playerCharacter.changeProjectileCharacteristics(playerCharacter.projectileHeight, playerCharacter.projectileWidth, playerCharacter.projectileMovementSpeed, 0.1f);

        enemyCharacter = new EnemyCharacter(500, 200, 140,
                WORLD_WIDTH / 2.5f, WORLD_HEIGHT * 3/4,
                enemyCharacterTextureRegion, enemyProjectileTextureRegion);

        playerProjectileList = new LinkedList<>();
        enemyProjectileList = new LinkedList<>();

        explosionList = new LinkedList<>();

        batch = new SpriteBatch();

        prepareHUD();

        saulMusic = Gdx.audio.newMusic(Gdx.files.internal("saulMusic.mp3"));
        saulSound = Gdx.audio.newMusic(Gdx.files.internal("saulSound.mp3"));
        vineBoom = Gdx.audio.newMusic(Gdx.files.internal("vineBoom.mp3"));

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

    private void prepareHUD() {

        // create bitmap fonts from file

        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("BreakingBad.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        fontParameter.size = 80;
        fontParameter.borderWidth = 4;
        fontParameter.color = new Color(0, 255, 0, 1);
        fontParameter.borderColor = new Color(0, 0, 0, 3);

        font1 = fontGenerator.generateFont(fontParameter);

        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("EdgeFont.otf"));

        font2 = fontGenerator.generateFont(fontParameter);


        // scale font

        font1.getData().setScale(0.8f);
        font2.getData().setScale(0.6f);

        // calculate hud parameters

        hudVerticalMargin = font1.getCapHeight() / 2;

        hudLeftX = hudVerticalMargin;

        hudRightX = (float)WORLD_WIDTH * 2/3 - hudLeftX;

        hudCenterX = (float)WORLD_WIDTH * 1/3;

        hudRow1Y = WORLD_HEIGHT - hudVerticalMargin;

        hudRow2Y = hudRow1Y - hudVerticalMargin - font1.getCapHeight();

        hudSectionWidth = hudCenterX;

    }

    @Override
    public void render(float deltaTime) {

        batch.begin();

        detectInput(deltaTime);

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

        renderProjectiles(deltaTime);

        // detect collisions

        detectCollisions();

        // explosions

        updateAndRenderExplosions(deltaTime);

        // hud

        updateAndRenderHUD();

        if (enemyScore >= 10 || playerScore >= 10) {

            explodeLevel();

        }

        batch.end();

    }

    private void detectInput(float deltaTime) {

        // escape

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {

            System.exit(0);

        }

        // keyboard input

        float rightLimit, leftLimit, upLimit, downLimit;

        rightLimit = WORLD_WIDTH - playerCharacter.boundingBox.getX() - playerCharacter.boundingBox.getWidth();
        leftLimit = -playerCharacter.boundingBox.getX();

        upLimit = (float)WORLD_HEIGHT / 2 - playerCharacter.boundingBox.getY() - playerCharacter.boundingBox.getHeight();
        downLimit = -playerCharacter.boundingBox.getY();

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && rightLimit > 0) {

            float xChange = Math.min(playerCharacter.movementSpeed * deltaTime, rightLimit);

            playerCharacter.translate(xChange, 0f);

        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && leftLimit < 0) {

            float xChange = Math.max(-playerCharacter.movementSpeed * deltaTime, leftLimit);

            playerCharacter.translate(xChange, 0f);

        }


        if (Gdx.input.isKeyPressed(Input.Keys.UP) && upLimit > 0) {

            float yChange = Math.min(playerCharacter.movementSpeed * deltaTime, upLimit);

            playerCharacter.translate(0f, yChange);

        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && downLimit < 0) {

            float yChange = Math.max(-playerCharacter.movementSpeed * deltaTime, leftLimit);

            playerCharacter.translate(0f, yChange);

        }

        // ENEMY PLAYER CONTROL, COMMENT OUT TO REMOVE FUNCTION

        rightLimit = WORLD_WIDTH - enemyCharacter.boundingBox.getX() - enemyCharacter.boundingBox.getWidth();
        leftLimit = -enemyCharacter.boundingBox.getX();

        upLimit = WORLD_HEIGHT - enemyCharacter.boundingBox.getY() - enemyCharacter.boundingBox.getHeight();
        downLimit = (float)WORLD_HEIGHT / 2 - enemyCharacter.boundingBox.getY();

        if (Gdx.input.isKeyPressed(Input.Keys.D) && rightLimit > 0) {

            float xChange = Math.min(enemyCharacter.movementSpeed * deltaTime, rightLimit);

            enemyCharacter.translate(xChange, 0f);

        }

        if (Gdx.input.isKeyPressed(Input.Keys.A) && leftLimit < 0) {

            float xChange = Math.max(-enemyCharacter.movementSpeed * deltaTime, leftLimit);

            enemyCharacter.translate(xChange, 0f);

        }


        if (Gdx.input.isKeyPressed(Input.Keys.W) && upLimit > 0) {

            float yChange = Math.min(enemyCharacter.movementSpeed * deltaTime, upLimit);

            enemyCharacter.translate(0f, yChange);

        }

        if (Gdx.input.isKeyPressed(Input.Keys.S) && downLimit < 0) {

            float yChange = Math.max(-enemyCharacter.movementSpeed * deltaTime, leftLimit);

            enemyCharacter.translate(0f, yChange);

        }

        //moveEnemies(deltaTime); ENEMY AI, UNCOMMENT TO FUNCTION

    }

    private void moveEnemies(float deltaTime) {

        float rightLimit, leftLimit, upLimit, downLimit;

        rightLimit = WORLD_WIDTH - enemyCharacter.boundingBox.getX() - enemyCharacter.boundingBox.getWidth();
        leftLimit = -enemyCharacter.boundingBox.getX();

        upLimit = WORLD_HEIGHT - enemyCharacter.boundingBox.getY() - enemyCharacter.boundingBox.getHeight();
        downLimit = (float)WORLD_HEIGHT / 2 -enemyCharacter.boundingBox.getY();

        float xMove = enemyCharacter.getDirectionVector().x * enemyCharacter.movementSpeed * deltaTime;
        float yMove = enemyCharacter.getDirectionVector().y * enemyCharacter.movementSpeed * deltaTime;

        if (xMove > 0) {

            xMove = Math.min(xMove, rightLimit);

        } else {

            xMove = Math.max(xMove, leftLimit);

        }

        if (yMove > 0) {

            yMove = Math.min(yMove, upLimit);

        } else {

            yMove = Math.max(yMove, downLimit);

        }

        enemyCharacter.translate(xMove, yMove);

    }

    private void renderBackground(float deltaTime) {

        backgroundOffsets[0] += (deltaTime * backgroundMaxScrollingSpeed * 1/8);
        backgroundOffsets[1] += (deltaTime * backgroundMaxScrollingSpeed * 1);
        backgroundOffsets[2] += (deltaTime * backgroundMaxScrollingSpeed * 1);
        backgroundOffsets[3] += (deltaTime * backgroundMaxScrollingSpeed * 2);

        backgroundOffsets[4] += (deltaTime * backgroundMaxScrollingSpeed * 2);
        backgroundOffsets[5] += (deltaTime * backgroundMaxScrollingSpeed * 4);
        backgroundOffsets[6] += (deltaTime * backgroundMaxScrollingSpeed * 3);
        backgroundOffsets[7] += (deltaTime * backgroundMaxScrollingSpeed * 8);

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

        // FOR PARALLAX BACKGROUND WHEN REPLACED IN THE FUTURE

        for (int layer = 4; layer < backgroundOffsets.length; layer++) {

            if (backgroundOffsets[layer] > WORLD_HEIGHT) {
                backgroundOffsets[layer] = 0;
            }

            batch.draw(backgrounds[layer], 0, -backgroundOffsets[layer], WORLD_WIDTH, WORLD_HEIGHT);
            batch.draw(backgrounds[layer], 0, -backgroundOffsets[layer] + WORLD_HEIGHT, WORLD_WIDTH, WORLD_HEIGHT);

        }

    }

    private void renderProjectiles(float deltaTime) {

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

        // draw & remove old projectiles

        ListIterator<Projectile> iterator = playerProjectileList.listIterator();

        while (iterator.hasNext()) {

            Projectile projectile = iterator.next();
            projectile.draw(batch);
            projectile.boundingBox.y += (projectile.movementSpeed * deltaTime);

            if (projectile.boundingBox.y > WORLD_HEIGHT) {
                iterator.remove();
            }

        }

        iterator = enemyProjectileList.listIterator();

        while (iterator.hasNext()) {

            Projectile projectile = iterator.next();
            projectile.draw(batch);
            projectile.boundingBox.y -= projectile.movementSpeed * deltaTime;

            if (projectile.boundingBox.y + projectile.boundingBox.height < 0) {
                iterator.remove();
            }

        }


    }

    private void updateAndRenderExplosions(float deltaTime) {

        ListIterator<Explosion> explosionListIterator = explosionList.listIterator();

        while (explosionListIterator.hasNext()) {

            Explosion explosion = explosionListIterator.next();
            explosion.update(deltaTime);

            if (explosion.animationFinished()) {

                explosionListIterator.remove();

            } else {

                explosion.draw(batch);

            }

        }

    }

    private void explodeLevel() {

        explodeLevel = true;

        playerCharacter.stop();
        enemyCharacter.stop();

        saulMusic.stop();
        saulSound.stop();
        vineBoom.stop();

    }

    private void updateAndRenderHUD() {

        // top row

        font1.draw(batch, "Player One Score", hudLeftX, hudRow1Y, hudSectionWidth, Align.left, false);
        font1.draw(batch, "Enemy Score", hudRightX, hudRow1Y, hudSectionWidth, Align.right, false);

        // second row

        font2.draw(batch, String.format(Locale.getDefault(), "%05d", playerScore), hudLeftX, hudRow2Y, hudSectionWidth, Align.left, false);

        font2.draw(batch, String.format(Locale.getDefault(), "%05d", enemyScore), hudRightX, hudRow2Y, hudSectionWidth, Align.right, false);

    }

    private void detectCollisions() {

        // player projectiles

        ListIterator<Projectile> iterator = playerProjectileList.listIterator();

        while (iterator.hasNext()) {

            Projectile projectile = iterator.next();

            if (enemyCharacter.intersects(projectile.boundingBox)) {

                // collision with enemy character
                if (enemyCharacter.hitAndCheckDestroyed(projectile)) {

                    explosionList.add(new Explosion(explosionTexture, new Rectangle(enemyCharacter.boundingBox), 0.7f));
                    explosion.play(0.5f);
                    enemyHurt.play(1f);
                    playerScore++;

                }

                iterator.remove();

            }

        }

        // enemy projectiles

        iterator = enemyProjectileList.listIterator();

        while (iterator.hasNext()) {

            Projectile projectile = iterator.next();

            if (playerCharacter.intersects(projectile.boundingBox)) {

                // collision with player character
                if (playerCharacter.hitAndCheckDestroyed(projectile)) {

                    explosionList.add(new Explosion(explosionTexture, new Rectangle(playerCharacter.boundingBox), 0.7f));
                    explosion.play(0.5f);
                    playerHurt.play(1f);
                    enemyScore++;

                }

                iterator.remove();

            }

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
