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

import com.badlogic.gdx.Gdx;
import org.abberkeep.gameframework.screen.ScreenInput;
import org.abberkeep.gameframework.sprite.BoundingBox;
import org.abberkeep.gameframework.sprite.SpriteUpdate;

/**
 * Title: MouseMovement
 *
 * <p>
 * Description: Encapsulates the Movement controlled by clicking (touching) the screen, and updating the movement to go
 * towards that locations.</p>
 *
 * Copyright (c) Jul 1, 2023
 *
 * @author Gary Deken
 * @version 0.8
 */
public class MouseMovement extends BaseMovement {
   private boolean isButtonClicked = false;
   private int buttonID;
   private float goalX;
   private float goalY;
   private boolean xPositive;
   private boolean yPositive;

   /**
    * Constructs a MouseMovement based on the buttonID and the speed.
    *
    * @param buttonID
    * @param speed
    */
   public MouseMovement(int buttonID, float speed) {
      this.buttonID = buttonID;
      this.speed = speed;
   }

   @Override
   public void handleCollision(SpriteUpdate spriteUpdate, BoundingBox other) {
      // determine which direction was the collision
      // check if reverting X update there is no collision
      spriteUpdate.setX(spriteUpdate.getX() - xUpdate);
      if (spriteUpdate.contains(other)) {
         // Still collision, so collided on Y axis. Set X back and revert Y update.
         spriteUpdate.setX(spriteUpdate.getX() + xUpdate);
         spriteUpdate.setY(spriteUpdate.getY() - yUpdate);
      }
      // otherwise collision is on the X axis, leave reverted.
   }

   @Override
   public void update(float deltaTime, SpriteUpdate spriteUpdate) {
      float x = spriteUpdate.getX();
      float y = spriteUpdate.getY();
      if (!isButtonClicked) {
         goalX = x;
         goalY = y;
      }

      if (Gdx.input.isButtonPressed(buttonID)) {
         isButtonClicked = true;
         goalX = ScreenInput.getX();
         goalY = ScreenInput.getY();
         float newDirection = Direction.getDirection(x, y, goalX, goalY);
         calculateMagnitudesByDirection(newDirection, speed);
         xPositive = goalX > x;
         yPositive = goalY > y;
      }
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
         xUpdate = 0f;
         yUpdate = 0f;
      }
   }

}
