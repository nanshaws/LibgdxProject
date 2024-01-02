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
package org.cyl.framwork.utils;

/**
 * Title: SpatialKey
 *
 * <p>
 * Description: The SpatialKey used in the SpatialTree for setting X and Y values within the TreeMap.
 *
 * Copyright (c) Sep 21, 2023
 *
 * @author Gary Deken
 * @version 0.10
 */
public class SpatialKey implements Comparable<SpatialKey> {
   private int x;
   private int y;
   private float xf;
   private float key;

   public SpatialKey(int x, int y, float xf) {
      this.x = x;
      this.y = y;
      this.xf = xf;
      key = (y * SpatialTree.FACTORAL) + xf;
   }

   @Override
   public int compareTo(SpatialKey other) {
      return Float.compare(key, other.getKey());
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof SpatialKey) {
         SpatialKey spk = (SpatialKey) obj;
         return key == spk.key;
      }
      return false;
   }

   public float getKey() {
      return key;
   }

   public int getX() {
      return (int) x;
   }

   public float getXf() {
      return xf;
   }

   public int getY() {
      return y;
   }

   @Override
   public int hashCode() {
      return Float.hashCode(key);
   }

   public void increaseX() {
      xf += SpatialTree.OFFSET;
      key = (y * SpatialTree.FACTORAL) + xf;
   }

   public void setX(int x) {
      this.x = x;
   }

   public void setXf(float xf) {
      this.xf = xf;
   }

   public void setY(int y) {
      this.y = y;
   }

   @Override
   public String toString() {
      return "Y: " + y + " X: " + xf;
   }

}
