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

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.cyl.framwork.BaseGame;

public class BlockAnimation extends BaseAnimation {
   private static Texture texture;

   /**
    * Creates a BlockAnimation with the given width and height.
    * @param width
    * @param height
    */
   public BlockAnimation(int width, int height) {
      if (texture == null) {
         texture = BaseGame.getGlobalTexture("blank.png");
      }
      color = Color.WHITE;
      this.width = width;
      this.height = height;
   }

   @Override
   public void update(float deltaTime) {
      if (stateTime == 0.0f && sound != null) {
         sound.play();
         stateTime = 1f;
      }
   }

   @Override
   protected void drawChild(SpriteBatch batch, float x, float y) {
      batch.draw(texture, x + xOffset, y + yOffset, width, height);
   }

}
