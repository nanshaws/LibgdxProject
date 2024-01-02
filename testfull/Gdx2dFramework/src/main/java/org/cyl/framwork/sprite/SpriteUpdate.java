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

/**
 * Title: SpriteUpdate
 *
 * <p>
 * Description: Interface for handling updates to the Sprite class. This is used in the Movement interface, exposing the
 * methods needed to allow the Movement classes to change the Sprite's location.</p>
 *
 * Copyright (c) Jul 1, 2023
 * @author Gary Deken
 * @version 0.8
 */
public interface SpriteUpdate {

   /**
    * Determines if this updated Sprite contains any points from the other Sprite.
    * @param other
    * @return
    */
   boolean contains(BoundingBox other);

   /**
    * Returns the Sprite's X location.
    * @return
    */
   float getX();

   /**
    * Returns the Sprite's Y location.
    * @return
    */
   float getY();

   /**
    * Sets the Sprite's X and Y location.
    * @param x
    * @param y
    */
   void setLocation(float x, float y);

   void setRemove(boolean remove);

   /**
    * Sets the Sprite's X location.
    * @param x
    */
   void setX(float x);

   /**
    * Sets the Sprite's Y location.
    * @param y
    */
   void setY(float y);

}
