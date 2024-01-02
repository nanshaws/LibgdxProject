package org.cyl.motion;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Motion {
    void draw(SpriteBatch batch,float x,float y);
    void update(float deltaTime,float direction);
}
