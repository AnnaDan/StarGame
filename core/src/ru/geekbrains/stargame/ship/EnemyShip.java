package ru.geekbrains.stargame.ship;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.sun.org.apache.regexp.internal.RE;

import ru.geekbrains.stargame.bullet.BulletPool;
import ru.geekbrains.stargame.engine.math.Rect;
import ru.geekbrains.stargame.engine.math.Rnd;

public class EnemyShip extends Ship {

    private static final float SHIP_HEIGHT = 0.15f;
    private static final float BOTTOM_MARGIN = 0.05f;
    private static final int INVALID_POINTER = -1;

    private final Vector2 v0 = new Vector2(0.5f, 0.0f);


    private boolean pressedLeft;
    private boolean pressedRight;
    private Sound sound;
    private Rect worldBounds;

    private int leftPointer = INVALID_POINTER;
    private int rightPointer = INVALID_POINTER;


    public EnemyShip(TextureAtlas atlas, BulletPool bulletPool, Sound sound,Rect worldBounds) {
        super(atlas.findRegion("enemy2"), 1, 2, 2);
        setHeightProportion(SHIP_HEIGHT);
        this.bulletPool = bulletPool;
        this.bulletRegion = atlas.findRegion("bulletMainShip");//("bulletEnemy");
        this.bulletHeight = 0.01f;
        this.bulletV.set(0, 0.5f);
        this.bulletDamage = 1;
        this.reloadInterval = 0.2f;
        this.sound = sound;
        v.set(0, -0.5f);
        this.worldBounds = worldBounds;
       pos.set(Rnd.nextFloat(worldBounds.getLeft(),
               worldBounds.getRight()), worldBounds.getTop());
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        reloadTimer += delta;
        shoot();
        /*
        if (reloadTimer >= reloadInterval) {
            reloadTimer = 0f;
            shoot();
            sound.play();
        }
        /*
        /*
        if (getRight() > worldBounds.getRight()) {
            setRight(worldBounds.getRight());
            stop();
        }
        if (getLeft() < worldBounds.getLeft()) {
            setLeft(worldBounds.getLeft());
            stop();
        }
        */
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
       // setBottom(worldBounds.getBottom() + BOTTOM_MARGIN);
    }

    private void stop() {
        v.setZero();
    }

    public Vector2 getV() {
        return v;
    }
}
