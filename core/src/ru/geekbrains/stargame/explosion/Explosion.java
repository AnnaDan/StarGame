package ru.geekbrains.stargame.explosion;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.engine.Sprite;

public class Explosion extends Sprite {

    private Sound sound;
    private float animateInterval = 0.017f; //время между кадрами анимации
    private float animateTimer;

    public Explosion(TextureRegion region, int rows, int cols, int frames, Sound sound) {
        super(region, rows, cols, frames);
        this.sound = sound;
    }

    public void set(float height, Vector2 pos) {
        this.pos.set(pos);
        setHeightProportion(height);
        sound.play();
    }

    @Override
    public void update(float delta) {
        animateTimer += delta;
        if (animateTimer >= animateInterval) {
            animateTimer = 0f;
            if (++frame==regions.length) {
                destroy();
            }
        }
    }

    public void destroy() {
        setDestroyed(true);
        frame = 0;
    }
}
