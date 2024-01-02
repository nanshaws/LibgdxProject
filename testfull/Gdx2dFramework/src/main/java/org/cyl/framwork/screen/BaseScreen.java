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
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.util.HashMap;
import java.util.Map;
import org.abberkeep.gameframework.sprite.Actor;
import org.abberkeep.gameframework.sprite.Decor;

/**
 * Title: BaseScreen
 *
 * <p>
 * Description: The base screen used by all screens.</p>
 *
 * Copyright (c) Dec 9, 2022
 * @author Gary Deken
 * @version 1
 * @since 0.1
 */
public abstract class BaseScreen implements Screen {
   protected SpriteBatch batch;
   protected int largestSpriteWidth = 0;
   protected int largestSpriteHeight = 0;
   protected Viewport viewport;
   protected int height;
   protected int width;
   private Color bgColor;
   private Map<String, Texture> textures = new HashMap<>();
   private Map<String, Sound> sounds = new HashMap<>();
   private Map<String, Music> musics = new HashMap<>();

   /**
    * Constructor for the BaseScreen. It sets the background color to a default Black.
    */
   protected BaseScreen() {
      bgColor = new Color();
   }

   /**
    * Adds an Actor to this Screen at the location.
    * @param actor
    */
   public abstract void addActor(Actor actor);

   /**
    * Adds a Decor to this Screen at the location.
    * @param decor
    */
   public abstract void addDecor(Decor decor);

   @Override
   public void dispose() {
      for (Texture texture : textures.values()) {
         texture.dispose();
      }
      for (Sound sound : sounds.values()) {
         sound.dispose();
      }
      for (Music music : musics.values()) {
         music.dispose();
      }
      textures.clear();
      sounds.clear();
      musics.clear();
   }

   /**
    * Method to get Musics and storing them at the Screen level for disposal when the screen disposes.
    * @param fileName
    * @return
    */
   public Music getMusic(String fileName) {
      return musics.computeIfAbsent(fileName, fn -> Gdx.audio.newMusic(Gdx.files.internal(fn)));
   }

   /**
    * Method to get Sounds and storing them at the Screen level for disposal when the screen disposes.
    * @param fileName
    * @return
    */
   public Sound getSound(String fileName) {
      return sounds.computeIfAbsent(fileName, fn -> Gdx.audio.newSound(Gdx.files.internal(fn)));
   }

   /**
    * Method to get Textures and storing them at the Screen level for disposal when the screen disposes.
    * @param fileName
    * @return
    */
   public Texture getTexture(String fileName) {
      return textures.computeIfAbsent(fileName, fn -> new Texture(fn));
   }

   @Override
   public void hide() {
      //
   }

   @Override
   public void pause() {
      //
   }

   /**
    * Renders the images to the screen and setting up basic setup for all Screens. This calls the renderChild method
    * that child Screens will implement and render for the specific Screen.
    * @param deltaTime
    */
   @Override
   public void render(float deltaTime) {
      Gdx.gl.glClearColor(bgColor.r, bgColor.g, bgColor.b, bgColor.a);
      Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
      viewport.getCamera().update();
      batch.setProjectionMatrix(viewport.getCamera().combined);
      batch.begin();
      renderChild(deltaTime);
      batch.end();
   }

   /**
    * ChildScreens must implement this for the Screen's specific implementation.
    * @param deltaTime
    */
   protected abstract void renderChild(float deltaTime);

   @Override
   public void resize(int width, int height) {
      this.width = width;
      this.height = height;
   }

   @Override
   public void resume() {
      //
   }

   /**
    * Used within the BaseGame when calling setScreen. The setScreen will call this method to inject the SpriteBatch and
    * Viewport objects.
    * @param batch
    * @param viewport
    */
   public void setupScreen(SpriteBatch batch, Viewport viewport) {
      this.batch = batch;
      this.viewport = viewport;
      height = Gdx.graphics.getHeight();
      width = Gdx.graphics.getWidth();
   }

   /**
    * Set the background color for the Screen. The default is 0, 0, 0, or black.
    * @param color
    */
   public void setBackgroundColor(Color color) {
      bgColor = color;
   }

   /**
    * Set the background color for the Screen. The default is 0, 0, 0, or black.
    * @param red
    * @param green
    * @param blue
    */
   public void setBackgroundColor(float red, float green, float blue) {
      bgColor = new Color(red, green, blue, 0f);
   }

   /**
    * Set the background color for the Screen from RGB 0-255 range. The range is converted to decimal 0.0 to 1.0..
    * @param red
    * @param green
    * @param blue
    */
   public void setBackgroundColor(int red, int green, int blue) {
      bgColor = new Color(red / 255.0f, green / 255.0f, blue / 255.0f, 0f);
   }

}
