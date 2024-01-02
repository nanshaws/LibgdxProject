package org.cyl;

public interface Updatable {

   /**
    * This method takes the elapse time between rendered frames.
    *
    * @param deltaTime float
    */
   void update(float deltaTime);
}