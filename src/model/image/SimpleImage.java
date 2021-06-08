package model.image;

import model.ColorUtils.Color;
import model.Filters.Filter;

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

  /**
   * Copy constructor
   * @param that the image to make a copy of.
   */
  public SimpleImage(Image that) {
    super();
    this.width = that.getWidth();
    this.height = that.getHeight();
    this.pixArray = new Color[width * height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        pixArray[width * j + i] = new Color(that.getPixel(i, j));
      }
    }
  }
}
