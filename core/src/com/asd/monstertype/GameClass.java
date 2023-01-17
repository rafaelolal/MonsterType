package com.asd.monstertype;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameClass extends Game {

	GameScreen gameScreen;

	@Override
	public void create() {

		gameScreen = new GameScreen();
		setScreen(gameScreen);

	}

	public void newScreen() {

		gameScreen = new GameScreen();
		this.setScreen(gameScreen);

	}

	@Override
	public void dispose() {
		gameScreen.dispose();
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
