package model.color;

/**
 * represents a color with a red, green, and blue channel.
 */
public interface Color {

  /**
   * gets the red channel of this color.
   * @return the red value.
   */
  public int getRed();

  /**
   * gets the green channel of this color.
   * @return the green value.
   */
  public int getGreen();

  /**
   * gets the blue value of this color.
   * @return the blue value.
   */
  public int getBlue();

  /**
   * gets the full rgb code of this color.
   * @return the rgb code.
   */
  public int getRGB();
}
