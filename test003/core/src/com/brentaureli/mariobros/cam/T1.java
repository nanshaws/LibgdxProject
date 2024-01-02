package com.brentaureli.mariobros.cam;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import static com.badlogic.gdx.Gdx.gl;

public class T1 implements ApplicationListener {

    private OrthographicCamera camera;
    private float VIEWPORT_WIDTH=100f;
    private float VIEWPORT_HEIGHT=100f;
    private SpriteBatch batch;
    private Sprite sprite;
    @Override
    public void create() {
        sprite=new Sprite(new Texture(Gdx.files.internal("block.png")));
        sprite.setSize(VIEWPORT_WIDTH,VIEWPORT_HEIGHT);
        sprite.setPosition(0, 0);
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera=new OrthographicCamera(100,100*h/w);
        camera.position.set(15f,11.25f,0);
        camera.update();
        batch=new SpriteBatch();
    }

    @Override
    public void resize(int width, int height) {

        //调整窗口，相机位置不变
        camera.viewportWidth = 30f;
        camera.viewportHeight = 30f * height / width;
        camera.update();
    }

    @Override
    public void render() {

        handleInput();
        resetCamera();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    private void resetCamera() {
        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
            camera.zoom = 1;
            camera.rotate(0, 0, 0, 1);
        }
    }

    private void handleInput() {
        //放大相机
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.zoom += 0.02;
        }
        //缩小相机
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            camera.zoom -= 0.02;
        }
        //左移相机
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.translate(-3, 0, 0);
        }
        //右移相机
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.translate(3, 0, 0);
        }
        //下移相机
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camera.translate(0, -3, 0);
        }
        //上移相机
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            camera.translate(0, 3, 0);
        }
//        //判定相机范围，不超出地图外
//        camera.zoom = MathUtils.clamp(camera.zoom, 0.1f, 100 / camera.viewportWidth);
//        float effectiveViewportWidth = camera.viewportWidth * camera.zoom;
//        float effectiveViewportHeight = camera.viewportHeight * camera.zoom;
//        camera.position.x = MathUtils.clamp(camera.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
//        camera.position.y = MathUtils.clamp(camera.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        sprite.getTexture().dispose();
        batch.dispose();
    }
}
