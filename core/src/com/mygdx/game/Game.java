package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gamestates.GameState;
import com.mygdx.game.managers.GameStateManager;

public class Game extends ApplicationAdapter {

	private GameStateManager gsm;
	
	@Override
	public void create () {
		gsm = new GameStateManager();
		gsm.setState(GameStateManager.MENU);
	}

	@Override
	public void render () {

		//Clear screen
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.draw();
	}
}
