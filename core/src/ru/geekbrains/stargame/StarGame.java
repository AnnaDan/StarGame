package ru.geekbrains.stargame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class StarGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img;
	private TextureRegion region;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("sky.jpg");
		region = new TextureRegion(img,0,0,1024,1024);

		Vector2 v1 = new Vector2(1,3);
		Vector2 v2 = new Vector2(0,-1);
		Vector2 v3 = v1.cpy().add(v2); // сложение
		// 1,2
		System.out.println("Сложение v3 = " + v3.x +" " + v3.y);
		// 3,1
		v1 = new Vector2(4,3);
		v2 = new Vector2(1,2);
		v3 = v1.cpy().sub(1,2); // вычитание
		System.out.println("Вычитание v3 = " + v3.x +" " + v3.y);

	    v1 = new Vector2(31, 5);
	    // Каждая координата умножается на скаляр
	    v1.scl(0.9f);
		System.out.println("Умножение на скаляр v1 " + v1.x +" "+ v1.y);
		// |v1| = sqrt(x^ = Y^2)
        v1 = new Vector2(2,8);
		System.out.println("Длина v1 = " + v1.len() );
		// |v3| = |v1 - v2|
		// Нормализация это единичный вектор длина которого равна 1
		System.out.println("Нормализованный V1 " + v1.nor());
       // Скалярное произведение v1(x1, y1 ) * v2(x2,y2) = x1*x2 + y1*y2 Нужно для определния того как смотрят друг на друга единичные вектора
		v1 = new Vector2(1,1);
		v2 = new Vector2(-1,1);
		v1.nor();
		v2.nor();
		System.out.println("Угол в радианах" + Math.acos(v1.dot(v2)));

	}

	@Override
	public void render () { // Работает 60 раз в секунду
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//batch.draw(img, 0, 0);
		batch.draw(region,0,0,2000,2000);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
