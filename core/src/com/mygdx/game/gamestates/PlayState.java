package com.mygdx.game.gamestates;

import com.mygdx.game.managers.GameStateManager;

/**
 * Created by englund on 10/09/15.
 */
public class PlayState extends GameState {
    public PlayState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {

    }

    @Override
    public void update(float dt) {
        System.out.println("PLAY STATE UPDATING");

    }

    @Override
    public void draw() {
        System.out.println("PLAY STATE DRAWING");
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void dispose() {

    }
}

