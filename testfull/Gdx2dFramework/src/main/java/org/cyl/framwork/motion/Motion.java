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
package org.cyl.framwork.motion;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.abberkeep.gameframework.animation.Animation;

/**
 * Title: Motion
 *
 * <p>
 * Description: This interface encapsulates the Animations based on the direction of the Sprite. These classes will
 * maintain the Animation objects that the motion uses, updating the correct one and return the current image to be
 * displayed based on the direction of the sprite. There will be an Animation for each direction that the Game Piece
 * will need motion for.</p>
 *
 * Copyright (c) May 14, 2023
 * @author Gary Deken
 * @version 0.6
 */
public interface Motion {

   /**
    * Draws the Motion on the Screen, based on the underlining Animation class.
    * @param batch
    * @param x
    * @param y
    */
   void draw(SpriteBatch batch, float x, float y);

   /**
    * Returns the Animation at the given index of the Motion's Animation list. If the index is beyond the number of
    * Animations, then null would be returned.
    * @param index
    * @return
    */
   Animation getAnimation(int index);

   /**
    * Returns the Height of this Motion, based on the current Animation.
    * @return
    */
   int getHeight();

   /**
    * Returns the Width of this Motion, based on the current Animation.
    * @return
    */
   int getWidth();

   /**
    * Set the color for all the Animations in this Motion, by the LibGdx Color object. The default is White.
    * @param color
    */
   void setColor(Color color);

   /**
    * Set the color for all the Animations in this Motion by percentage (0.0 to 1.0). The default is 1, 1, 1, or white.
    * @param red
    * @param green
    * @param blue
    */
   void setColor(float red, float green, float blue);

   /**
    * Set the color for all the Animations in this Motion from RGB 0-255 range. The range is converted to a decimal 0.0
    * to 1.0.
    * @param red
    * @param green
    * @param blue
    */
   void setColor(int red, int green, int blue);

   /**
    * Sets the direction of the Motion. This is useful if another motion sets a direction, and the Actor then flips to
    * this motion, they will end up having the same direction.
    * @param direction
    */
   void setDirection(float direction);

   /**
    * Sets the size of all the Animations in this Motion to the size that it will be drawn, resizing the image. This
    * will also set the rotation origin to the center of the image.
    * @param width
    * @param height
    */
   void setSize(int width, int height);

   /**
    * Sets a Sound on all the Animation in the Motion.
    * @param sound
    */
   void setSound(Sound sound);

   /**
    * Sets the translucency of all the Animations in this Motion. This is the percentage that you can see through the
    * image. 1 is normal, no translucency. 0 is fully transparent.
    * @param percent
    */
   void setTranslucency(float percent);

   /**
    * Updates the Motion, where the implementing class will determine the Animation to update based on the Direction
    * passed in.
    * @param deltaTime
    * @param direction
    */
   void update(float deltaTime, float direction);
}
