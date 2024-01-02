package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Demo extends ApplicationAdapter {

    public Texture sandTexture;
    public SpriteBatch spriteBatch;
    public Sprite textureSprite;
    public OrthographicCamera camera;
    @Override
    public void create() {
        spriteBatch=new SpriteBatch();
        camera=new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        sandTexture=new Texture(Gdx.files.internal("sand.png"));
        textureSprite=new Sprite(sandTexture);
        textureSprite.setBounds(0,0,200,200);
    }

    @Override
    public void render() {
      handleInput();
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
      spriteBatch.begin();
      spriteBatch.draw(sandTexture,0,0,200,200);
      textureSprite.draw(spriteBatch);
      spriteBatch.end();
    }

    private void handleInput() {
        //左移相机
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.translate(-3, 0, 0);
        }
        //右移相机
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            camera.translate(3, 0, 0);
        }
        //下移相机
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            camera.translate(0, -3, 0);
        }
        //上移相机
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            camera.translate(0, 3, 0);
        }
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        sandTexture.dispose();
    }

    @Override
    public void resize(int width, int height) {
        spriteBatch.getProjectionMatrix().setToOrtho2D(0,0,width,height);
    }
}
