package ru.geekbrains.stargame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.stargame.engine.Sprite;
import ru.geekbrains.stargame.engine.pool.SpritesPool;
import ru.geekbrains.stargame.ship.MainShip;

/**
 * Created by annav on 09.02.2018.
 */

public class BulletPool extends SpritesPool {
    protected TextureAtlas atlas;
    protected MainShip mainShip;

    public BulletPool(TextureAtlas atlas, MainShip mainShip) {
        this.atlas = atlas;
        this.mainShip = mainShip;
    }

    @Override
    protected Sprite newObject() {
        Bullet bullet = new Bullet(atlas, mainShip.getLeft()+ mainShip.getHalfWidth(), mainShip.getTop());
        return bullet;
    }

}
