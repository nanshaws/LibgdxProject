package com.mygdx.game.screen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Collision001Example extends ApplicationAdapter {
    private OrthographicCamera camera;
    private World  world;
    private Body body;
    private Body body1;
    private SpriteBatch batch;
    private Box2DDebugRenderer debugRenderer;
    private TiledMap map;
    private OrthogonalTiledMapRenderer ren;
    private float size=50f;
    @Override
    public void create() {
        batch=new SpriteBatch();
        camera=new OrthographicCamera(Gdx.graphics.getWidth()/size,Gdx.graphics.getHeight()/size);
        camera.setToOrtho(false, Gdx.graphics.getWidth()/size, Gdx.graphics.getHeight()/size);
        debugRenderer=new Box2DDebugRenderer();
        world=new World(new Vector2(0f,-9.8f),true);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type=BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0,0);
        FixtureDef fixtureDef=new FixtureDef();
        PolygonShape shape=new PolygonShape();
        shape.setAsBox(50/size,50/size);
        fixtureDef.shape=shape;
        body=world.createBody(bodyDef);
        body.createFixture(fixtureDef);

        bodyDef.type=BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0,300/size);
        fixtureDef.restitution=1f;
        world.createBody(bodyDef).createFixture(fixtureDef);

        map=new TmxMapLoader().load("map/map01.tmx");
        ren=new OrthogonalTiledMapRenderer(map,4);

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.step(1 / 60f, 6, 2);
        debugRenderer.render(world,camera.combined);
        ren.render();
        batch.begin();
        batch.end();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
