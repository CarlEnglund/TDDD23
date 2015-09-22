package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by englund on 08/09/15.
 */
public class StartScreen implements Screen {

    private SpriteBatch batch;
    private Texture splash;

    public StartScreen() {
        super();
        batch = new SpriteBatch();
        splash = new Texture("assets/splash.png");
    }


    public void render(float delta) {

        //Clear the screen by setting frame and depth buffers to a
        //solid red color, then clear everything with glClear() and what buffers to clear
        //https://github.com/libgdx/libgdx/wiki/Clearing-the-screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Set up the batch for drawing and draw it as a rectangle over the
        //screen width and height
        batch.begin();
        batch.draw(splash, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
    }

    public void hide() {}
    public void pause() {}
    public void resume() {}
    public void show() {}
    public void resize(int width, int height) {}
    public void dispose() {}

}
