package com.mygdx.game.managers;

import com.mygdx.game.gamestates.*;

/**
 * Created by englund on 10/09/15.
 */
public class GameStateManager {

    private GameState gameState;

    public static final int MENU = 0;
    public static final int LEVELONE = 1;
    public static final int LEVELTWO = 2;
    public static final int LEVELTHREE = 3;
    public static final int LEVELSELECT = 4;

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
        if(state == LEVELTHREE) {
            gameState = new LevelThreeState(this);
        }
        if(state == LEVELSELECT) {
            gameState = new SelectLevelState(this);
        }
    }

    public void update(float dt) {
        gameState.update(dt);
    }

    public void draw() {
        gameState.draw();
    }

}
