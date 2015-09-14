package com.mygdx.game.managers;

import com.mygdx.game.gamestates.GameState;
import com.mygdx.game.gamestates.MenuState;
import com.mygdx.game.gamestates.PlayState;

/**
 * Created by englund on 10/09/15.
 */
public class GameStateManager {

    private GameState gameState;

    public static final int MENU = 0;
    public static final int PLAY = 1;

    public GameStateManager() {
        setState(PLAY);
    }

    public void setState(int state) {
        if(gameState != null)
            gameState.dispose();

        if(state == MENU) {
            //switch to menu state
            gameState = new MenuState(this);
        }

        if(state == PLAY) {
            //switch to play state
            gameState = new PlayState(this);
        }
    }

    public void update(float dt) {
        gameState.update(dt);
    }

    public void draw() {
        gameState.draw();
    }

}
