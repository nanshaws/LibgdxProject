package com.mygdx.game.map;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

public class MapTest001 extends ApplicationAdapter {
    OrthographicCamera camera;
    TiledMap map;
    OrthogonalTiledMapRenderer ren;
    World world;
    Box2DDebugRenderer ren2;
    Texture t;
    SpriteBatch batch;
    float x=0,y=0;
    Body body;
    @Override
    public void create() {
        world=new World(new Vector2(0,-9.8f),true);
        ren2=new Box2DDebugRenderer();
        map=new TmxMapLoader().load("map/mario/map03.tmx");
        batch=new SpriteBatch();
        ren=new OrthogonalTiledMapRenderer(map,4);
        camera=new OrthographicCamera(Gdx.graphics.getWidth()*2,Gdx.graphics.getHeight()*2);
        camera.position.x=Gdx.graphics.getWidth();
        camera.position.y=Gdx.graphics.getHeight();
        camera.update();


        BodyDef bodyDef=new BodyDef();
        bodyDef.type=BodyDef.BodyType.StaticBody;
        FixtureDef fixtureDef=new FixtureDef();


        TiledMapTileLayer layer1=(TiledMapTileLayer) map.getLayers().get("图块层 1");

        MapLayer layers = map.getLayers().get("对象层 1");
        MapObjects objects=layers.getObjects();
        Array<RectangleMapObject> rmos=objects.getByType(RectangleMapObject.class);
        for (RectangleMapObject rmo:rmos){
            Rectangle rect=rmo.getRectangle();
            PolygonShape shape=new PolygonShape();
            shape.setAsBox(rect.getWidth()/2,rect.getHeight()/2);
            fixtureDef.shape=shape;
            bodyDef.position.set((rect.getX()+rect.getWidth()/2),(rect.getY()+rect.getHeight()/2));
            world.createBody(bodyDef).createFixture(fixtureDef);
        }

//        bodyDef.type=BodyDef.BodyType.DynamicBody;
//        bodyDef.position.set(Gdx.graphics.getWidth()+40,Gdx.graphics.getHeight());
//        CircleShape cshape=new CircleShape();
//        cshape.setRadius(8);
//        fixtureDef.shape=cshape;
//        fixtureDef.restitution=1;
//        body=world.createBody(bodyDef);
//        body.createFixture(fixtureDef);


        TiledMapTileLayer layer2=(TiledMapTileLayer) map.getLayers().get("图块层 2");
        for (int i=0;i<layer1.getWidth();i++){
            for (int j=0;j<layer1.getHeight();j++){
                if (layer1.getCell(i,j)!=null){

                }
            }
        }

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void render() {
        world.step(1/Gdx.graphics.getDeltaTime(),1,1);
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.end();
        ren.render();
        ren.setView(camera);
        ren2.render(world,camera.combined);
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
