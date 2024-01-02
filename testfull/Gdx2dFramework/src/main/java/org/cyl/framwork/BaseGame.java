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
package org.cyl.framwork;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.util.HashMap;
import java.util.Map;
import org.abberkeep.gameframework.screen.BaseScreen;
import org.abberkeep.gameframework.screen.ScreenInput;

/**
 * Title: BaseGame
 *
 * <p>
 * Description: Base Game setup for 2D games.</p>
 *
 * Copyright (c) Dec 9, 2022
 * @author Gary Deken
 * @version 1
 * @since 0.1
 */
public class BaseGame extends Game {
   protected SpriteBatch batch;
   protected float height = 600;
   protected float width = 800;
   protected Viewport viewport;
   private static Map<String, Texture> textures = new HashMap<>();

   /**
    * Returns a Global Textures and will dispose of it when the Game is Disposed.
    * @param fileName
    * @return
    */
   public static Texture getGlobalTexture(String fileName) {
      return textures.computeIfAbsent(fileName, fn -> new Texture(fn));
   }

   /**
    * Creates the SpriteBatch and OrthographicCamera based on the width and height.
    */
   @Override
   public void create() {
      height = Gdx.graphics.getHeight();
      width = Gdx.graphics.getWidth();
      batch = new SpriteBatch();
      OrthographicCamera camera = new OrthographicCamera();
      camera.setToOrtho(false, width, height);
      camera.position.set(width / 2, height / 2, 0);
      camera.update();
      viewport = new ScreenViewport(camera);
      ScreenInput.setScreenSize((int) width, (int) height, camera);
   }

   /**
    * Disposes of the SpriteBatch.
    */
   @Override
   public void dispose() {
      super.dispose();
      for (Texture texture : textures.values()) {
         texture.dispose();
      }
      textures.clear();
      batch.dispose();
   }

   /**
    * Clears the screen and sets the Projection Matrix to the camera's combined.
    */
   @Override
   public void render() {
      ScreenUtils.clear(1, 0, 0, 1);
      batch.setProjectionMatrix(viewport.getCamera().combined);
      super.render();
   }

   /**
    * Resizes the screen and updates the camera.
    * @param width
    * @param height
    */
   @Override
   public void resize(int width, int height) {
      super.resize(width, height);
      if (viewport.getCamera() instanceof OrthographicCamera) {
         ((OrthographicCamera) viewport.getCamera()).setToOrtho(false, width, height);
      }
      ScreenInput.setScreenSize(width, height, viewport.getCamera());
   }

   /**
    * Sets the Screen for this Game and calls the BaseScreen's setupScreen, where it injects the SpriteBatch and
    * Viewport in to the BaseScreen passed in. The Screen passed in must inherit from BaseScreen.
    * @param screen
    */
   @Override
   public void setScreen(Screen screen) {
      ((BaseScreen) screen).setupScreen(batch, viewport);
      super.setScreen(screen);

   }

}
