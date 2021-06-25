package model.color;

/**
 * A type of color that utilises a single 32-bit number to represent its rgb channels.
 */
public class LightColor implements Color {

  private int color;

  /**
   * Takes a hexcode integer as color.
   *
   * @param color the color code to use as this color.
   */
  public LightColor(int color) {
    this.color = color & 0xffffff;
  }

  /**
   * Copy constructor.
   *
   * @param that the color to copy
   */
  public LightColor(Color that) {
    this.color = that.getRGB();
  }

  /**
   * Takes red, blue, and green channels to construct this color (0-255).
   *
   * @param r the red channel.
   * @param g the blue channel.
   * @param b the green channel.
   */
  public LightColor(Number r, Number g, Number b) {
    int red = (int) r;
    int green = (int) g;
    int blue = (int) b;
    this.color = (red << 16) ^ (green << 8) ^ blue;
  }

  @Override
  public int getRed() {
    return color >> 16 & 0xff;
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
