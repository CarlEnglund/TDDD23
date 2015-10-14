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
public class InstructionsState extends GameState {

    private SpriteBatch sb;
    private String[] levelItems;
    private BitmapFont titleFont;
    private BitmapFont font;
    private int currentItem;
    OrthographicCamera camera;
    TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;
    private Texture dwarfTexture;


    private final String title = "Instructions";
    private String INSTRUCTIONS;

    public InstructionsState(GameStateManager gsm) {super(gsm);}

    public void init() {
        sb = new SpriteBatch();
        dwarfTexture = new Texture("assets/dwarf.png");
        tiledMap = new TmxMapLoader().load("assets/menumap.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 512, 512);
        camera.update();

        INSTRUCTIONS =  "Lorem ipsum dolor sit amet, consectetur adipiscing elit. \nCras sollicitudin placerat tellus sed " +
                "convallis. Vestibulum\n sit amet pharetra elit, id congue lorem. Sed \nsodales a sapien eget lobortis. Duis ultrices" +
                " pharetra sodales. Morbi porta lobortis justo. Integer\n ultrices dolor \negestas urna congue dapibus. Nullam quis urna" +
                " bibendum elit tristique molestie.\n Fusce eros nibh, mattis quis  \nsollicitudin dapibus, hendrerit ac sem. Vestibulum";

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


            font.draw(sb, INSTRUCTIONS, 150, 280);

        sb.end();


    }

    public void handleInput() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            gsm.setState(GameStateManager.MENU);
        }
    }

    public void dispose() {}
    public void restart() {};
    public void timerMode() {};
}
