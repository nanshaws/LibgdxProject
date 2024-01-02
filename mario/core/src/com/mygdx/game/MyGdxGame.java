package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.utils.MapUtil;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	TextureRegion img;
	TiledMap map;
    World world;
	MapUtil util;
	OrthographicCamera camera;
	OrthogonalTiledMapRenderer ren;
	Box2DDebugRenderer ren2;
	BodyDef bodyDef;
	@Override
	public void create () {
		batch = new SpriteBatch();
		img=new TextureRegion(new Texture(Gdx.files.internal("t1.png")));
		util=new MapUtil();
		world=new World(new Vector2(0,-9.8f),true);
		camera=new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		camera.setToOrtho(false,480,320);
		map=util.getTmxMapLoader();
		ren=new OrthogonalTiledMapRenderer(map);
		ren2=new Box2DDebugRenderer();
		FixtureDef fixtureDef=new FixtureDef();
		bodyDef=new BodyDef();
		util.createStatic(map,world,fixtureDef,bodyDef);

        util.createDystatic(bodyDef,fixtureDef,world);
	}



	@Override
	public void render () {
		util.setColor(Color.BLACK);
		Color color = util.getColor();
		Gdx.gl.glClearColor(color.r,color.g,color.b,color.a);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		ren.render();
		ren.setView(camera);
		ren2.render(world,camera.combined);
		batch.begin();
		util.handleInput(batch,world);
		util.draw(batch,map);
		batch.end();
		world.step(1 / 60f, 6, 2);
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
