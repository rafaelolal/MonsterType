package com.asd.monstertype;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameClass extends Game {

	protected MainMenuScreen mainMenuScreen;
	protected SettingsScreen settingsScreen;
	protected GameScreen gameScreen;

	public final static int MENU = 0;
	public final static int SETTINGS = 1;
	public final static int GAME = 2;

	public static float volume;


	@Override
	public void create() {

		Assets.load();
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

				}

		}

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
