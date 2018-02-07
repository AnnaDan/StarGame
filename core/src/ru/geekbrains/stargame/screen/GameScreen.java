package ru.geekbrains.stargame.screen;

/**
 * Created by annav on 07.02.2018.
 */


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrains.stargame.Background;
import ru.geekbrains.stargame.engine.Base2DScreen;
import ru.geekbrains.stargame.engine.math.Rect;
import ru.geekbrains.stargame.engine.math.Rnd;
import ru.geekbrains.stargame.star.Star;

public class GameScreen extends Base2DScreen {

    private Texture backgroundTexture;
    private Background background;

    private static final int STARS_CNT = 10;
    private Star stars[];
    private TextureAtlas atlas;



    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        atlas = new TextureAtlas("menuAtlas.tpack");
        backgroundTexture = new Texture("sky.jpg");
        background = new Background(new TextureRegion(backgroundTexture));
        stars = new Star[STARS_CNT];
        for (int i = 0; i < STARS_CNT ; i++) {
            stars[i] = new Star(atlas, Rnd.nextFloat(-0.005f, 0.005f), Rnd.nextFloat(-0.5f, -0.1f), 0.01f);
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.7f, 0.3f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);

        for (int i = 0; i < stars.length ; i++) {
            stars[i].update(delta);
            stars[i].draw(batch);
        }

        batch.end();
    }

    @Override
    protected void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (int i = 0; i <stars.length ; i++) {
            stars[i].resize(worldBounds);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        backgroundTexture.dispose();
        atlas.dispose();
    }
}
