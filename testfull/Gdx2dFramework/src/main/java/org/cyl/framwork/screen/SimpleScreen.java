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
package org.cyl.framwork.screen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.abberkeep.gameframework.sprite.Actor;
import org.abberkeep.gameframework.sprite.Decor;
import org.abberkeep.gameframework.sprite.Sprite;

/**
 * Title: FreestyleScreen
 *
 * <p>
 * Description: </p>
 *
 * Copyright (c) Sept 10, 2023
 *
 * @author Gary Deken
 * @version 0.11
 */
public abstract class SimpleScreen extends BaseScreen {
   private List<Sprite> sprites = new ArrayList<>();
   private boolean renderCycle = false;
   private List<Sprite> toBeAdded = new ArrayList<>();

   @Override
   public void addActor(Actor actor) {
      addSprite(actor);
   }

   @Override
   public void addDecor(Decor decor) {
      addSprite(decor);
   }

   @Override
   protected void renderChild(float deltaTime) {
      Iterator<Sprite> iter = sprites.iterator();
      renderCycle = true;

      while (iter.hasNext()) {
         Sprite sprite = iter.next();
         sprite.update(deltaTime);
         // detect collision
         if (sprite.doesImpact()) {
            detectCollision(sprite);
         }
         if (sprite.isRemove()) {
            iter.remove();
         } else {
            sprite.draw(batch);
         }
      }
      renderCycle = false;
      if (!toBeAdded.isEmpty()) {
         sprites.addAll(toBeAdded);
         toBeAdded.clear();
      }
   }

   private void addSprite(Sprite sprite) {
      if (renderCycle) {
         toBeAdded.add(sprite);
      } else {
         sprites.add(sprite);
      }
   }

   private void detectCollision(Sprite sprite) {
      for (Sprite collideSprite : sprites) {
         sprite.handleCollision(collideSprite);
      }
   }

}
