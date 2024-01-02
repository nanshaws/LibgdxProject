/*
 * Copyright (c) 2022-2023 Gary Deken
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
package org.cyl.framwork.movement;

import org.abberkeep.gameframework.sprite.BoundingBox;
import org.abberkeep.gameframework.sprite.SpriteUpdate;

/**
 * Title: NoMovement
 *
 * <p>
 * Description: This Movement does not move. But it does have Direction. Its update values are always zero.</p>
 *
 * Copyright (c) Nov 12, 2023
 * @author Gary Deken
 * @version 0.12
 */
public class NoMovement implements Movement {
   private float direction;

   /**
    * This create NoMovement Movement with the given direction.
    * @param direction
    */
   public NoMovement(float direction) {
      this.direction = direction;
   }

   @Override
   public float getDirection() {
      return direction;
   }

   @Override
   public float getCurrentSpeed() {
      return 0f;
   }

   @Override
   public void handleCollision(SpriteUpdate spriteUpdate, BoundingBox other) {
      // Do nothing.
   }

   @Override
   public void update(float deltaTime, SpriteUpdate spriteUpdate) {
      // Do nothing.
   }

   @Override
   public void setSpeed(float speed) {
      // Do nothing.
   }

}
