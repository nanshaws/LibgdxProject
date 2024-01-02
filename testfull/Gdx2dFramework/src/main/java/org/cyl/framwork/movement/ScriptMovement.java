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
package org.cyl.framwork.movement;

import java.util.ArrayList;
import java.util.List;
import org.abberkeep.gameframework.movement.actions.ScriptAction;
import org.abberkeep.gameframework.movement.actions.WaitAction;
import org.abberkeep.gameframework.sprite.BoundingBox;
import org.abberkeep.gameframework.sprite.SpriteUpdate;

/**
 * Title: ScriptMovement
 *
 * <p>
 * <p>
 * Description: The ScriptSimpleMovement takes a collection of ScriptActions and will run each action to the end.
 * Multiple actions can be added, and once the ScriptSimpleMovement completes all the actions, it will stop the Actor.
 * The Actor's movement can be restarted by adding a new Action, at which point it will run that action.</p>
 *
 * Copyright (c) Nov 18, 2023
 * @author Gary Deken
 * @version 0.12
 */
public class ScriptMovement implements Movement {
   private List<ScriptAction> actions = new ArrayList<>();
   private int currentIndex = 0;
   private ScriptAction currentAction;
   private boolean done = false;
   private WaitAction waitAction = new WaitAction(0f);

   /**
    * Create a new ScriptMovement with the given ScriptAction. Sets up the ScriptMovement with an initial ScriptAction
    * that will setup the intended first ScriptAction to be set up correctly.
    * @param action
    */
   public ScriptMovement(ScriptAction action) {
      actions.add(waitAction);
      currentAction = waitAction;
      // add the intended first ScriptAction
      actions.add(action);
   }

   /**
    * Adds an ScriptAction to this ScriptMovement. If the ScriptMovement is marked as done, adding a new action will
    * cause that action to be executed on, and marking the ScriptMovement as not done.
    * @param action
    */
   public void addAction(ScriptAction action) {
      actions.add(action);
      if (done) {
         currentAction = action;
         currentIndex = actions.size() - 1;
         done = false;
      }
   }

   @Override
   public float getCurrentSpeed() {
      if (done) {
         return 0f;
      }
      return currentAction.getSpeed();
   }

   @Override
   public float getDirection() {
      return currentAction.getDirection();
   }

   /**
    * Sets the newIndex as the currentIndex and sets up that action. If the index is greater then the size of the array
    * an IllegalArgumentException will be thrown. If the newIndex is set to zero or outside the number of ScriptActions,
    * this will throw an IllegalArgumentException.
    * @param newIndex
    */
   public void goToAction(int newIndex) {
      if (newIndex >= actions.size() || newIndex < 1) {
         throw new IllegalArgumentException(
            "New ScriptAction is not a valid index. Expected: " + (actions.size() - 1) + ", Actual: " + newIndex);
      }
      currentIndex = newIndex;
      float direction = currentAction.getDirection();
      float speed = currentAction.getSpeed();

      currentAction = actions.get(currentIndex);
      currentAction.setup(direction, speed);
   }

   @Override
   public void handleCollision(SpriteUpdate spriteUpdate, BoundingBox other) {
      // stop moving
      spriteUpdate.setLocation(spriteUpdate.getX() - currentAction.getXUpdate(),
         spriteUpdate.getY() - currentAction.getYUpdate());
   }

   /**
    * Return true if all ScriptActions are Done.
    * @return boolean
    */
   public boolean isDone() {
      return done;
   }

   public void nextAction() {
      gotoNextAction();
   }

   /**
    * Set the Speed of the Script. It is best practice to set the speed within each ScriptAction and not via the
    * ScriptMovement. This method is create to comply with the Movement interface.
    * @param speed
    */
   @Override
   public void setSpeed(float speed) {
      currentAction.setSpeed(speed);
   }

   /**
    * The implementation in this method, the update is delegated to the current ScriptAction, which the ScriptAction
    * will call calculateMagnitues and return to this class to call the SpriteUpate's setLocation().
    * @param deltaTime
    * @param spriteUpdate
    */
   @Override
   public void update(float deltaTime, SpriteUpdate spriteUpdate) {
      if (!done) {
         if (currentAction.isDone()) {
            gotoNextAction();
         }
         currentAction.update(deltaTime, spriteUpdate);
         if (currentAction.isDone() && currentIndex == actions.size() - 1) {
            done = true;
         }
      }
   }

   private void gotoNextAction() {
      float direction = currentAction.getDirection();
      float speed = currentAction.getSpeed();
      currentIndex++;
      if (currentIndex < actions.size()) {
         currentAction = actions.get(currentIndex);
         currentAction.setup(direction, speed);
      } else {
         done = true;
         currentAction = waitAction;
         currentAction.setup(direction, speed);
      }
   }

}
