package ru.geekbrains.stargame.screen;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import ru.geekbrains.stargame.Background;
import ru.geekbrains.stargame.bullet.Bullet;
import ru.geekbrains.stargame.bullet.BulletPool;
import ru.geekbrains.stargame.engine.Base2DScreen;
import ru.geekbrains.stargame.engine.math.Rect;
import ru.geekbrains.stargame.engine.math.Rnd;
import ru.geekbrains.stargame.explosion.Explosion;
import ru.geekbrains.stargame.explosion.ExplosionPool;
import ru.geekbrains.stargame.ship.EnemyEmmiter;
import ru.geekbrains.stargame.ship.EnemyPool;
import ru.geekbrains.stargame.ship.EnemyShip;
import ru.geekbrains.stargame.ship.MainShip;
import ru.geekbrains.stargame.star.TrackingStar;

public class GameScreen extends Base2DScreen {

    private static final int STAR_COUNT = 56;
    private static final float STAR_HEIGHT = 0.01f;

    private Texture backgroundTexture;
    private Background background;

    private TextureAtlas atlas;

    private MainShip mainShip;

    private TrackingStar star[];

    private final BulletPool bulletPool = new BulletPool();
    private ExplosionPool explosionPool;
    private EnemyPool enemyPool;

    private EnemyEmmiter enemyEmmiter;

    private Sound soundExplosion;
    private Sound soundLaser;
    private Sound soundBullet;
    private Music music;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        music  = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
        music.setLooping(true);
        music.play();
        soundExplosion = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));
        soundLaser = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));
        soundBullet = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));

        backgroundTexture = new Texture("textures/sky.jpg");
        background = new Background(new TextureRegion(backgroundTexture));

        atlas = new TextureAtlas("textures/mainAtlas.tpack");

        this.explosionPool = new ExplosionPool(atlas, soundExplosion);
        mainShip = new MainShip(atlas, bulletPool, explosionPool, worldBounds, soundLaser);

        this.enemyPool = new EnemyPool(bulletPool, explosionPool, worldBounds, mainShip, soundBullet);
        this.enemyEmmiter = new EnemyEmmiter(enemyPool, worldBounds, atlas);

        star = new TrackingStar[STAR_COUNT];
        for (int i = 0; i < star.length; i++) {
            star[i] = new TrackingStar(atlas, Rnd.nextFloat(-0.005f, 0.005f), Rnd.nextFloat(-0.5f, -0.1f), STAR_HEIGHT, mainShip.getV());
        }

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        checkCollisions();
        deleteAllDestroyed();
        update(delta);
        draw();
    }

    public void checkCollisions() {

        // столкновение кораблей
        List<EnemyShip> enemyShipList = enemyPool.getActiveObjects();
        for (EnemyShip enemy : enemyShipList) {
            if (enemy.isDestroyed()) {
                continue;
            }
            float minDist = enemy.getHalfWidth() + mainShip.getHalfWidth();
            if (enemy.pos.dst2(mainShip.pos) < minDist * minDist) {
                enemy.setDestroyed(true);
                enemy.boom();
                mainShip.damage(enemy.getBulletDamage());
                return;
            }
        }

        // нанесение урона вражескому кораблю
        List<Bullet> bulletList = bulletPool.getActiveObjects();
        for (EnemyShip enemy : enemyShipList) {
            if (enemy.isDestroyed()) {
                continue;
            }
            for (Bullet bullet : bulletList) {
                if (bullet.getOwner() != mainShip || bullet.isDestroyed()) {
                    continue;
                }
                if (enemy.isBulletCollision(bullet)) {
                    enemy.damage(bullet.getDamage());
                 //   System.out.println("EnemyShip Before " + enemy.getHp() );
                    enemy.setHp(mainShip.getBulletDamage());
                 //   System.out.println("EnemyShip After " + enemy.getHp() );
                    if  (enemy.isDestroyed()){
                    enemy.boom();}
                    bullet.setDestroyed(true);
                }
            }
        }

      // Нанесение урона кораблю
        if (!mainShip.isDestroyed()){
              for (EnemyShip enemy : enemyShipList) {
                if (enemy.isDestroyed()) {
                    continue;
                }
                for (Bullet bullet : bulletList) {
                    if (bullet.getOwner() != enemy || bullet.isDestroyed()) {
                        continue;
                    }
                    if (mainShip.isBulletCollision(bullet)) {
                        mainShip.damage(bullet.getDamage());
                      //  System.out.println("MainShip Before " + mainShip.getHp() );
                        mainShip.setHp( enemy.getBulletDamage());
                      //  System.out.println("MainShip After " + mainShip.getHp() );
                        if  (mainShip.isDestroyed()){
                            mainShip.boom();}
                        System.out.println();
                        bullet.setDestroyed(true);
                    }
                }
            }
        }
    }

    public void deleteAllDestroyed() {
        bulletPool.freeAllDestroyedActiveObjects();
        explosionPool.freeAllDestroyedActiveObjects();
        enemyPool.freeAllDestroyedActiveObjects();
    }

    public void update(float delta) {
        for (int i = 0; i < star.length; i++) {
            star[i].update(delta);
        }
        bulletPool.updateActiveObjects(delta);
        explosionPool.updateActiveObjects(delta);
        enemyPool.updateActiveObjects(delta);
        mainShip.update(delta);
        enemyEmmiter.generateEnemy(delta);
    }

    public void draw() {
        Gdx.gl.glClearColor(0.7f, 0.3f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (int i = 0; i < star.length; i++) {
            star[i].draw(batch);
        }
        mainShip.draw(batch);
        bulletPool.drawActiveObjects(batch);
        explosionPool.drawActiveObjects(batch);
        enemyPool.drawActiveObjects(batch);
        batch.end();
    }

    @Override
    protected void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (int i = 0; i < star.length; i++) {
            star[i].resize(worldBounds);
        }
        mainShip.resize(worldBounds);
    }

    @Override
    public void dispose() {
        super.dispose();
        backgroundTexture.dispose();
        atlas.dispose();
        bulletPool.dispose();
        soundLaser.dispose();
        soundBullet.dispose();
        explosionPool.dispose();
        enemyPool.dispose();
        soundExplosion.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        mainShip.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        mainShip.keyUp(keycode);
        return false;
    }

    @Override
    protected void touchDown(Vector2 touch, int pointer) {
        mainShip.touchDown(touch, pointer);
    }

    @Override
    protected void touchUp(Vector2 touch, int pointer) {
        mainShip.touchUp(touch, pointer);
    }
}
