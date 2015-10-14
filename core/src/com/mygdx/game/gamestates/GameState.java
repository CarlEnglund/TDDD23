package com.mygdx.game.gamestates;

import com.badlogic.gdx.utils.Timer;
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
    public abstract void dispose();
    public abstract void restart();
    public void timerMode(int seconds) {
        Timer clock = new Timer();
        clock.scheduleTask(new Timer.Task() {

            public void run() {
                restart();
            }
        }, seconds);

        };
    };

