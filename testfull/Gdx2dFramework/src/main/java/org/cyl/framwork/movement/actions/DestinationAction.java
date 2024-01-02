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
 * Title: DestinationAction
 *
 * <p>
 * Description: A ScriptAction that move the Actor from its current location to the goal location.</p>
 *
 * Copyright (c) Nov 16, 2023
 * @author Gary Deken
 * @version 0.12
 */
public class DestinationAction extends ScriptAction {
   private int initialX;
   private int initialY;
   private int goalX;
   private int goalY;
   private boolean directionSet = false;

   /**
    * This ScriptAction will move the Actor from its current location to the specified goal location.
    * @param goalX
    * @param goalY
    * @param speed
    */
   public DestinationAction(int goalX, int goalY, float speed) {
      this.goalX = goalX;
      this.goalY = goalY;
      this.speed = speed;
   }

   /**
    * This ScriptAction will move the Actor from its current location to the specified goal location. This action will
    * change direction gradually, in an arc.
    * @param goalX
    * @param goalY
    * @param speed
    * @param changeGradually
    */
   public DestinationAction(int goalX, int goalY, float speed, boolean changeGradually) {
      this.goalX = goalX;
      this.goalY = goalY;
      this.speed = speed;
      // TODO this is currently not working.
      this.changeDirectionGradually = changeGradually;
   }

   @Override
   public void setup(float direction, float speed) {
      super.setup(direction, speed);
      directionSet = false;
   }

   @Override
   public void updateChild(float deltaTime, SpriteUpdate spriteUpdate) {
      float x = spriteUpdate.getX();
      float y = spriteUpdate.getY();

      if (!directionSet) {
         initialX = (int) x;
         initialY = (int) y;
         directionSet = true;
         float x1 = initialX - goalX;
         float y1 = initialY - goalY;
         float f = (x1 * x1) + (y1 * y1);
         float distance = (float) Math.sqrt(f);
         duration = distance / speed;
         changeInDirection = (Direction.getDirection(initialX, initialY, goalX, goalY) - initialDirection) / duration;
      }
      direction = Direction.getDirection(x, y, goalX, goalY);
      calculateMagnitudesByDirection(getUpdatedDirection(deltaTime), speed);

      boolean xPositive = goalX > x;
      boolean yPositive = goalY > y;
      if (x != goalX || goalY != y) {
         float nx = spriteUpdate.getX() + xUpdate;
         if (xPositive) {
            if (nx > goalX) {
               nx = goalX;
            }
         } else {
            if (nx < goalX) {
               nx = goalX;
            }
         }
         spriteUpdate.setX(nx);
         float ny = spriteUpdate.getY() + yUpdate;
         if (yPositive) {
            if (ny > goalY) {
               ny = goalY;
            }
         } else {
            if (ny < goalY) {
               ny = goalY;
            }
         }
         spriteUpdate.setY(ny);
      } else {
         calculateMagnitudesByDirection(getUpdatedDirection(deltaTime), 0f);
      }
      if (spriteUpdate.getX() == goalX && spriteUpdate.getY() == goalY) {
         done = true;
      }
   }

}
