package com.mygdx.game.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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


    private final String title = "Select Level";

    public SelectLevelState(GameStateManager gsm) {super(gsm);}

    public void init() {
        sb = new SpriteBatch();

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
        sb.begin();

        //Draw title
        titleFont.draw(sb, title, 200, 300);

        //Draw menu
        for(int i = 0; i < levelItems.length; i++) {
            if(currentItem == i)
                font.setColor(Color.GREEN);
            else
                font.setColor(Color.WHITE);

            font.draw(sb, levelItems[i], 200, 280 - 20*i);
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
    }

    public void dispose() {}
}
