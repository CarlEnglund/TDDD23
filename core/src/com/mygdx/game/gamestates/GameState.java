package com.mygdx.game.gamestates;

import com.mygdx.game.managers.GameStateManager;

/**
 * Created by englund on 10/09/15.
 */
public abstract class GameState {

    protected GameStateManager gsm;

    protected GameState(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public abstract void init();
    public abstract void update(float dt);
    public abstract void draw();
    public abstract void handleInput();
    public abstract void dispose();
}
