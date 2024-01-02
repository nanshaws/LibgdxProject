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
import org.abberkeep.gameframework.sprite.BoundingBox;
import org.abberkeep.gameframework.sprite.SpriteUpdate;

/**
 * Title: TwoKeyMovement
 *
 * <p>
 * Description: Encapsulates getting the input from two keys and determines the direction and the amount of movement for
 * the update. This works either in a horizontal or horizontal direction.</p>
 *
 * Copyright (c) Jun 22, 2023
 *
 * @author Gary Deken
 * @version 0.7
 */
public class TwoKeyMovement extends BaseMovement {
   private int[] keyIds = new int[2];
   private boolean horizontal = true;

   /**
    * Constructs a TwoKeyMovement, with the key IDs and speed. If the direction is vertical (keyId1 is up, keyId2 is
    * down) or horizontal (keyId1 is right, keyId2 is left). The horizontal flag is for determining if the movement is
    * horizontal or vertical.
    *
    * @param keyId1
    * @param keyId2
    * @param speed
    * @param horizontal
    */
   public TwoKeyMovement(int keyId1, int keyId2, float speed, boolean horizontal) {
      keyIds[0] = keyId1;
      keyIds[1] = keyId2;
      this.speed = speed;
      this.horizontal = horizontal;
   }

   @Override
   public void handleCollision(SpriteUpdate spriteUpdate, BoundingBox other) {
      spriteUpdate.setLocation(spriteUpdate.getX() - xUpdate, spriteUpdate.getY() - yUpdate);
   }

   @Override
   public void update(float deltaTime, SpriteUpdate spriteUpdate) {
      if (Gdx.input.isKeyPressed(keyIds[0])) {
         // up or right
         if (horizontal) {
            xUpdate = speed;
            direction = Direction.EAST;
         } else {
            yUpdate = speed;
            direction = Direction.NORTH;
         }
      } else if (Gdx.input.isKeyPressed(keyIds[1])) {
         // down or left
         if (horizontal) {
            xUpdate = -speed;
            direction = Direction.WEST;
         } else {
            yUpdate = -speed;
            direction = Direction.SOUTH;
         }
      } else {
         xUpdate = 0;
         yUpdate = 0;
      }
      spriteUpdate.setLocation(spriteUpdate.getX() + xUpdate, spriteUpdate.getY() + yUpdate);
   }

}
