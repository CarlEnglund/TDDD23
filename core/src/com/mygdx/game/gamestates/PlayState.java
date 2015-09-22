package com.mygdx.game.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.managers.GameStateManager;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


/**
 * Created by englund on 10/09/15.
 */
public class PlayState extends GameState {

    ShapeRenderer playerObject;
    TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;
    OrthographicCamera camera;



    public PlayState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        playerObject = new ShapeRenderer();
        tiledMap = new TmxMapLoader().load("assets/levelOne.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 512, 512);
        camera.update();


    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void draw() {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        playerObject.begin(ShapeRenderer.ShapeType.Filled);
        playerObject.setColor(0, 1, 0, 1);
        playerObject.rect(250, 250, 20, 20);
        playerObject.end();
        camera.update();



    }

    @Override
    public void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
                playerObject.translate(-5, 0, 0);
            else
                playerObject.translate(-5, 0, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
                playerObject.translate(5, 0, 0);
            else
                playerObject.translate(5, 0, 0);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            playerObject.translate(0, 5, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            playerObject.translate(0, -5, 0);
        }
    }

    @Override
    public void dispose() {

    }
}

