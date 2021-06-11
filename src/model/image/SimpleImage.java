package model.image;

import java.util.Objects;
import model.ColorUtils.Color;
import model.ColorUtils.LightColor;

public class SimpleImage extends AbstractImage{

  /**
   * Constructs an image given an array of pixels and a width and a height, constructs an image.
   *
   * @param pixels the array of pixels
   * @param width  the width of the image
   * @param height the height of the image
   */
  public SimpleImage(Color[] pixels, int width, int height) {
    Objects.requireNonNull(pixels);
    if (width * height != pixels.length) {
      throw new IllegalArgumentException("Width and height are invalid.");
    }
    this.pixArray = pixels;
    this.width = width;
    this.height = height;
  }

  /**
   * Copy constructor
   * @param that the image to make a copy of.
   */
  public SimpleImage(Image that) {
    super();
    if (that.getWidth() * that.getHeight() > that.pixArray().length) {
      throw new IllegalStateException("The width and height are invalid.");
    }
    this.width = that.getWidth();
    this.height = that.getHeight();
    this.pixArray = new Color[width * height];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Color pixel = that.getPixel(j, i);
        pixArray[width * i + j] = new LightColor(pixel.getRGB());
      }
    }
  }
}
