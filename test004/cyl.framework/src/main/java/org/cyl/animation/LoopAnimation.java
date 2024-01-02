package org.cyl.animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.cyl.Updatable;

public class LoopAnimation extends BaseAnimation {
   private Updatable loopUpdate;
   private float stateTime;
   private int currentIndex = 0;
   private TextureRegion[] frames;
   private float animationDuration;

   /**
    * Creates a LoopAnimation that loops continuously.
    * @param frameDuration
    * @param region
    */
   public LoopAnimation(float frameDuration, TextureRegion[] region) {
      frames = region;
      animationDuration = frameDuration * frames.length;
      width = region[0].getRegionWidth();
      height = region[0].getRegionHeight();
      originX = width / 2;
      originY = height / 2;
      loopUpdate = (deltaTime) -> {
         stateTime += deltaTime;
         currentIndex = (int) (stateTime / frameDuration);
         currentIndex = currentIndex % frames.length;
      };
   }

   /**
    * Creates a LoopAnimation that loops for a set number of times. The number of loops must be positive
    * @param frameDuration
    * @param region
    * @param numberOfLoops
    * @throws IllegalArgumentException when the numberOfLoops is zero or negative.
    */
   public LoopAnimation(float frameDuration, TextureRegion[] region, int numberOfLoops) {
      if (numberOfLoops < 1) {
         throw new IllegalArgumentException("the numberOfLoops needs to be one are greater.");
      }
      frames = region;
      animationDuration = frameDuration * frames.length;
      width = region[0].getRegionWidth();
      height = region[0].getRegionHeight();
      originX = width / 2;
      originY = height / 2;
      loopUpdate = (deltaTime) -> {
         stateTime += deltaTime;
         currentIndex = (int) (stateTime / frameDuration);
         int loopNumber = (int) (stateTime / animationDuration);
         if (loopNumber < numberOfLoops) {
            currentIndex = currentIndex % frames.length;
         } else {
            currentIndex = Math.min(frames.length - 1, currentIndex);
         }
      };
   }

   /**
    * Renders the TextureRegion to the screen based on the location provided and the parameters set by other methods.
    * @param batch
    * @param x
    * @param y
    */
   @Override
   public void draw(SpriteBatch batch, float x, float y) {
      batch.draw(frames[currentIndex], x, y, originX, originY, width, height, 1, 1, rotation);
   }

   /**
    * Updates the sequence of the images to be displayed to the screen.
    * @param deltaTime
    */
   @Override
   public void update(float deltaTime) {
      loopUpdate.update(deltaTime);
   }

}