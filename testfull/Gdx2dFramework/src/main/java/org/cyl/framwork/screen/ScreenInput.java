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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;

/**
 * Title: ScreenInput
 *
 * <p>
 * Description: A wrapper around the Gdx.input static class. This class takes in account the Screen size and adjusts the
 * Vertical position for zero being on the bottom of the screen.</p>
 *
 * Copyright (c) Jun 10, 2023
 * @author Gary Deken
 * @version 0.7
 */
public class ScreenInput {
   private static int width;
   private static int height;
   private static Camera camera;

   public static final int getDeltaX() {
      return Gdx.input.getDeltaX();
   }

   public static final int getDeltaY() {
      return -Gdx.input.getDeltaY();
   }

   /**
    * Returns the input X coordinate depending on the Camera's position.
    * @return
    */
   public static final int getX() {
      return (int) camera.position.x - (width / 2) + Gdx.input.getX();
   }

   /**
    * Returns the input Y coordinate depending on the Camera's position.
    * @return
    */
   public static final int getY() {
      return (int) camera.position.y - (height / 2) + ScreenInput.height - Gdx.input.getY();
   }

   public static final void setScreenSize(int screenWidth, int screenHeight, Camera camera) {
      ScreenInput.width = screenWidth;
      ScreenInput.height = screenHeight;
      ScreenInput.camera = camera;
   }

}
