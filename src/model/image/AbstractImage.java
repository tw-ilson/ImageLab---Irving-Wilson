package model.image;


import model.ColorUtils.Color;


/**
 * Abstract class solidifies general properties of Image implementations.
 */
public abstract class AbstractImage implements Image {

  protected int width;
  protected int height;
  protected Color[] pixArray;

  /**
   * Constructs an image given an array of pixels and a width and a height, constructs an image.
   *
   * @param pixels the array of pixels
   * @param width  the width of the image
   * @param height the height of the image
   */
  public AbstractImage(Color[] pixels, int width, int height) {
    this.pixArray = pixels;
    this.width = width;
    this.height = height;
  }

  /**
   * empty default constructor.
   */
  public AbstractImage() {
    super();
  }

  @Override
  public model.ColorUtils.Color[] pixArray() throws IllegalStateException {
    return pixArray.clone();
  }

  @Override
  public Color getPixel(int x, int y) {
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
