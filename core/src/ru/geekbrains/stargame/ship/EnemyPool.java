package ru.geekbrains.stargame.ship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.stargame.bullet.BulletPool;
import ru.geekbrains.stargame.engine.math.Rect;
import ru.geekbrains.stargame.engine.pool.SpritesPool;

public class EnemyPool extends SpritesPool<EnemyShip> {

    private Sound sound;
    TextureAtlas atlas;
    BulletPool bulletPool;
    protected Rect worldBounds; // границы мира

    public void setWorldBounds(Rect worldBounds) {
        this.worldBounds = worldBounds;
    }

    public EnemyPool() {
        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));
        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        bulletPool = new BulletPool();
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(atlas, bulletPool, sound,worldBounds);
    }


/*
    @Override
    protected void debugLog() {
        System.out.println("BulletPool change active/free:" + activeObjects.size() + "/" + freeObjects.size());
    }
*/
}
