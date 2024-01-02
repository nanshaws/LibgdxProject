package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import org.cyl.animation.StaticAnimation;
import org.cyl.screen.BaseScreen;

public class ImageScreen extends BaseScreen {

    private Texture img;
    private StaticAnimation sa1;
    private StaticAnimation sa2;
    protected ImageScreen(int width, int height) {
        super(width, height);
        setBgColor(Color.WHITE);

    }

    @Override
    public void renderChild(float dateTime) {
        sa1.draw(batch,150,20);
        sa2.draw(batch,0,20);
    }

    @Override
    public void show() {
        img=new Texture("01.png");
       sa1=new StaticAnimation(img);
       sa1.setCropping(15,20,150,105);
       sa2=new StaticAnimation(img,256,256);
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
