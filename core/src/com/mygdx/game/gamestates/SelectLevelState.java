package com.mygdx.game.gamestates;

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
import com.badlogic.gdx.utils.Select;
import com.mygdx.game.managers.GameStateManager;

/**
 * Created by englund on 13/10/15.
 */
public class SelectLevelState extends GameState {

    private SpriteBatch sb;
    private String[] levelItems;
    private BitmapFont titleFont;
    private BitmapFont font;
    private int currentItem;
    OrthographicCamera camera;
    TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;
    private Texture dwarfTexture;


    private final String title = "Select Level";

    public SelectLevelState(GameStateManager gsm) {super(gsm);}

    public void init() {
        sb = new SpriteBatch();
        dwarfTexture = new Texture("assets/dwarf.png");
        tiledMap = new TmxMapLoader().load("assets/menumap.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 512, 512);
        camera.update();

        levelItems = new String[] {
                "Level 1",
                "Level 2",
                "Level 3"
        };

        titleFont = new BitmapFont();
        font = new BitmapFont();
        titleFont.setColor(Color.BLACK);
    }

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
        for(int i = 0; i < levelItems.length; i++) {
            if(currentItem == i)
                font.setColor(Color.BLACK);
            else
                font.setColor(Color.WHITE);

            font.draw(sb, levelItems[i], 256, 280 - 20*i);
        }
        sb.end();


    }

    public void handleInput() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            if(currentItem > 0) {
                currentItem--;
                return;
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            if(currentItem < levelItems.length-1) {
                currentItem++;
                return;
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            switch(currentItem) {
                case 0:
                    gsm.setState(GameStateManager.LEVELONE);
                    break;
                case 1:
                    gsm.setState(GameStateManager.LEVELTWO);
                    break;
                case 2:
                    gsm.setState(GameStateManager.LEVELTHREE);
                    break;
                default:
                    break;
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            gsm.setState(GameStateManager.MENU);
        }
    }

    public void dispose() {}
    public void restart() {};
    public void timerMode() {};
}
