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

import java.util.function.Supplier;
import org.abberkeep.gameframework.movement.Direction;
import org.abberkeep.gameframework.sprite.SpriteUpdate;

/**
 * Title: EasingAction
 *
 * <p>
 * Description: A ScriptAction that will either ease in (accelerate) or ease out (decelerate) for a duration.</p>
 *
 * Copyright (c) Nov 15, 2023
 * @author Gary Deken
 * @version 0.12
 */
public class EasingAction extends ScriptAction {
   private boolean easeOut = false;
   private Supplier<Float> easing;

   /**
    * Creates an EasingAction where the movement will ease out (decelerate), for the given duration. The moving speed
    * and direction will be set from the previous action in the setup() method.
    * @param duration
    * @param power
    */
   public EasingAction(float duration, EASING_POWER power) {
      this.duration = duration;
      direction = -1;
      easeOut = true;
      easing = () -> 1 - (float) Math.pow(getPercentComplete(), power.power);
   }

   /**
    * Creates an EasingAction where the movement will ease out (decelerate), for the given duration and direction. The
    * moving speed and direction will be set from the previous action in the setup() method.
    * @param duration
    * @param direction
    * @param power
    */
   public EasingAction(float duration, float direction, EASING_POWER power) {
      this.direction = Direction.convertTo360Degrees(direction);
      this.duration = duration;
      easeOut = true;
      easing = () -> 1 - (float) Math.pow(getPercentComplete(), power.power);
   }

   /**
    * Creates an EasingAction where the movement will ease out (decelerate), for the given duration and direction. The
    * moving speed and direction will be set from the previous action in the setup() method. This action will change
    * direction gradually, in an arc.
    * @param duration
    * @param direction
    * @param power
    * @param changeGradually
    */
   public EasingAction(float duration, float direction, EASING_POWER power, boolean changeGradually) {
      this.direction = Direction.convertTo360Degrees(direction);
      this.duration = duration;
      easeOut = true;
      easing = () -> 1 - (float) Math.pow(getPercentComplete(), power.power);
      this.changeDirectionGradually = changeGradually;
   }

   /**
    * Creates an EasingAction where the movement will ease in (accelerate) to the given speed, for the given duration
    * and direction.
    * @param speed
    * @param direction
    * @param duration
    * @param power
    */
   public EasingAction(float speed, float direction, float duration, EASING_POWER power) {
      this.duration = duration;
      this.direction = Direction.convertTo360Degrees(direction);
      initialSpeed = speed;
      easing = () -> (float) Math.pow(getPercentComplete(), power.power);
   }

   /**
    * Creates an EasingAction where the movement will ease in (accelerate) to the given speed, for the given duration
    * and direction. This action will change direction gradually, in an arc.
    * @param speed
    * @param direction
    * @param duration
    * @param power
    * @param changeGradually
    */
   public EasingAction(float speed, float direction, float duration, EASING_POWER power, boolean changeGradually) {
      this.duration = duration;
      this.direction = Direction.convertTo360Degrees(direction);
      initialSpeed = speed;
      easing = () -> (float) Math.pow(getPercentComplete(), power.power);
      this.changeDirectionGradually = changeGradually;
   }

   @Override
   public void setSpeed(float speed) {
      if (easeOut) {
         this.initialSpeed = speed;
      }
   }

   @Override
   public void setup(float direction, float speed) {
      done = false;
      currentTime = 0;
      if (easeOut) {
         if (direction == -1) {
            this.direction = direction;
         }
         this.initialSpeed = speed;
      }
   }

   @Override
   public void updateChild(float deltaTime, SpriteUpdate spriteUpdate) {
      // we use initialSpeed instead of introducing a new attribute.
      speed = initialSpeed * easing.get();
      calculateMagnitudesByDirection(getUpdatedDirection(deltaTime), speed);
      spriteUpdate.setLocation(spriteUpdate.getX() + xUpdate, spriteUpdate.getY() + yUpdate);
      // need to account for floats not be percise
      if (currentTime >= duration) {
         done = true;
      }
   }

   public enum EASING_POWER {
      BI(2), TRI(3), QUAD(4), QUINT(5);
      int power;

      private EASING_POWER(int power) {
         this.power = power;
      }
   }

}
