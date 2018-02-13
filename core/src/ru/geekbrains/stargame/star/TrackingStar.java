package ru.geekbrains.stargame.star;


import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;


public class TrackingStar extends Star {

    private final Vector2 trackingV;

    private final Vector2 sumV = new Vector2();

    public TrackingStar(TextureAtlas atlas, float vx, float vy, float height, Vector2 trackingV) {
        super(atlas, vx, vy, height);
        this.trackingV = trackingV;
    }

    @Override
    public void update(float delta) {
        sumV.setZero().mulAdd(trackingV, 0.2f).rotate(180).add(v);
        pos.mulAdd(sumV, delta);
        checkAndHandleBounds();
    }
}
