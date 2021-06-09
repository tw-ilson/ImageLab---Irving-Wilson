package model.ColorUtils;

public class Color {
  private int color;

  /**
   * Takes a hexcode integer as color.
   * @param color the color code to use as this color.
   */
  public Color(int color) {
    this.color = color;
  }

  /**
   * Copy constructor.
   * @param that
   */
  public Color(Color that) { this.color = that.color; }

  /**
   * Takes red, blue, and green channels to construct this color (0-255).
   * @param r the red channel.
   * @param g the blue channel.
   * @param b the green channel.
   */
  public Color(int r, int g, int b) {
    this.color = (r << 16) ^ (g << 8) ^ b;
  }

  /**
   * gets the red channel of this color.
   * @return the red value.
   */
  public int getRed() {
    return color >> 16;
  }

  /**
   * gets the green channel of this color.
   * @return the green value.
   */
  public int getGreen() {
    return color >> 8 & 0xff;
  }

  /**
   * gets the blue value of this color.
   * @return the blue value.
   */
  public int getBlue() {
    return color & 0xff;
  }


}
