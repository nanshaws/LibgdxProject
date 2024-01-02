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

import org.abberkeep.gameframework.sprite.BoundingBox;
import org.abberkeep.gameframework.sprite.SpriteUpdate;

/**
 * Title: Movement
 *
 * <p>
 * Description: This interface is for implementing a movement control system, either through the Keyboard, mouse or
 * other device.</p>
 *
 * Copyright (c) Jun 22, 2023
 *
 * @author Gary Deken
 * @version 0.7
 */
public interface Movement {

   /**
    * returns the direction of the movement in degrees (0 to 360)
    *
    * @return
    */
   float getDirection();

   /**
    * Returns the current speed of the Movement.
    *
    * @return speed
    */
   float getCurrentSpeed();

   /**
    * Handle the collision between the Sprite and a BoundBox.
    *
    * @param spriteUpdate
    * @param other
    */
   void handleCollision(SpriteUpdate spriteUpdate, BoundingBox other);

   /**
    * This method takes the delta time since the last update and a SpriteUpdate. It determines the movement since the
    * last update and allows setting the update amounts to the SpriteUpdate. The amount of the movement is also returned
    * through the getXUpdate() and getYUpdate().
    *
    * @param deltaTime float
    * @param spriteUpdate
    */
   void update(float deltaTime, SpriteUpdate spriteUpdate);

   /**
    * Set the speed, as in pixels per second.
    *
    * @param speed
    */
   void setSpeed(float speed);

}
