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

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StaticAnimation extends BaseAnimation {
   private Texture texture;
   private int xStart = 0;
   private int xSrcWidth;
   private int yStart = 0;
   private int ySrcHeight;
   private boolean flipHorizontal = false;
   private boolean flipVertical = false;

   /**
    * Constructs a StaticAnimation based on the Texture's size.
    * @param texture
    */
   public StaticAnimation(Texture texture) {
      this.texture = texture;
      xSrcWidth = texture.getWidth();
      ySrcHeight = texture.getHeight();
      width = texture.getWidth();
      height = texture.getHeight();
      this.originX = width / 2;
      this.originY = height / 2;
   }

   /**
    * Constructs a StaticAnimation with a Texture and resizes it.
    * @param texture
    * @param width
    * @param height
    */
   public StaticAnimation(Texture texture, int width, int height) {
      this.texture = texture;
      xSrcWidth = texture.getWidth();
      ySrcHeight = texture.getHeight();
      this.width = width;
      this.height = height;
      this.originX = width / 2;
      this.originY = height / 2;
   }

   /**
    * StaticAnimations have no update.
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
    * Crops the image, with the starting X and Y locations within the image, and Width and Height from the starting to
    * the edge of the images. Any width or Height that goes beyond the original image size, will cause the last pixels
    * to repeat. The image will resize based on the Height and Width set for the Animation.
    * @param xStart
    * @param yStart
    * @param xSrcWidth
    * @param ySrcHeight
    */
   public void setCropping(int xStart, int yStart, int xSrcWidth, int ySrcHeight) {
      this.xStart = xStart;
      this.yStart = yStart;
      this.xSrcWidth = xSrcWidth;
      this.ySrcHeight = ySrcHeight;
      width = xSrcWidth;
      height = ySrcHeight;
      if (rotation != 0) {
         this.originX = width / 2;
         this.originY = height / 2;
      }
   }

   /**
    * Flips the image horizontally, side to side.
    * @param flipHorizontal
    */
   public void setFlipHorizontal(boolean flipHorizontal) {
      this.flipHorizontal = flipHorizontal;
   }

   /**
    * Flips the image vertically, top to bottom.
    * @param flipVertical
    */
   public void setFlipVertical(boolean flipVertical) {
      this.flipVertical = flipVertical;
   }

   /**
    * Renders the image to the screen based on the location provided and the parameters set by other methods.
    * @param batch
    * @param x
    * @param y
    */
   @Override
   protected void drawChild(SpriteBatch batch, float x, float y) {
      batch.draw(texture, x + xOffset, y + yOffset, originX, originY, width, height, 1, 1, rotation, xStart, yStart,
         xSrcWidth, ySrcHeight, flipHorizontal, flipVertical);
   }

}
