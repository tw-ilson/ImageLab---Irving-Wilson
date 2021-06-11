package model.ColorUtils;

import java.util.Objects;

public class LightColor implements Color {

  private int color;

  /**
   * Takes a hexcode integer as color.
   *
   * @param color the color code to use as this color.
   */
  public LightColor(int color) {
    this.color = color;
  }

  /**
   * Copy constructor.
   *
   * @param that
   */
  public LightColor(LightColor that) {
    Objects.requireNonNull(that);
    this.color = that.color;
  }

  /**
   * Takes red, blue, and green channels to construct this color (0-255).
   *
   * @param r the red channel.
   * @param g the blue channel.
   * @param b the green channel.
   */
  public LightColor(int r, int g, int b) {
    this.color = (r << 16) ^ (g << 8) ^ b;
  }

  @Override
  public int getRed() {
    return color >> 16;
  }

  @Override
  public int getGreen() {
    return color >> 8 & 0xff;
  }

  @Override
  public int getBlue() {
    return color & 0xff;
  }

  @Override
  public int getRGB() {
    return color;
  }
}
