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
import org.abberkeep.gameframework.utils.FastMath;

/**
 * Title: ScriptAction
 *
 * <p>
 * Description: This abstract class is the base for all actions used by the ScriptMovement. It defines a currentTime,
 * startDirection and startSpeed, with a reference to the ScriptMovement and a done flag. The startDirection and
 * startSpeed will be set from the parent as part of the setup() method.</p>
 *
 * Copyright (c) Nov 15, 2023
 * @author Gary Deken
 * @version 0.12
 */
public abstract class ScriptAction {
   protected float xUpdate;
   protected float yUpdate;
   protected float initialDirection;
   protected float initialSpeed;
   protected float direction;
   protected float changeInDirection;
   protected float speed;
   protected float currentTime;
   protected float duration;
   protected boolean done = false;
   protected boolean changeDirectionGradually = false;

   public float getDirection() {
      return direction;
   }

   public float getSpeed() {
      return speed;
   }

   public float getXUpdate() {
      return xUpdate;
   }

   public float getYUpdate() {
      return yUpdate;
   }

   /**
    * Returns if this ScriptAction has completed.
    * @return
    */
   public boolean isDone() {
      return done;
   }

   /**
    * Sets up this ScriptAction, setting the initialDirection and initialSpeed.
    * @param direction
    * @param speed
    */
   public void setup(float direction, float speed) {
      done = false;
      currentTime = 0;
      initialDirection = direction;
      initialSpeed = speed;
      if (changeDirectionGradually) {
         setupChangingDirectionGradually();
      }
   }

   public void setSpeed(float speed) {
      this.speed = speed;
   }

   /**
    * This replaces the Movement's update() method with an ScriptAction specific implementation. The SpriteAction needs
    * to call the parent's calculateMagnitudes(float direction, float speed) and
    * spriteUpdate.setLocation(spriteUpdate.getX() + parent.getXUpdate(), spriteUpdate.getY() + parent.getYUpdate());
    * @param deltaTime
    * @param spriteUpdate
    */
   public void update(float deltaTime, SpriteUpdate spriteUpdate) {
      currentTime += deltaTime;
      updateChild(deltaTime, spriteUpdate);
   }

   public abstract void updateChild(float deltaTime, SpriteUpdate spriteUpdate);

   /**
    * Calculates the X and Y update values based on the direction and updateSpeed. The updateSpeed is number of pixels
    * per second. If the updateSpeed is zero it does not calculate magnitudes, setting those to zero.
    *
    * @param direction
    * @param updateSpeed
    */
   protected void calculateMagnitudesByDirection(float direction, float updateSpeed) {
      if (updateSpeed > 0f) {
         // calculate the x & y distance based on the direction and speed.
         double radians = Math.toRadians(direction);
         yUpdate = (float) (FastMath.fastSin(radians) * updateSpeed);
         xUpdate = (float) (FastMath.fastCos(radians) * updateSpeed);
      } else {
         xUpdate = 0;
         yUpdate = 0;
      }
   }

   protected float getUpdatedDirection(float deltaTime) {
      if (changeDirectionGradually) {
         direction += (changeInDirection * deltaTime);
         direction = Direction.convertTo360Degrees(direction);
      }
      return direction;
   }

   protected float getPercentComplete() {
      return currentTime / duration;
   }

   /**
    * This methods setups the changing the direction gradually.
    */
   private void setupChangingDirectionGradually() {
      if ((initialDirection <= 90f && this.direction >= 270f) || (initialDirection - this.direction < -180)) {
         // if both are around 0 degrees Or the difference is less then 180
         //      | 1 3
         //  -----------
         //   4 | 2
         float id = initialDirection + 360f;
         changeInDirection = (this.direction - id) / duration;
         this.direction = initialDirection;
      } else if ((initialDirection >= 270f && this.direction <= 90f) || (this.direction - initialDirection < -180)) {
         // if both are around 0 degrees Or the difference is less then 180
         //   4 | 2
         //  -----------
         //      | 1 3
         float d = this.direction + 360f;
         changeInDirection = (d - initialDirection) / duration;
         this.direction = initialDirection;
      } else {
         // if both are in one of three locations
         //   1 3 | 1
         //  --------------
         //   2 3 | 2
         changeInDirection = (this.direction - initialDirection) / duration;
         this.direction = initialDirection;
      }
   }

}
