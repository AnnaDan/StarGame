package ru.geekbrains.stargame;

import com.badlogic.gdx.Game;

/**
 * Created by annav on 27.01.2018.
 */

public class Star2DGame extends Game {
    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }
}
