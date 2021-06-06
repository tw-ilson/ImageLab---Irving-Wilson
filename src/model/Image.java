package model;

/**
 * Represents an image as a width, a height and an array of pixels.
 * NOTE: could add fields representing effects applied such as +1 blur +2 gray
 */
public interface Image {
  
  /**
   * Gets the array of this image's pixels.
   * @return the array of this image's pixels
   * @throws IllegalStateException if pixels cannot be produced
   */
  public int[] pixArray() throws IllegalStateException;

  /**
   * gets the width of this image (in pixels).
   * @return the width of this image
   */
  public int getWidth();

  /**
   * gets the height of this image (in pixels).
   * @return the height of this image
   */
  public int getHeight();
}
