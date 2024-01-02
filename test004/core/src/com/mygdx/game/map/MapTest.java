package com.mygdx.game.map;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

public class MapTest extends ApplicationAdapter {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private TiledMap map;
    private OrthogonalTiledMapRenderer ren;
    private float size=50f;

    @Override
    public void create() {
        batch=new SpriteBatch();
        map=new TmxMapLoader().load("map/map01.tmx");
        ren=new OrthogonalTiledMapRenderer(map,4);
        camera=new OrthographicCamera(Gdx.graphics.getWidth()*5/size,Gdx.graphics.getHeight()*5/size);
        camera.position.x=Gdx.graphics.getWidth()/2/size;
        camera.position.y=Gdx.graphics.getHeight()/2/size;
        camera.update();
        BodyDef bodyDef=new BodyDef();
        bodyDef.type=BodyDef.BodyType.StaticBody;
        FixtureDef fixtureDef=new FixtureDef();
        MapLayers layers=map.getLayers();
        MapLayer ground = layers.get("ground");
        MapObjects objects=ground.getObjects();
        Array<RectangleMapObject> rmos=objects.getByType(RectangleMapObject.class);
        for (RectangleMapObject rmo:rmos){
            Rectangle rectangle=rmo.getRectangle();
            PolygonShape shape=new PolygonShape();
            shape.setAsBox(rectangle.getWidth()/2/size,rectangle.getHeight()/2/size);
            fixtureDef.shape=shape;
            bodyDef.position.set((rectangle.getX()+rectangle.getWidth()/2)/size,(rectangle.getY()+rectangle.getHeight())/2/size);
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ren.render();
        batch.begin();
        batch.end();
        ren.setView(camera);
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
