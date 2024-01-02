/*
 * Copyright (c) 2023 Gary Deken
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.cyl.framwork.animation;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.cyl.framwork.Updatable;


public interface Animation extends Updatable {

   /**
    * Draws the Animation to the screen.
    * @param batch
    * @param x
    * @param y
    */
   void draw(SpriteBatch batch, float x, float y);

   int getHeight();

   int getWidth();

   /**
    * Set the color for the Animation, by the LibGdx Color object. The default is White.
    * @param color
    */
   void setColor(Color color);

   /**
    * Set the color for the Animation by percentage (0.0 to 1.0). The default is 1, 1, 1, or white.
    * @param red
    * @param green
    * @param blue
    */
   void setColor(float red, float green, float blue);

   /**
    * Set the color for the Animation from RGB 0-255 range. The range is converted to a decimal 0.0 to 1.0.
    * @param red
    * @param green
    * @param blue
    */
   void setColor(int red, int green, int blue);

   /**
    * Sets the size of the Animation to the size that it will be drawn, resizing the image. This will also set the
    * rotation origin to the center of the image.
    * @param width
    * @param height
    */
   void setSize(int width, int height);

   /**
    * Set the sound for this Animation.
    * @param sound
    */
   void setSound(Sound sound);

   /**
    * Sets the translucency of the Animation. This is the percentage that you can see through the image. 1 is normal, no
    * translucency. 0 is fully transparent.
    * @param percent
    */
   void setTranslucency(float percent);

   /**
    * Sets an X offset in pixel to render this Animation image.
    * @param xOffset
    */
   void setXOffSet(int xOffset);

   /**
    * Sets an Y offset in pixel to render this Animation image.
    * @param yOffset
    */
   public void setYOffset(int yOffset);

   /**
    * Updates the animation.
    * @param deltaTime
    */
   @Override
   void update(float deltaTime);
}
