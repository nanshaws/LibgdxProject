package org.cyl.animation;
public abstract class BaseAnimation implements Animation {
   protected float width;
   protected float height;
   protected float originX;
   protected float originY;
   protected float rotation;

   @Override
   public float getHeight() {
      return height;
   }

   @Override
   public float getWidth() {
      return width;
   }

   /**
    * Rotates the Animation based on the center of the image. As the image rotates the corners will break the boundaries
    * of the original Texture,
    * @param rotation
    */
   public void setRotation(float rotation) {
      this.rotation = rotation;
   }

   /**
    * Sets the size of the Animation to the size that it will be draw as. This resizes the image. This will also set the
    * rotation origin to the center of the image.
    * @param width
    * @param height
    */
   public void setSize(float width, float height) {
      this.width = width;
      this.height = height;
      this.originX = width / 2;
      this.originY = height / 2;
   }

}