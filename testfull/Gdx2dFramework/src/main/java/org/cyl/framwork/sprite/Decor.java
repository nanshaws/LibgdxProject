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
package org.cyl.framwork.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.abberkeep.gameframework.animation.Animation;

/**
 * Title: Decor
 *
 * <p>
 * Description: Defines a sprite that does not move on the screen. (Has no movement) The decor is static "image", once
 * placed it doesn't move.</p>
 *
 * Copyright (c) Aug 5, 2023
 * @author Gary Deken
 * @version 0.9
 */
public class Decor extends Sprite {
   protected Animation animation;

   public Decor(Animation animation) {
      super(animation.getWidth(), animation.getHeight());
      this.animation = animation;
   }

   @Override
   public boolean doesImpact() {
      return false;
   }

   @Override
   public void draw(SpriteBatch batch) {
      animation.draw(batch, x, y);
   }

   /**
    * Sets the Animation for this Decor. The Animation must not be null.
    * @param animation
    * @throws IllegalArgumentException
    */
   public void setAnimation(Animation animation) {
      if (animation == null) {
         throw new IllegalArgumentException("Animation must not be null.");
      }
      this.animation = animation;
   }

   @Override
   public void update(float deltaTime) {
      animation.update(deltaTime);
   }

}
