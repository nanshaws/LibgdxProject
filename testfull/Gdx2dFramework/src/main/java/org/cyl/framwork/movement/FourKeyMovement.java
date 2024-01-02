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
 * Title: FourKeyMovement
 *
 * <p>
 * Description: Encapsulates getting the input from four keys and determines the direction and the amount of movement
 * for the update. This uses the four cardinal directions (North, South, East and West).</p>
 *
 * Copyright (c) Jun 22, 2023
 *
 * @author Gary Deken
 * @version 0.7
 */
public class FourKeyMovement extends BaseMovement {
   private int[] keyIds = new int[4];
   private boolean multi = false;

   /**
    * Constructs a FourKeyMovement, with the key IDs and the speed. The key IDs are used to move in that direction.
    *
    * @param keyUpId
    * @param keyDownId
    * @param keyRightId
    * @param keyLeftId
    * @param speed
    */
   public FourKeyMovement(int keyUpId, int keyDownId, int keyRightId, int keyLeftId, float speed) {
      this(keyUpId, keyDownId, keyRightId, keyLeftId, speed, false);
   }

   /**
    * Constructs a FourKeyMovement, with the key IDs and the speed. With the multi set to true, it allows for pressing
    * two non-conflicting keys at the same time to get a direction. The Right and Left keys can be pressed with either
    * Up or Down, but pressing Up and Down or Left and Right, will only go Up or Right.
    *
    * @param keyUpId
    * @param keyDownId
    * @param keyRightId
    * @param keyLeftId
    * @param speed
    * @param multi
    */
   public FourKeyMovement(int keyUpId, int keyDownId, int keyRightId, int keyLeftId, float speed, boolean multi) {
      keyIds[0] = keyUpId;
      keyIds[1] = keyDownId;
      keyIds[2] = keyRightId;
      keyIds[3] = keyLeftId;
      this.speed = speed;
      this.multi = multi;
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
      if (multi) {
         multipleUpdateDirection();
      } else {
         simpleUpdateDirection();
      }
      spriteUpdate.setLocation(spriteUpdate.getX() + xUpdate, spriteUpdate.getY() + yUpdate);
   }

   private void simpleUpdateDirection() {
      if (Gdx.input.isKeyPressed(keyIds[0])) {
         yUpdate = speed;
         xUpdate = 0;
         direction = Direction.NORTH;
      } else if (Gdx.input.isKeyPressed(keyIds[1])) {
         yUpdate = -speed;
         xUpdate = 0;
         direction = Direction.SOUTH;
      } else if (Gdx.input.isKeyPressed(keyIds[2])) {
         xUpdate = speed;
         yUpdate = 0;
         direction = Direction.EAST;
      } else if (Gdx.input.isKeyPressed(keyIds[3])) {
         xUpdate = -speed;
         yUpdate = 0;
         direction = Direction.WEST;
      } else {
         xUpdate = 0;
         yUpdate = 0;
      }
   }

   private void multipleUpdateDirection() {
      if (Gdx.input.isKeyPressed(keyIds[0]) && Gdx.input.isKeyPressed(keyIds[2])) {
         calculateMagnitudesByDirection(Direction.NORTH_EAST, speed);
      } else if (Gdx.input.isKeyPressed(keyIds[0]) && Gdx.input.isKeyPressed(keyIds[3])) {
         calculateMagnitudesByDirection(Direction.NORTH_WEST, speed);
      } else if (Gdx.input.isKeyPressed(keyIds[0])) {
         yUpdate = speed;
         xUpdate = 0;
         direction = Direction.NORTH;
      } else if (Gdx.input.isKeyPressed(keyIds[1]) && Gdx.input.isKeyPressed(keyIds[2])) {
         calculateMagnitudesByDirection(Direction.SOUTH_EAST, speed);
      } else if (Gdx.input.isKeyPressed(keyIds[1]) && Gdx.input.isKeyPressed(keyIds[3])) {
         calculateMagnitudesByDirection(Direction.SOUTH_WEST, speed);
      } else if (Gdx.input.isKeyPressed(keyIds[1])) {
         yUpdate = -speed;
         xUpdate = 0;
         direction = Direction.SOUTH;
      } else if (Gdx.input.isKeyPressed(keyIds[2])) {
         xUpdate = speed;
         yUpdate = 0;
         direction = Direction.EAST;
      } else if (Gdx.input.isKeyPressed(keyIds[3])) {
         xUpdate = -speed;
         yUpdate = 0;
         direction = Direction.WEST;
      } else {
         xUpdate = 0;
         yUpdate = 0;
      }
   }

}
