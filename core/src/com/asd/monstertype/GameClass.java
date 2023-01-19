package com.asd.monstertype;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameClass extends Game {

	protected GameScreen gameScreen;
	protected MainMenuScreen mainMenuScreen;

	public final static int MENU = 0;
	public final static int GAME = 1;

	@Override
	public void create() {

		mainMenuScreen = new MainMenuScreen(this);
		setScreen(mainMenuScreen);

		//gameScreen = new GameScreen(this, false);
		//setScreen(gameScreen);

	}

	public void newScreen() {

		dispose();

	}

	public void changeScreen(int screen) {

		switch(screen) {

			case MENU:

				if (mainMenuScreen == null) {

					mainMenuScreen = new MainMenuScreen(this);
					this.setScreen(mainMenuScreen);
					break;

				}

			case GAME:

				if (gameScreen == null) {

					gameScreen = new GameScreen(this, false);
					this.setScreen(gameScreen);
					break;

				}

		}

	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		gameScreen.resize(width, height);
	}

	@Override
	public void pause() {

		gameScreen.pause();

	}

}
