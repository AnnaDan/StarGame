package ru.geekbrains.stargame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.geekbrains.stargame.engine.Base2DScreen;

import com.badlogic.gdx.math.Vector2;

public class MenuScreen extends Base2DScreen {

    private SpriteBatch batch;
    private Texture background;
    private Texture logotip;
    private int logotipX, logotipY;
    private int logotipX_old, logotipY_old;
    float logotip_mlt_X, logotip_mlt_Y;

    private Vector2 v1, v2, v3;

    public MenuScreen(Game game) {
        super(game);
    }

    private void logoMove() {
        // System.out.println("logotipX " + logotipX + " logotipX " + logotipY + "logotipX_old " + logotipX_old + " logotipY_old " + logotipY_old);
        v1 = new Vector2(logotipX, logotipY);
        v2 = new Vector2(logotipX_old, logotipY_old);
        v3 = v1.cpy().sub(v2);
        v3.scl(0.1f);
        //     System.out.println(v3.x + " " + v3.y);
        logotip_mlt_X = v3.x;
        logotip_mlt_Y = v3.y;
        //    System.out.println("logotip_mlt_X " + logotip_mlt_X + " logotip_mlt_Y " + logotip_mlt_Y + " " + logotipX + " " + logotipY + " " + logotipX_old + " " + logotipY);
    }

    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        background = new Texture("sky.jpg");
        logotip = new Texture("badlogic.jpg");
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        logotipX = screenX;
        logotipY = Gdx.graphics.getHeight() - screenY;
        logoMove();
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        logotipX = screenX;
        logotipY = Gdx.graphics.getHeight() - screenY;
        logoMove();
        return super.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        logotipX = screenX;
        logotipY = Gdx.graphics.getHeight() - screenY;
        logoMove();
        return super.touchDragged(screenX, screenY, pointer);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);

        if (logotipX_old != logotipX) {
            //   System.out.println("Math.round(logotip_mlt_X) "  + (int)(logotip_mlt_X));
            if ((int) (logotip_mlt_X) == 0) logotip_mlt_X = Math.signum(logotip_mlt_X);
            logotipX_old = (int) (logotipX_old + logotip_mlt_X);
        }

        if (logotipY_old != logotipY) {
            //  System.out.println("Math.round(logotip_mlt_Y) " + (int)(logotip_mlt_Y));
            if ((int) (logotip_mlt_Y) == 0) logotip_mlt_Y = Math.signum(logotip_mlt_Y);
            logotipY_old = (int) (logotipY_old + logotip_mlt_Y);
        }

        batch.draw(logotip, logotipX_old, logotipY_old);
        logoMove();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        background.dispose();
    }
}
