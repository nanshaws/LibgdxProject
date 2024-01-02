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
import com.badlogic.gdx.math.MathUtils;
import org.cyl.framwork.Updatable;

public class RandomAnimation extends BaseAnimation {
   private TextureRegion[] frames;
   private float animationDuration;
   private int currentIndex = 0;
   private Updatable randomUpdate;
   private int lastFrameIndex;
   private float lastStateTime;
   private boolean playSound = true;
   private int countIndex = 0;

   /**
    * Creates a RandomAnimation that randomly renders frames continuously.
    * @param frameDuration
    * @param region
    */
   public RandomAnimation(float frameDuration, TextureRegion[] region) {
      frames = region;
      animationDuration = frameDuration * frames.length;
      width = region[0].getRegionWidth();
      height = region[0].getRegionHeight();
      originX = width / 2;
      originY = height / 2;
      randomUpdate = (deltaTime) -> {
         stateTime += deltaTime;
         currentIndex = (int) (stateTime / frameDuration);
         int lastFrame = (int) ((lastStateTime) / frameDuration);
         if (lastFrame != currentIndex) {
            currentIndex = MathUtils.random(frames.length - 1);
            countIndex++;
         } else {
            currentIndex = lastFrameIndex;
         }
         lastFrameIndex = currentIndex;
         lastStateTime = stateTime;
         if (sound != null) {
            updateSound();
         }
      };
   }

   public RandomAnimation(float frameDuration, TextureRegion[] region, int numberOfCycles) {
      if (numberOfCycles < 1) {
         throw new IllegalArgumentException("The numberOfCycles must be one or greater.");
      }
      frames = region;
      animationDuration = frameDuration * frames.length;
      width = region[0].getRegionWidth();
      height = region[0].getRegionHeight();
      originX = width / 2;
      originY = height / 2;
      randomUpdate = (deltaTime) -> {
         stateTime += deltaTime;
         currentIndex = (int) (stateTime / frameDuration);
         int cycleNumber = (int) (stateTime / animationDuration);
         if (cycleNumber < numberOfCycles) {
            int lastFrame = (int) ((lastStateTime) / frameDuration);
            if (lastFrame != currentIndex) {
               currentIndex = MathUtils.random(frames.length - 1);
               countIndex++;
            } else {
               currentIndex = lastFrameIndex;
            }
            lastFrameIndex = currentIndex;
            lastStateTime = stateTime;
         } else {
            currentIndex = lastFrameIndex;
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
      randomUpdate.update(deltaTime);
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
      if (countIndex >= frames.length) {
         countIndex = 0;
      }
      if (playSound && countIndex == 0) {
         sound.play();
         playSound = false;
      } else if (countIndex > 0) {
         playSound = true;
      }
   }

}
