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
package org.cyl.framwork.movement;

import org.abberkeep.gameframework.utils.FastMath;

/**
 * Title: BaseMovement
 *
 * <p>
 * Description: This base class holds all the attributes associated with all Movment classes.</p>
 *
 * Copyright (c) Jun 22, 2023
 * @author Gary Deken
 * @version 0.7
 */
public abstract class BaseMovement implements Movement {
   protected float xUpdate;
   protected float yUpdate;
   protected float direction;
   protected float speed;

   @Override
   public float getDirection() {
      return direction;
   }

   @Override
   public float getCurrentSpeed() {
      if (xUpdate == 0.0f && yUpdate == 0.0f) {
         return 0.0f;
      }
      return speed;
   }

   @Override
   public void setSpeed(float speed) {
      this.speed = speed;
   }

   /**
    * Calculates the X and Y update values based on the direction and updateSpeed. The updateSpeed is number of pixels
    * per second. If the updateSpeed is zero it does not calculate magnitudes, setting those to zero.
    *
    * @param direction
    * @param updateSpeed
    */
   protected void calculateMagnitudesByDirection(float direction, float updateSpeed) {
      this.direction = direction;
      if (updateSpeed > 0) {
         // calculate the x & y distance based on the direction and speed.
         double radians = Math.toRadians(direction);
         yUpdate = (float) (FastMath.fastSin(radians) * updateSpeed);
         xUpdate = (float) (FastMath.fastCos(radians) * updateSpeed);
      } else {
         xUpdate = 0;
         yUpdate = 0;
      }
   }

}
