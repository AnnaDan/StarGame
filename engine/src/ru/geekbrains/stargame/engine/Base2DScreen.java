package ru.geekbrains.stargame.engine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

/**
 * Created by annav on 27.01.2018.
 */

public class Base2DScreen implements Screen, InputProcessor {

    protected Game game;

    public Base2DScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        System.out.println("Show");
        Gdx.input.setInputProcessor(this); // отловлео в Скине
    }

    @Override
    public void render(float delta) {// 60 раз в секунду можно отрегулировать это delta

    }

    @Override
    public void resize(int width, int height) {
        System.out.println("resize width = " + width + " height = " + height);
    }

    @Override
    public void pause() {
        System.out.println("Pause");

    }

    @Override
    public void resume() {
        System.out.println("Resume");

    }

    @Override
    public void hide() {
        System.out.println("Hide");
    }

    @Override
    public void dispose() {
        System.out.println("Dispose");
    }

    @Override
    public boolean keyDown(int keycode) {
        System.out.println("KeyDown keyCode = " + keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        System.out.println("KeyUP keyCode = " + keycode);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        System.out.println("KeyTyped char = " + character);
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {// Кроспалтформенные
        System.out.println("touchDown screenX = " + screenX +" screenY = " + ( Gdx.graphics.getHeight() - screenY )+" pointer = " + pointer);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {// Кроспалтформенные
        System.out.println("touchUP screenX = " + screenX +" screenY = " + screenY +" pointer = " + pointer);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {// Кроспалтформенные
        System.out.println("touchDragged screenX = " + screenX +" screenY = " + screenY +" pointer = " + pointer);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
