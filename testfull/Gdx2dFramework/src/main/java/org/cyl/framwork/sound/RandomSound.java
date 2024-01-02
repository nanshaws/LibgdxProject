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
package org.cyl.framwork.sound;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Title: RandomSound
 *
 * <p>
 * Description: Holds multiple Sound objects, and plays them randomly.</p>
 *
 * Copyright (c) Nov 3, 2023
 * @author Gary Deken
 * @version 0.11
 */
public class RandomSound implements Sound {
   private List<Sound> sounds = new ArrayList<>();
   private Sound currentSound;

   public RandomSound(Sound sound) {
      sounds.add(sound);
   }

   public void addSound(Sound sound) {
      sounds.add(sound);
   }

   /**
    * Disposes of all the sounds in the RandomSound
    */
   @Override
   public void dispose() {
      sounds.forEach(sound -> sound.dispose());
   }

   @Override
   public long loop() {
      getNextSound();
      return currentSound.loop();
   }

   @Override
   public long loop(float volume) {
      getNextSound();
      return currentSound.loop(volume);
   }

   @Override
   public long loop(float volume, float pitch, float pan) {
      getNextSound();
      return currentSound.loop(volume, pitch, pan);
   }

   @Override
   public void pause() {
      currentSound.pause();
   }

   @Override
   public void pause(long soundId) {
      currentSound.pause(soundId);
   }

   @Override
   public long play() {
      getNextSound();
      return currentSound.play();
   }

   @Override
   public long play(float volume) {
      getNextSound();
      return currentSound.play(volume);
   }

   @Override
   public long play(float volume, float pitch, float pan) {
      getNextSound();
      return currentSound.play(volume, pitch, pan);
   }

   @Override
   public void resume() {
      currentSound.resume();
   }

   @Override
   public void resume(long soundId) {
      currentSound.resume(soundId);
   }

   @Override
   public void setLooping(long soundId, boolean foreverLooping) {
      currentSound.setLooping(soundId, foreverLooping);
   }

   @Override
   public void setPan(long soundId, float pan, float volume) {
      currentSound.setPan(soundId, pan, volume);
   }

   @Override
   public void setPitch(long soundId, float pitch) {
      currentSound.setPitch(soundId, pitch);
   }

   @Override
   public void setVolume(long soundId, float volume) {
      currentSound.setVolume(soundId, volume);
   }

   @Override
   public void stop() {
      currentSound.stop();
   }

   @Override
   public void stop(long soundId) {
      currentSound.stop(soundId);
   }

   private void getNextSound() {
      currentSound = sounds.get(MathUtils.random(sounds.size() - 1));
   }

}
