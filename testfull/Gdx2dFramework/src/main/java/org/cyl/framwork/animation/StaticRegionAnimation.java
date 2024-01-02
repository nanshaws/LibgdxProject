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

public class StaticRegionAnimation extends BaseAnimation {
   private TextureRegion region;

   /**
    * Constructs a StaticRegionAnimation based on the TextureRegion's size.
    * @param region
    */
   public StaticRegionAnimation(TextureRegion region) {
      this.region = region;
      this.width = region.getRegionWidth();
      this.height = region.getRegionHeight();
      this.originX = width / 2;
      this.originY = height / 2;
   }

   /**
    * StaticRegionAnimations have no update.
    * @param deltaTime
    */
   @Override
   public void update(float deltaTime) {
      if (stateTime == 0.0f && sound != null) {
         sound.play();
         stateTime = 1f;
      }
   }

   /**
    * Renders the TextureRegion to the screen based on the location provided and the parameters set by other methods.
    * @param batch
    * @param x
    * @param y
    */
   @Override
   protected void drawChild(SpriteBatch batch, float x, float y) {
      batch.draw(region, x + xOffset, y + yOffset, originX, originY, width, height, 1, 1, rotation);
   }

}
