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
package org.cyl.framwork.movement.actions;

import org.abberkeep.gameframework.movement.Direction;
import org.abberkeep.gameframework.sprite.SpriteUpdate;

/**
 * Title: WaitAction
 *
 * <p>
 * Description: The ScriptAction waits for the specified time in seconds. If the time is zero or negative it will
 * default to being done.</p>
 *
 * Copyright (c) Nov 15, 2023
 * @author Gary Deken
 * @version 0.12
 */
public class WaitAction extends ScriptAction {

   /**
    * Creates a WaitAction that cause the Actor to have no movement for the duration. If the wait time is set to zero
    * (or negative), this then WaitAction will start out as being marked as done, causing the ScriptMovement to proceed
    * to the next ScriptAction.
    * @param waitTime
    */
   public WaitAction(float waitTime) {
      this.duration = waitTime;
      if (waitTime <= 0) {
         done = true;
      }
   }

   /**
    * Creates a WaitAction that cause the Actor to have no movement for the duration and setting its direction to the
    * passed in value. If the wait time is set to zero (or negative), this then WaitAction will start out as being
    * marked as done, causing the ScriptMovement to proceed to the next ScriptAction.
    * @param waitTime
    * @param direction
    */
   public WaitAction(float waitTime, float direction) {
      this.duration = waitTime;
      if (waitTime <= 0) {
         done = true;
      }
      this.direction = Direction.convertTo360Degrees(direction);
   }

   @Override
   public void updateChild(float deltaTime, SpriteUpdate spriteUpdate) {
      calculateMagnitudesByDirection(direction, 0f);
      spriteUpdate.setLocation(spriteUpdate.getX() + xUpdate, spriteUpdate.getY() + yUpdate);
      if (currentTime >= duration) {
         done = true;
      }
   }

}
