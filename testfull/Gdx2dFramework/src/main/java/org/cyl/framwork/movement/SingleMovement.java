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
 * Title: SingleMovement
 *
 * <p>
 * Description: This represent a single Movement. It has a set speed and a set direction, which does not change.</p>
 *
 * Copyright (c) Nov 9, 2023
 * @author Gary Deken
 * @version 0.12
 */
public class SingleMovement extends BaseMovement {

   /**
    * Creates a SingleMovement with the given speed and direction.
    * @param speed
    * @param direction
    */
   public SingleMovement(float speed, float direction) {
      this.speed = speed;
      this.direction = direction;
   }

   @Override
   public void handleCollision(SpriteUpdate spriteUpdate, BoundingBox other) {
      // stop moving
      spriteUpdate.setLocation(spriteUpdate.getX() - xUpdate, spriteUpdate.getY() - yUpdate);
   }

   @Override
   public void update(float deltaTime, SpriteUpdate spriteUpdate) {
      calculateMagnitudesByDirection(direction, speed);
      spriteUpdate.setLocation(spriteUpdate.getX() + xUpdate, spriteUpdate.getY() + yUpdate);
   }

}
