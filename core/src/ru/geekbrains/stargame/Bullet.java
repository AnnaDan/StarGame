package ru.geekbrains.stargame;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.engine.Sprite;
import ru.geekbrains.stargame.engine.math.Rect;

/**
 * Created by annav on 09.02.2018.
 */

public class Bullet extends Sprite {

    private final Vector2 v = new Vector2(0.0f, 0.5f);
    private Rect worldBounds;


    public Bullet(TextureAtlas atlas, float x, float y) {
        super(atlas.findRegion("bulletMainShip"), 1, 1, 1);
        setHeightProportion(0.01f);
        pos.set(x, y);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        /*
        if (getTop() > worldBounds.getTop()) {
            setDestroyed(true);
        }*/
    }

    /*
    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
    }
    */
}
