package com.mygdx.game.gamestates;

import com.mygdx.game.managers.GameStateManager;

/**
 * Created by englund on 03/10/15.
 */
public class LevelOneState extends PlayState{


    protected GameStateManager gsm;

    public LevelOneState(GameStateManager gsm) {
        super(gsm);
    }

    public void init() {
        String levelName= "assets/levelOne.tmx";

    }

}
