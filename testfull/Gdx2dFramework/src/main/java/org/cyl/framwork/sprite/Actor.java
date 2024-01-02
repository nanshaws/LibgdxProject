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
package org.cyl.framwork.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.abberkeep.gameframework.motion.Motion;
import org.abberkeep.gameframework.movement.Movement;

/**
 * Title: Actor
 *
 * <p>
 * Description: Defines a Sprite that moves on the screen.</p>
 *
 * Copyright (c) Aug 6, 2023
 *
 * @author Gary Deken
 * @version 0.9
 */
public class Actor extends Sprite {
   protected Movement movement;
   protected Motion[] moveMotion;
   protected int currentMoveMotion = 0;
   protected Motion[] stillMotion;
   protected int currentStillMotion = 0;

   /**
    * Creates an Actor with the Movement, move Motion and still Motion passed in. The width and height are based on the
    * first still Motion.
    *
    * @param movement
    * @param moveMotion
    * @param stillMotion
    */
   public Actor(Movement movement, Motion moveMotion, Motion stillMotion) {
      super(stillMotion.getWidth(), stillMotion.getHeight());
      this.movement = movement;
      this.moveMotion = new Motion[]{moveMotion};
      this.stillMotion = new Motion[]{stillMotion};
   }

   /**
    * Creates an Actor with the Movement, move Motion and still Motion passed in. The width and height are based on the
    * first still Motion.
    *
    * @param movement
    * @param moveMotion
    * @param stillMotion
    */
   public Actor(Movement movement, Motion[] moveMotion, Motion[] stillMotion) {
      super(stillMotion[0].getWidth(), stillMotion[0].getHeight());
      this.movement = movement;
      this.moveMotion = moveMotion;
      this.stillMotion = stillMotion;
   }

   @Override
   public boolean doesImpact() {
      return true;
   }

   @Override
   public void draw(SpriteBatch batch) {
      if (getSpeed() == 0.0f) {
         stillMotion[currentStillMotion].draw(batch, x, y);
      } else {
         moveMotion[currentMoveMotion].draw(batch, x, y);
      }
   }

   public Motion[] getMoveMotions() {
      return moveMotion;
   }

   public Motion[] getStillMotions() {
      return stillMotion;
   }

   public float getSpeed() {
      return movement.getCurrentSpeed();
   }

   @Override
   public void handleCollision(Sprite other) {
      // move back by default
      if (contains(other)) {
         movement.handleCollision(this, other.bounds);
      }
   }

   @Override
   public void update(float deltaTime) {
      movement.update(deltaTime, this);
      if (getSpeed() == 0.0f) {
         stillMotion[currentStillMotion].update(deltaTime, movement.getDirection());
      } else {
         moveMotion[currentMoveMotion].update(deltaTime, movement.getDirection());
         stillMotion[currentStillMotion].setDirection(movement.getDirection());
      }
   }

}
