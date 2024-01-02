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

import org.abberkeep.gameframework.sprite.SpriteUpdate;

/**
 * Title: ChangeSpeedAction
 *
 * <p>
 * Description: This action changes the speed of the Actor in a linear method.</p>
 *
 * Copyright (c) Nov 18, 2023
 * @author Gary Deken
 * @version 0.12
 */
public class ChangeSpeedAction extends ScriptAction {
   private float change;

   /**
    * Creates a ChangeSpeedAction that will change to the finalSpeed for the given duration.
    * @param finalSpeed
    * @param duration
    */
   public ChangeSpeedAction(float finalSpeed, float duration) {
      this.speed = finalSpeed;
      this.duration = duration;
   }

   /**
    * Creates a ChangeSpeedAction that will change to the finalSpeed and change direction for the given duration.
    * @param finalSpeed
    * @param direction
    * @param duration
    */
   public ChangeSpeedAction(float finalSpeed, float direction, float duration) {
      this.speed = finalSpeed;
      this.direction = direction;
      this.duration = duration;
   }

   /**
    * Creates a ChangeSpeedAction that will change to the finalSpeed and change direction for the given duration. This
    * action will change direction gradually, in an arc.
    * @param finalSpeed
    * @param direction
    * @param duration
    * @param changeGradually
    */
   public ChangeSpeedAction(float finalSpeed, float direction, float duration, boolean changeGradually) {
      this.speed = finalSpeed;
      this.direction = direction;
      this.duration = duration;
      this.changeDirectionGradually = changeGradually;
   }

   @Override
   public void setup(float direction, float speed) {
      super.setup(direction, speed);
      change = (this.speed - speed) / duration;
      this.speed = initialSpeed;
   }

   @Override
   public void updateChild(float deltaTime, SpriteUpdate spriteUpdate) {
      speed += (change * deltaTime);
      calculateMagnitudesByDirection(getUpdatedDirection(deltaTime), speed);
      spriteUpdate.setLocation(spriteUpdate.getX() + xUpdate, spriteUpdate.getY() + yUpdate);
      if (currentTime > duration) {
         done = true;
      }
   }

}
