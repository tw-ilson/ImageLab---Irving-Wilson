package model;

/**
 * Abstract class solidifies general properties of Image implementations.
 */
public abstract class AbstractImage implements Image {

  protected int width;
  protected int height;
  protected int[] pixArray;

  /**
   * Constructs an image given an array of pixels and a width and a height, constructs an image.
   *
   * @param pixels the array of pixels
   * @param width  the width of the image
   * @param height the height of the image
   */
  public AbstractImage(int[] pixels, int width, int height) {
    this.pixArray = pixels;
    this.width = width;
    this.height = height;
  }

  @Override
  public int[] pixArray() throws IllegalStateException {
    return pixArray.clone();
  }

  @Override
  public int getPixel(int x, int y) {
    return pixArray[width * y + x];
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }
}
