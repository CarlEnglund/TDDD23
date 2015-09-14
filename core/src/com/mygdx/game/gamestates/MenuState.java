package com.mygdx.game.gamestates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.mygdx.game.managers.GameStateManager;

/**
 * Created by englund on 10/09/15.
 */
public class MenuState extends GameState {

    private SpriteBatch sb;

    private BitmapFont titleFont;
    private BitmapFont font;

    private final String title = "Låd-Leif";

    private int currentItem;
    private String[] menuItems;


    public MenuState(GameStateManager gsm) {
        super(gsm);

    }

    @Override
    public void init() {
        sb = new SpriteBatch();

        menuItems = new String[] {
                "Play from beginning",
                "Levels",
                "Quit"
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


        sb.begin();
            //Draw title
            titleFont.draw(sb, title, 200, 300);

            //Draw menu
        for(int i = 0; i < menuItems.length; i++) {
            if(currentItem == i)
                font.setColor(Color.GREEN);
            else
                font.setColor(Color.WHITE);

            font.draw(sb, menuItems[i], 200, 280 - 20*i);
        }
        sb.end();


    }

    @Override
    public void handleInput() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            if(currentItem > 0) {
                currentItem--;
                return;
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            if(currentItem < menuItems.length-1) {
                currentItem++;
                return;
            }
        }
    }

    @Override
    public void dispose() {

    }
}
