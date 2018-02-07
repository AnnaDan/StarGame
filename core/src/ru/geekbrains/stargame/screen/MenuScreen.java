package ru.geekbrains.stargame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.engine.Base2DScreen;
import ru.geekbrains.stargame.engine.Sprite;
import ru.geekbrains.stargame.engine.math.Rect;

public class MenuScreen extends Base2DScreen {

    private Texture backgroundTexture;
    private Background background;
    private Texture exitTexture;
    private Background exit;
    private Texture playTexture;
    private Background play;

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        backgroundTexture = new Texture("sky.jpg");
        background = new Background(new TextureRegion(backgroundTexture));
        exitTexture = new Texture("close.png");
        exit = new Background(new TextureRegion(backgroundTexture));
        playTexture = new Texture("play_2.png");
        play = new Background(new TextureRegion(backgroundTexture));
        play.setHeightProportion(0.5f);
        play.setScale(0.1f);
         }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.7f, 0.3f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(backgroundTexture, -1f, -1f, 2f, 2f);
       // batch.draw(exitTexture, -0.3f, 0f,0.3f,0.3f );
       // batch.draw(playTexture, 0f, 0f, 0.3f, 0.3f );

        batch.end();
    }

    @Override
    protected void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        exit.resize(worldBounds);
        play.resize(worldBounds);
    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        exitTexture.dispose();
        playTexture.dispose();
        super.dispose();
    }

    @Override
    protected void touchUp(Vector2 touch, int pointer) {
        super.touchUp(touch, pointer);
    }
}
