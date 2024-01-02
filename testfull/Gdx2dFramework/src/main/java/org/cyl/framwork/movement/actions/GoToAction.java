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

import org.abberkeep.gameframework.movement.ScriptMovement;
import org.abberkeep.gameframework.sprite.SpriteUpdate;

/**
 * Title: GoToAction
 *
 * <p>
 * Description: This ScriptAction make the script go to a specific action and proceed from there. The actionIndex must
 * between 1 and the number of ScriptActions in the ScriptMovement. Setting the repeat count, will repeat for the set
 * number of times.</p>
 *
 * Copyright (c) Nov 25, 2023
 * @author Gary Deken
 * @version 0.12
 */
public class GoToAction extends ScriptAction {
   private ScriptMovement parent;
   private int actionIndex;
   private int repeat = -1;

   /**
    * Creates a GoToAction to set the next index an unlimited amount of times this GoToAction is called. Every time this
    * GoToAction is called it will continue to set the index.
    * @param parent
    * @param actionIndex
    */
   public GoToAction(ScriptMovement parent, int actionIndex) {
      this.parent = parent;
      if (actionIndex < 1) {
         throw new IllegalArgumentException("ScriptAction needs to be one or greater.");
      }
      this.actionIndex = actionIndex;
   }

   /**
    * Creates a GoToAction to set the next index for the number of repeat times. Once it set the index the repeat
    * counter times, it will then be done and allow the ScriptMovement to go to the next index, if one exist.
    * @param parent
    * @param actionIndex
    * @param repeat
    */
   public GoToAction(ScriptMovement parent, int actionIndex, int repeat) {
      this.parent = parent;
      if (actionIndex < 1) {
         throw new IllegalArgumentException("ScriptAction needs to be one or greater.");
      }
      this.actionIndex = actionIndex;
      this.repeat = repeat;
   }

   @Override
   public void setup(float direction, float speed) {
      super.setup(direction, speed);
      if (repeat != 0) {
         parent.goToAction(actionIndex);
         if (repeat >= 0) {
            repeat--;
         }
      } else {
         done = true;
      }
   }

   @Override
   public void updateChild(float deltaTime, SpriteUpdate spriteUpdate) {

   }

}
