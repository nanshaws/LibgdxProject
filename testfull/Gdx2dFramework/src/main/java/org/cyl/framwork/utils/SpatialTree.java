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

import java.util.AbstractSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import org.abberkeep.gameframework.sprite.Sprite;

/**
 * Title: SpatialTree
 *
 * <p>
 * Description: Encapsulates a TreeMap, that creates a key based on the Sprites Spatial location. It multiples the Y
 * coordinate by 10,000 then adds the x location. If the space is already occupied, it adds 0.01 to the x to generate a
 * new key.</p>
 *
 * Copyright (c) Sept 20, 2023
 *
 * @author Gary Deken
 * @version 0.10
 */
public class SpatialTree {
   public static final int FACTORAL = 10000;
   public static final float OFFSET = 0.01f;
   private Node root;
   private EntrySet es;
   private int size = 0;
   private int modCount = 0;
   private TreeMap<SpatialKey, Sprite> map = new TreeMap<>(Collections.reverseOrder());

   public Set<Node> entrySet() {
      if (es == null) {
         es = new EntrySet();
      }
      return es;
      // TODO
      //return map.entrySet();
   }

   /**
    * Searches through the tree by location to find the Sprite at that location.
    * @param key
    * @return Sprite
    */
   public Sprite get(SpatialKey key) {
      Node n = getNode(key);
      if (n != null) {
         return n.value;
      }
      return null;
   }

   public Iterator<SpatialKey> iterator() {
      // TODO
      return map.keySet().iterator();
   }

   /**
    * Inserts the Sprite in to the SpatialTree by creating a SpatialKey and finding its location within the Tree. If the
    * location is occupied, the SpatialKey is "increased" to be behind the Sprite at that location.
    * @param sprite
    */
   public void put(Sprite sprite) {
      SpatialKey key = new SpatialKey((int) sprite.getX(), (int) sprite.getY(), (int) sprite.getX());
      Node t = root;

      // Empty Tree set as root.
      if (root == null) {
         size = 1;
         modCount++;
         root = new Node(key, sprite, null);
         return;
      }
      // Find place in Tree and insert new Node
      Node parent;
      int cmp = 0;

      do {
         parent = t;
         cmp = key.compareTo(t.key);
         if (cmp < 0) {
            t = t.left;
         } else if (cmp > 0) {
            t = t.right;
         } else {
            // same location
            key.increaseX();
            t = t.right;
         }
      } while (t != null);

      // place Sprite in Tree
      Node nn = new Node(key, sprite, parent);
      if (cmp < 0) {
         parent.left = nn;
      } else {
         parent.right = nn;
      }
      size++;
      modCount++;
   }

   private Node getNode(SpatialKey key) {
      Node p = root;

      while (p != null) {
         int cmp = key.compareTo(p.key);

         if (cmp < 0) {
            p = p.left;
         } else if (cmp > 0) {
            p = p.right;
         } else {
            return p;
         }
      }
      return null;
   }

   private void put(SpatialKey key, Sprite sprite) {
      // TODO
      if (map.containsKey(key)) {
         key.increaseX();
         put(key, sprite);
      }
      map.put(key, sprite);
   }

   class EntrySet extends AbstractSet<Node> {
      public Iterator<Node> iterator() {
         return null;
//         return new EntryIterator(getFirstEntry());
      }

//      public boolean contains(Object o) {
//         if (!(o instanceof Map.Entry)) {
//            return false;
//         }
//         Map.Entry<?, ?> entry = (Map.Entry<?, ?>) o;
//         Object value = entry.getValue();
//         Entry<K, V> p = getEntry(entry.getKey());
//         return p != null && valEquals(p.getValue(), value);
//      }
      public boolean remove(SpatialKey spk) {
//         if (!(o instanceof Map.Entry)) {
//            return false;
//         }
//         Map.Entry<?, ?> entry = (Map.Entry<?, ?>) o;
//         Object value = entry.getValue();
//         Entry<K, V> p = getEntry(entry.getKey());
//         if (p != null && valEquals(p.getValue(), value)) {
//            deleteEntry(p);
//            return true;
//         }
         return false;
      }

      public int size() {
         return size;
      }

   }

   static final class Node {
      SpatialKey key;
      Sprite value;
      Node left;
      Node right;
      Node parent;

      public Node(SpatialKey key, Sprite value, Node parent) {
         this.key = key;
         this.value = value;
         this.parent = parent;
      }

      public SpatialKey getKey() {
         return key;
      }

      public Node getLeft() {
         return left;
      }

      public Node getParent() {
         return parent;
      }

      public Node getRight() {
         return right;
      }

      public Sprite getValue() {
         return value;
      }

   }

}
