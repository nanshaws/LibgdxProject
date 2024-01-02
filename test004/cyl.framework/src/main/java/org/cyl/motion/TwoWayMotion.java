package org.cyl.motion;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.cyl.animation.Animation;
import org.cyl.animation.LoopAnimation;
import org.cyl.movement.Direction;

public class TwoWayMotion implements Motion {
   private Animation[] animation = new Animation[2];
   private int direction = 0;
   private boolean horizontal = true;

   /**
    * Creates a TwoWayMotion to control the animation. The animation changes as the direction changes. If the direction
    * is vertical (directionOne is up, directionTwo is down) or horizontal (directionOne is right, directionTwo is
    * left).
    *
    * @param directionOne
    * @param directionTwo
    */
   public TwoWayMotion(Animation directionOne, Animation directionTwo) {
      animation[0] = directionOne;
      animation[1] = directionTwo;
   }

   /**
    * Creates a TwoWayMotion to control the animation. The animation changes as the direction changes. The Texture is
    * split into a TextureRegion and made into LoopAnimations where the first TextureRegion array is Right or Up, and
    * the second TextureRegion array is Left or Down.
    * @param texture
    * @param tileWidth
    * @param tileHeight
    * @param duration
    */
   public TwoWayMotion(Texture texture, int tileWidth, int tileHeight, float duration) {
      TextureRegion[][] textureRegions = TextureRegion.split(texture, tileWidth, tileHeight);
      animation[0] = new LoopAnimation(duration, textureRegions[0]);
      animation[1] = new LoopAnimation(duration, textureRegions[1]);
   }

   /**
    * Creates a TwoWayMotion to control the animation. The animation changes as the direction changes. The Texture is
    * split into a TextureRegion and made into a LoopAnimation where the direction0 of the TextureRegion array is Right
    * or Up, and the direction1 of the TextureRegion array is Left or Down.
    * @param texture
    * @param tileWidth
    * @param tileHeight
    * @param duration
    * @param direction0
    * @param direction1
    */
   public TwoWayMotion(Texture texture, int tileWidth, int tileHeight, float duration, int direction0, int direction1) {
      TextureRegion[][] textureRegions = TextureRegion.split(texture, tileWidth, tileHeight);
      animation[0] = new LoopAnimation(duration, textureRegions[direction0]);
      animation[1] = new LoopAnimation(duration, textureRegions[direction1]);
   }

   /**
    * Draws the Animation based on the direction determined by the update method.
    * @param batch
    * @param x
    * @param y
    */
   @Override
   public void draw(SpriteBatch batch, float x, float y) {
      animation[direction].draw(batch, x, y);
   }

   /**
    * Set the direction of this Motion to be horizontal (East and West) or vertical (North and South).
    * @param horizontal
    */
   public void setHorizontal(boolean horizontal) {
      this.horizontal = horizontal;
   }

   /**
    * Updates the Animation based on the direction passed in. It will check if the Motion is vertical or horizontal.
    * @param deltaTime
    * @param direction
    */
   @Override
   public void update(float deltaTime, float direction) {
      direction = Direction.convertTo360Degrees(direction);
      this.direction = 1;
      if (horizontal) {
         if (direction <= Direction.NORTH || direction > Direction.SOUTH) {
            this.direction = 0;
         }
      } else {
         if (direction >= Direction.EAST && direction < Direction.WEST) {
            this.direction = 0;
         }
      }
      animation[this.direction].update(deltaTime);
   }

}