package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;

public class MapUtil extends Sprite {
    TiledMap load;
    TextureRegion textureRegion;
    Color bgcolor;
    Body body;
    public float playx;
    public float playy;
    public TiledMap getTmxMapLoader(){
        load = new TmxMapLoader().load("map05.tmx");
        textureRegion=new TextureRegion(new Texture(Gdx.files.internal("t1.png")));
        bgcolor=new Color();
        return load;
    }

    public void createStatic(TiledMap map, World world,FixtureDef fixtureDef,BodyDef bodyDef) {

        MapObjects objects = map.getLayers().get("objects").getObjects();
        bodyDef.type= BodyDef.BodyType.StaticBody;
        Array<RectangleMapObject> rmos=objects.getByType(RectangleMapObject.class);
        for (RectangleMapObject rmo:rmos){
            if (rmo.getName()!=null&&rmo.getName().equals("mario")){
                playx= rmo.getRectangle().x;
                playy= rmo.getRectangle().y;
                continue;
            }
            Rectangle rect=rmo.getRectangle();
            PolygonShape shape=new PolygonShape();
            shape.setAsBox(rect.getWidth()/2,rect.getHeight()/2);
            fixtureDef.shape=shape;
            bodyDef.position.set((rect.getX()+rect.getWidth()/2),(rect.getY()+rect.getHeight()/2));
            world.createBody(bodyDef).createFixture(fixtureDef);

        }
    }


    public void setColor(Color color){
        bgcolor.set(color);
    }
    public Color getColor(){
        return bgcolor;
    }

    public void createDystatic(BodyDef bodyDef, FixtureDef fixtureDef,World world) {

        bodyDef.type=BodyDef.BodyType.DynamicBody;
        fixtureDef.restitution=0f;
        bodyDef.position.set(playx+8,playy);
        PolygonShape shape=new PolygonShape();
        shape.setAsBox(40/2.5f,50/3f);
        fixtureDef.shape=shape;
        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        setRegion(textureRegion);
    }
    public void draw(SpriteBatch batch,TiledMap map){
        setRegion(textureRegion);
    }


    public void handleInput(SpriteBatch batch,World world) {
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            playx+=4;
            body.applyForceToCenter(new Vector2(playx, playy), true);
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){
           batch.draw(textureRegion,playx,playy);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            playx-=4;
            body.applyForceToCenter(new Vector2(playx, playy), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)&&Gdx.input.isKeyPressed(Input.Keys.D)){
            playx+=4;
            playy+=4;
            body.applyForceToCenter(new Vector2(playx, playy), true);

        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)&&Gdx.input.isKeyPressed(Input.Keys.A)){
            playx-=4;
            playy+=4;
            body.applyForceToCenter(new Vector2(playx, playy), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            playy-=4;
            body.applyForceToCenter(new Vector2(playx, playy), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            playy+=4;
            body.applyForceToCenter(new Vector2(playx, playy), true);
        }
    }
    // Mouse / touch
//		int mouseX = Gdx.input.getX();
//		int mouseY = Gdx.input.getY();
//		boolean leftPressed = Gdx.input.isButtonPressed(Input.Buttons.LEFT); // 鼠标左键触发事件
//		boolean rightPressed = Gdx.input.isButtonPressed(Input.Buttons.RIGHT); // 鼠标右键触发事件
//		boolean middlePressed = Gdx.input.isButtonPressed(Input.Buttons.MIDDLE); // 鼠标中键触发事件
//		System.out.println(mouseX+":"+mouseY+"左键是否成功"+leftPressed+"右键是否成功"+rightPressed);
}
