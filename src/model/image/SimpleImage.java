package model.image;

import model.ColorUtils.Color;

public class SimpleImage extends AbstractImage{

  /**
   * Constructs an image given an array of pixels and a width and a height, constructs an image.
   *
   * @param pixels the array of pixels
   * @param width  the width of the image
   * @param height the height of the image
   */
  public SimpleImage(Color[] pixels, int width, int height) {
    super(pixels, width, height);
  }
}
