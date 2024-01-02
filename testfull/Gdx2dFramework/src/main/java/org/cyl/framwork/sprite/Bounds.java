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
 * Title: Bounds
 *
 * <p>
 * Description: Allows for setting the X and Y inset and the Width and Height of the bounds for a Sprite.</p>
 *
 * Copyright (c) Nov 3, 2023
 * @author Gary Deken
 * @version 0.11
 */
public interface Bounds {

   /**
    * Set the height of a Sprite's BoundingBox. This BoundingBox's bounds are effected by the Y Inset, Adding a Y Inset
    * and not changing the height will cause the Sprite to collide incorrectly.
    * @param height
    */
   void setHeight(int height);

   /**
    * Set the width of a Sprite's BoundingBox. This BoundingBox's bounds are effected by the X Inset, Adding an X Inset
    * and not changing the width will cause the Sprite to collide incorrectly.
    * @param width
    */
   void setWidth(int width);

   /**
    * Set the xInset of a Sprite's BoundingBox from the Left. This BoundingBox's bounds are effected by the X Inset,
    * Adding a X Inset and not changing the width will cause the Sprite to collide incorrectly.
    * @param xInset
    */
   void setXInset(int xInset);

   /**
    * Set the yInset of a Sprite's BoundingBox from the top. This BoundingBox's bounds are effected by the Y Inset,
    * Adding a Y Inset and not changing the height will cause the Sprite to collide incorrectly.
    * @param yInset
    */
   void setYInset(int yInset);

}
