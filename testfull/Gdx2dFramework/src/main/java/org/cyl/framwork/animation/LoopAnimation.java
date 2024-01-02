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

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.cyl.framwork.Updatable;

public class LoopAnimation extends BaseAnimation {
   private Updatable loopUpdate;
   private int currentIndex = 0;
   private TextureRegion[] frames;
   private float animationDuration;
   private boolean playSound = true;

   /**
    * Creates a LoopAnimation that loops continuously.
    * @param frameDuration
    * @param region
    */
   public LoopAnimation(float frameDuration, TextureRegion[] region) {
      frames = region;
      animationDuration = frameDuration * frames.length;
      width = region[0].getRegionWidth();
      height = region[0].getRegionHeight();
      originX = width / 2;
      originY = height / 2;
      loopUpdate = (deltaTime) -> {
         stateTime += deltaTime;
         currentIndex = (int) (stateTime / frameDuration);
         currentIndex = currentIndex % frames.length;
         if (sound != null) {
            updateSound();
         }
      };
   }

   /**
    * Creates a LoopAnimation that loops for a set number of times. The number of loops must be positive
    * @param frameDuration
    * @param region
    * @param numberOfLoops
    * @throws IllegalArgumentException when the numberOfLoops is zero or negative.
    */
   public LoopAnimation(float frameDuration, TextureRegion[] region, int numberOfLoops) {
      if (numberOfLoops < 1) {
         throw new IllegalArgumentException("The numberOfLoops needs to be one or greater.");
      }
      frames = region;
      animationDuration = frameDuration * frames.length;
      width = region[0].getRegionWidth();
      height = region[0].getRegionHeight();
      originX = width / 2;
      originY = height / 2;
      loopUpdate = (deltaTime) -> {
         stateTime += deltaTime;
         currentIndex = (int) (stateTime / frameDuration);
         int loopNumber = (int) (stateTime / animationDuration);
         if (loopNumber < numberOfLoops) {
            currentIndex = currentIndex % frames.length;
         } else {
            currentIndex = Math.min(frames.length - 1, currentIndex);
         }
         if (sound != null) {
            updateSound();
         }
      };
   }

   /**
    * Updates the sequence of the images to be displayed to the screen.
    * @param deltaTime
    */
   @Override
   public void update(float deltaTime) {
      loopUpdate.update(deltaTime);
   }

   /**
    * Renders the TextureRegion to the screen based on the location provided and the parameters set by other methods.
    * @param batch
    * @param x
    * @param y
    */
   @Override
   protected void drawChild(SpriteBatch batch, float x, float y) {
      batch.draw(frames[currentIndex], x + xOffset, y + yOffset, originX, originY, width, height, 1, 1, rotation);
   }

   private void updateSound() {
      if (playSound && currentIndex == 0) {
         sound.play();
         playSound = false;
      } else if (currentIndex > 0) {
         playSound = true;
      }
   }

}
