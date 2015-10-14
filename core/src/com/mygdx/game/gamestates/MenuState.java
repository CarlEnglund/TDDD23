package com.mygdx.game.gamestates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.managers.GameStateManager;
import javafx.scene.media.Media;
import javafx.scene.media.VideoTrack;

/**
 * Created by englund on 10/09/15.
 */
public class MenuState extends GameState {

    private SpriteBatch sb;

    private Texture dwarfTexture;
    OrthographicCamera camera;
    private BitmapFont titleFont;
    private BitmapFont font;
    TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;
    private final String title = "Låd-Leif";

    private int currentItem;
    private String[] menuItems;

    public static boolean timerMode = false;

    public MenuState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        sb = new SpriteBatch();
        dwarfTexture = new Texture("assets/dwarf.png");
        tiledMap = new TmxMapLoader().load("assets/menumap.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 512, 512);
        camera.update();
        menuItems = new String[]{
                "Normal Mode",
                "Timer Mode",
                "Levels",
                "Instructions"
        };

        titleFont = new BitmapFont();
        font = new BitmapFont();

        titleFont.setColor(Color.BLACK);


    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void draw() {

        tiledMapRenderer.setView(camera);

        tiledMapRenderer.render();

        sb.begin();

        //Draw title

        titleFont.draw(sb, title, 256, 300);
        sb.draw(dwarfTexture, 200, 280);
        sb.draw(dwarfTexture, 365, 280);
        //Draw menu
        for (int i = 0; i < menuItems.length; i++) {
            if (currentItem ==  i)
                font.setColor(Color.BLACK);
            else
                font.setColor(Color.WHITE);

            font.draw(sb, menuItems[i], 256, 280 - 20 * i);
        }
        sb.end();


    }

    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if (currentItem > 0) {
                currentItem--;
                return;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            if (currentItem < menuItems.length - 1) {
                currentItem++;
                return;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            switch(currentItem) {
                case 0:
                    gsm.setState(GameStateManager.LEVELONE);
                    break;
                case 1:
                    timerMode = true;
                    gsm.setState(GameStateManager.LEVELONE);
                    break;
                case 2:
                    gsm.setState(GameStateManager.LEVELSELECT);
                    break;
                case 3:
                    gsm.setState(GameStateManager.INSTRUCTIONS);
                    break;
            }

        }
    }

    @Override
    public void dispose() {

    }

    public void restart() {
    }

}