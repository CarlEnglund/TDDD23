package com.mygdx.game.managers;

import com.mygdx.game.gamestates.GameState;
import com.mygdx.game.gamestates.LevelOneState;
import com.mygdx.game.gamestates.MenuState;
import com.mygdx.game.gamestates.LevelTwoState;

/**
 * Created by englund on 10/09/15.
 */
public class GameStateManager {

    private GameState gameState;

    public static final int MENU = 0;
    public static final int LEVELONE = 1;
    public static final int LEVELTWO = 2;

    public GameStateManager() {

    }

    public void setState(int state) {
        if(gameState != null)
            gameState.dispose();

        if(state == MENU) {
            gameState = new MenuState(this);
        }

        if(state == LEVELONE) {
            gameState = new LevelOneState(this);
        }

        if(state == LEVELTWO) {
            gameState = new LevelTwoState(this);
        }
    }

    public void update(float dt) {
        gameState.update(dt);
    }

    public void draw() {
        gameState.draw();
    }

}
