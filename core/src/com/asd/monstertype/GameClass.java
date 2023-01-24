package com.asd.monstertype;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameClass extends Game {

	// screens

	protected MainMenuScreen mainMenuScreen;
	protected SettingsScreen settingsScreen;
	protected GameScreen gameScreen;

	// screen values

	public final static int MENU = 0;
	public final static int SETTINGS = 1;
	public final static int GAME = 2;

	// screen parameters

	protected int screenReturn;

	// audio

	protected Music mainMenuMusic;
	protected Music saulMusic;
	protected Music saulSound;
	protected Music vineBoom;
	public static float masterMusicVolume = 1f;
	public static float masterSoundVolume = 1f;



	@Override
	public void create() {

		Assets.loadTextures();
		Assets.loadMusic();

		Assets.manager.finishLoading();

		mainMenuScreen = new MainMenuScreen(this);
		setScreen(mainMenuScreen);

	}

	public void changeScreen(int screen) {

		switch(screen) {

			case MENU:

				if (gameScreen != null) {

					gameScreen = null;
					mainMenuScreen = new MainMenuScreen(this);
					this.setScreen(mainMenuScreen);
					break;

				} else {

					settingsScreen = null;
					mainMenuScreen = new MainMenuScreen(this);
					this.setScreen(mainMenuScreen);
					break;

				}

			case SETTINGS:

				if (settingsScreen == null) {

					mainMenuScreen = null;
					settingsScreen = new SettingsScreen(this);
					this.setScreen(settingsScreen);
					break;

				} else {

					this.setScreen(settingsScreen);

				}

				break;

			case GAME:

				if (gameScreen == null) {

					//mainMenuScreen = null;

					if (mainMenuScreen.getPlayWithAI()) {

						gameScreen = new GameScreen(this, true);
						this.setScreen(gameScreen);
						break;

					} else {

						gameScreen = new GameScreen(this, false);
						this.setScreen(gameScreen);
						break;

					}

				} else {

					this.setScreen(gameScreen);

				}

		}

	}

	public void setMasterMusicVolume(float volume) {

		masterMusicVolume = volume;

		if (mainMenuMusic != null) mainMenuMusic.setVolume(0.7f * volume);
		if (saulMusic != null) saulMusic.setVolume(0.7f * volume);
		if (saulSound != null) saulSound.setVolume(0.8f * volume);
		if (vineBoom != null) vineBoom.setVolume(0.8f * volume);

	}

	@Override
	public void dispose() {

		super.dispose();
		Assets.dispose();

	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {


	}

	@Override
	public void pause() {



	}

}
