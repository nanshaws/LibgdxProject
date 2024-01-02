package org.cyl.animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Animation {
   void draw(SpriteBatch batch,float x,float y) ;
   float getHeight();
   float getWidth();
   void update(float deltaTime);
}
