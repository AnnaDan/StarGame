package ru.geekbrains.stargame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class StarGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture img;
	TextureRegion region;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		background = new Texture("bg.png");
		region = new TextureRegion(img, 20, 20, 100, 100);

		Vector2 v1 = new Vector2(1, 3);
		Vector2 v2 = new Vector2(0, -1);
		// v1(x1, y1) + v2(x2, y2) = v3(x1 + x2, y1 + y2)
		Vector2 v3 = v1.cpy().add(v2);
		System.out.println("Сложение v3 = " + v3.x + " " + v3.y);

		v1 = new Vector2(4, 3);
		v2 = new Vector2(1, 2);
		// v1(x1, y1) - v2(x2, y2) = v3(x1 - x2, y1 - y2)
		v3 = v1.cpy().sub(v2);
		System.out.println("Вычитание v3 = " + v3.x + " " + v3.y);

		v1 = new Vector2(7, 5);
		// n * v1(x, y) = v2(n*x, n*y)
		v1.scl(0.9f);
		System.out.println("Умножение на скаляр v1 = " + v1.x + " " + v1.y);

		v1 = new Vector2(2, 8);
		// |v1| = sqrt(x^2 + y^2)
		System.out.println("Длина v1 = " + v1.len());

		System.out.println("Нормализованны v1 = " + v1.nor());

		// v1(x1, y1) * v2(x2, y2) = x1*x2 + y1*y2
		v1 = new Vector2(1, 1);
		v2 = new Vector2(-1, 1);
		v1.nor();
		v2.nor();
		System.out.println(Math.acos(v1.dot(v2)));


	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.7f, 0.3f, 0.7f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(background, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
