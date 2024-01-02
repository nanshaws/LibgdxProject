package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Mario extends Actor {
   public boolean isWalking;
   public boolean isRunning;
   public boolean isGround;
   public SpriteBatch batch;
   public TextureRegion img;
   public Animation animation;
   
}
