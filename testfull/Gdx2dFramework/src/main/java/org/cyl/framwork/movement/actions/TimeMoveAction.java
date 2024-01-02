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
 * Title: TimeMoveAction
 *
 * <p>
 * Description: This Action makes the Actor move in a given startDirection for the given time in seconds.</p>
 *
 * Copyright (c) Nov 15, 2023
 * @author Gary Deken
 * @version 0.12
 */
public class TimeMoveAction extends ScriptAction {

   /**
    * Creates a ScriptAction where the Actor will move for a specified Time and direction.
    * @param direction
    * @param speed
    * @param time
    */
   public TimeMoveAction(float direction, float speed, float time) {
      this.direction = Direction.convertTo360Degrees(direction);
      this.speed = speed;
      this.duration = time;
   }

   /**
    * Creates a ScriptAction where the Actor will move for a specified Time and direction. This action will change
    * direction gradually, in an arc.
    * @param direction
    * @param speed
    * @param time
    * @param changeGradually
    */
   public TimeMoveAction(float direction, float speed, float time, boolean changeGradually) {
      this.direction = Direction.convertTo360Degrees(direction);
      this.speed = speed;
      this.duration = time;
      this.changeDirectionGradually = changeGradually;
   }

   @Override
   public void updateChild(float deltaTime, SpriteUpdate spriteUpdate) {
      calculateMagnitudesByDirection(getUpdatedDirection(deltaTime), speed);
      spriteUpdate.setLocation(spriteUpdate.getX() + xUpdate, spriteUpdate.getY() + yUpdate);
      if (currentTime >= duration) {
         done = true;
      }
   }

}
