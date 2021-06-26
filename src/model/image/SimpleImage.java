package model.image;

import model.color.Color;
import model.color.LightColor;

/**
 * The simple image class represents the most basic representation of an image, with merely a array
 * of the pixels, the width, and the height.
 */
public class SimpleImage extends AbstractImage {

  /**
   * Constructs an image given an array of pixels and a width and a height, constructs an image.
   *
   * @param pixels the array of pixels
   * @param width  the width of the image
   * @param height the height of the image
   */
  public SimpleImage(Color[] pixels, int width, int height) {
    this.pixArray = pixels;
    this.width = width;
    this.height = height;
  }

  /**
   * Copy constructor.
   *
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

  @Override
  public Image resize(int w, int h) throws IllegalArgumentException {
    return new SimpleImage(resizedRaster(w, h), w, h);
  }

  @Override
  public Image mosaic(int numSeeds) {
    return new SimpleImage(imageMosaic(numSeeds), width, height);
  }
}
