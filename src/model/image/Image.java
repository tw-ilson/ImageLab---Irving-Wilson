package model.image;


import model.color.Color;

/**
 * Represents an image as a width, a height and an array of pixels. NOTE: could add fields
 * representing effects applied such as +1 blur +2 gray
 */
public interface Image {

  /**
   * Gets the array of this image's pixels.
   *
   * @return the array of this image's pixels
   * @throws IllegalStateException if pixels cannot be produced
   */
  public Color[] pixArray() throws IllegalStateException;

  /**
   * Gets the pixel at the specific coordinate in the image array.
   *
   * @param x the horizontal coordinate (0 <= x < width)
   * @param y the vertical coordinate (0 <= y < height)
   * @return the integer representation of the color requested
   */
  public Color getPixel(int x, int y);


  /**
   * gets the width of this image (in pixels).
   *
   * @return the width of this image
   */
  public int getWidth();

  /**
   * gets the height of this image (in pixels).
   *
   * @return the height of this image
   */
  public int getHeight();

  /**
   * resizes this Image to the requested dimensions and returns the result.
   *
   * @param w the requested width to resize to.
   * @param h the requested height to resize to.
   * @throws IllegalArgumentException if the dimensions requested are not valid.
   */
  Image resize(int w, int h) throws IllegalArgumentException;


  /**
   * alters the Image to rebuild it in a Mosaic format, which means selecting a number of seeds
   * based on user input, changing the pixels which lie closest to that seed to the color of the
   * seed. This creates the effect of a mosaic window pane, as one would see in a Church for
   * instance.
   *
   * @param numSeeds (the number of seeds which the user wishes to spread the pixels over)
   * @return Image (the newly altered Image).
   * @throws IllegalStateException (if the integer is invalid, or the current layer has no image
   *                               instantiated).
   */
  Image mosaic(int numSeeds) throws IllegalStateException;
}
