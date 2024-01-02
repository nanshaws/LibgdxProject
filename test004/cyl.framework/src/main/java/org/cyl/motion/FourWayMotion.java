package org.cyl.motion;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.cyl.animation.Animation;
import org.cyl.animation.LoopAnimation;
import org.cyl.movement.Direction;

public class FourWayMotion implements Motion {
   private final static int NORTH = 0;
   private final static int EAST = 1;
   private final static int SOUTH = 2;
   private final static int WEST = 3;
   private Animation[] animations = new Animation[4];
   private int index;

   /**
    * Creates a TwoWayMotion to control the animation. The animation changes as the direction changes.
    * @param north
    * @param east
    * @param south
    * @param west
    */
   public FourWayMotion(Animation north, Animation east, Animation south, Animation west) {
      animations[NORTH] = north;
      animations[EAST] = east;
      animations[SOUTH] = south;
      animations[WEST] = west;
   }

   /**
    * Creates a TwoWayMotion to control the animation. The animation changes as the direction changes. The Texture is
    * split into a TextureRegion and made into a LoopAnimation, where the elements are expected to be arranged where the
    * first row is the character moving North (or up), second is East (or right), third is South (or down) and the
    * fourth is West (or left).
    * @param texture
    * @param tileWidth
    * @param tileHeight
    * @param duration
    */
   public FourWayMotion(Texture texture, int tileWidth, int tileHeight, float duration) {
      TextureRegion[][] textureRegions = TextureRegion.split(texture, tileWidth, tileHeight);
      animations[NORTH] = new LoopAnimation(duration, textureRegions[0]);
      animations[EAST] = new LoopAnimation(duration, textureRegions[1]);
      animations[SOUTH] = new LoopAnimation(duration, textureRegions[2]);
      animations[WEST] = new LoopAnimation(duration, textureRegions[3]);
   }

   /**
    * Creates a TwoWayMotion to control the animation. The animation changes as the direction changes. The Texture is
    * split into a TextureRegion and made into a LoopAnimation, where the elements are arranged where the north row is
    * the character moving North (or up), east row is East (or right), the south row is South (or down) and the west row
    * is West (or left).
    * @param texture
    * @param tileWidth
    * @param tileHeight
    * @param duration
    * @param north
    * @param east
    * @param south
    * @param west
    */
   public FourWayMotion(Texture texture, int tileWidth, int tileHeight, float duration, int north, int east, int south,
      int west) {
      TextureRegion[][] textureRegions = TextureRegion.split(texture, tileWidth, tileHeight);
      animations[NORTH] = new LoopAnimation(duration, textureRegions[north]);
      animations[EAST] = new LoopAnimation(duration, textureRegions[east]);
      animations[SOUTH] = new LoopAnimation(duration, textureRegions[south]);
      animations[WEST] = new LoopAnimation(duration, textureRegions[west]);
   }

   @Override
   public void draw(SpriteBatch batch, float x, float y) {
      animations[index].draw(batch, x, y);
   }

   @Override
   public void update(float deltaTime, float direction) {
      direction = Direction.nearest4thDirection(direction);
      if (direction == Direction.NORTH) {
         index = NORTH;
      } else if (direction == Direction.EAST) {
         index = EAST;
      } else if (direction == Direction.SOUTH) {
         index = SOUTH;
      } else {
         index = WEST;
      }
      animations[index].update(deltaTime);
   }

}