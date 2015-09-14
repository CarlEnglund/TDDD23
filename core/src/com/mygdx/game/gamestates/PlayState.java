package com.mygdx.game.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.managers.GameStateManager;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


/**
 * Created by englund on 10/09/15.
 */
public class PlayState extends GameState {

    ShapeRenderer shapeRenderer;

    public PlayState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void update(float dt) {
       // System.out.println("PLAY STATE UPDATING");
        handleInput();
    }

    @Override
    public void draw() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 1, 0, 1);
        shapeRenderer.rect(5, 5, 20, 20);
        //shapeRenderer.circle(x, y, radius);
        shapeRenderer.end();
    }

    @Override
    public void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
                shapeRenderer.translate(-10, 0, 0);

            else
                shapeRenderer.translate(-10, 0, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
                shapeRenderer.translate(10, 0, 0);
            else
                shapeRenderer.translate(10, 0, 0);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
                shapeRenderer.translate(0, 10, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
                shapeRenderer.translate(0, -10, 0);
        }
    }

    @Override
    public void dispose() {

    }
}

