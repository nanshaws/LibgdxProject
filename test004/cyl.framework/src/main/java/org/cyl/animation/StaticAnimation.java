package org.cyl.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StaticAnimation extends BaseAnimation {
   private Texture texture;
   private float originX;
   private float originY;
   private float rotation;
   private int xStart = 0;
   private int xSrcWidth;
   private int yStart = 0;
   private int ySrcHeight;
   private boolean flipHorizontal = false;
   private boolean flipVertical = false;

   /**
    * Constructs a StaticAnimation based on the images size.
    * @param texture
    */
   public StaticAnimation(Texture texture) {
      this.texture = texture;
      xSrcWidth = texture.getWidth();
      ySrcHeight = texture.getHeight();
      width = texture.getWidth();
      height = texture.getHeight();
   }

   /**
    * Constructs a StaticAnimation and resizing it.
    * @param texture
    * @param width
    * @param height
    */
   public StaticAnimation(Texture texture, float width, float height) {
      this.texture = texture;
      xSrcWidth = texture.getWidth();
      ySrcHeight = texture.getHeight();
      this.width = width;
      this.height = height;
   }

   @Override
   public void draw(SpriteBatch batch, float x, float y) {
      //originX要旋转的x，originY和要旋转的y
      // xStart,yStart要裁剪的x开始，要裁剪y的开始，, xSrcWidth, ySrcHeight要裁剪的x大小，要裁剪y的大小,flipHorizontal, flipVertical 是否y反转，是否x反转
      batch.draw(texture, x, y, originX, originY, width, height, 1, 1, rotation, xStart, yStart, xSrcWidth, ySrcHeight,
         flipHorizontal, flipVertical);
   }

   /**
    * StaticAnimations have no update.
    * @param deltaTime
    */
   @Override
   public void update(float deltaTime) {
      // no update.
   }

   public void setCropping(int xStart, int yStart, int xSrcWidth, int ySrcHeight) {
      this.xStart = xStart;
      this.yStart = yStart;
      this.xSrcWidth = xSrcWidth;
      this.ySrcHeight = ySrcHeight;
      width = xSrcWidth;
      height = ySrcHeight;
      if (rotation != 0) {
         this.originX = width / 2;
         this.originY = height / 2;
      }
   }

   public void setFlipHorizontal(boolean flipHorizontal) {
      this.flipHorizontal = flipHorizontal;
   }

   public void setFlipVertical(boolean flipVertical) {
      this.flipVertical = flipVertical;
   }

   public void setRotation(float rotation) {
      this.rotation = rotation;
      this.originX = width / 2;
      this.originY = height / 2;
   }

}