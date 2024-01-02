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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
public class FourWayMotion implements Motion {
   private final static int NORTH = 0;
   private final static int EAST = 1;
   private final static int SOUTH = 2;
   private final static int WEST = 3;
   private Animation[] animations = new Animation[4];
   private int index;

   /**
    * Creates a TwoWayMotion to control the animation. The animation changes as the direction changes.
    * @param north
    * @param east
    * @param south
    * @param west
    */
   public FourWayMotion(Animation north, Animation east, Animation south, Animation west) {
      animations[NORTH] = north;
      animations[EAST] = east;
      animations[SOUTH] = south;
      animations[WEST] = west;
   }

   /**
    * Creates a TwoWayMotion to control the animation. The animation changes as the direction changes. The Texture is
    * split into a TextureRegion and made into a LoopAnimation, where the elements are expected to be arranged where the
    * first row is the character moving North (or up), second is East (or right), third is South (or down) and the
    * fourth is West (or left).
    * @param texture
    * @param tileWidth
    * @param tileHeight
    * @param duration
    */
   public FourWayMotion(Texture texture, int tileWidth, int tileHeight, float duration) {
      TextureRegion[][] textureRegions = TextureRegion.split(texture, tileWidth, tileHeight);
      animations[NORTH] = new LoopAnimation(duration, textureRegions[0]);
      animations[EAST] = new LoopAnimation(duration, textureRegions[1]);
      animations[SOUTH] = new LoopAnimation(duration, textureRegions[2]);
      animations[WEST] = new LoopAnimation(duration, textureRegions[3]);
   }

   /**
    * Creates a TwoWayMotion to control the animation. The animation changes as the direction changes. The Texture is
    * split into a TextureRegion and made into a LoopAnimation, where the elements are arranged where the north row is
    * the character moving North (or up), east row is East (or right), the south row is South (or down) and the west row
    * is West (or left).
    * @param texture
    * @param tileWidth
    * @param tileHeight
    * @param duration
    * @param north
    * @param east
    * @param south
    * @param west
    */
   public FourWayMotion(Texture texture, int tileWidth, int tileHeight, float duration, int north, int east, int south,
      int west) {
      TextureRegion[][] textureRegions = TextureRegion.split(texture, tileWidth, tileHeight);
      animations[NORTH] = new LoopAnimation(duration, textureRegions[north]);
      animations[EAST] = new LoopAnimation(duration, textureRegions[east]);
      animations[SOUTH] = new LoopAnimation(duration, textureRegions[south]);
      animations[WEST] = new LoopAnimation(duration, textureRegions[west]);
   }

   @Override
   public void draw(SpriteBatch batch, float x, float y) {
      animations[index].draw(batch, x, y);
   }

   @Override
   public Animation getAnimation(int index) {
      if (index > 3 || index < 0) {
         return null;
      }
      return animations[index];
   }

   @Override
   public int getHeight() {
      return animations[index].getHeight();
   }

   @Override
   public int getWidth() {
      return animations[index].getWidth();
   }

   @Override
   public void setColor(Color color) {
      for (Animation animation1 : animations) {
         animation1.setColor(color);
      }
   }

   @Override
   public void setColor(float red, float green, float blue) {
      for (Animation animation1 : animations) {
         animation1.setColor(red, green, blue);
      }
   }

   @Override
   public void setColor(int red, int green, int blue) {
      for (Animation animation1 : animations) {
         animation1.setColor(red, green, blue);
      }
   }

   @Override
   public void setDirection(float direction) {
      setIndex(direction);
   }

   @Override
   public void setSize(int width, int height) {
      for (Animation animation1 : animations) {
         animation1.setSize(width, height);
      }
   }

   @Override
   public void setSound(Sound sound) {
      for (Animation animation1 : animations) {
         animation1.setSound(sound);
      }
   }

   @Override
   public void setTranslucency(float percent) {
      for (Animation animation1 : animations) {
         animation1.setTranslucency(percent);
      }
   }

   @Override
   public void update(float deltaTime, float direction) {
      direction = Direction.nearest4thDirection(direction);
      setIndex(direction);
      animations[index].update(deltaTime);
   }

   private void setIndex(float direction) {
      if (direction == Direction.NORTH) {
         index = NORTH;
      } else if (direction == Direction.EAST) {
         index = EAST;
      } else if (direction == Direction.SOUTH) {
         index = SOUTH;
      } else {
         index = WEST;
      }
   }

}
