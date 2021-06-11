package model.ColorUtils;

/**
 * A type of color that utilises 3 32-bit numbers to represent its rgb channels.
 */
public class HeavyColor implements Color {
  int r;
  int g;
  int b;

  public HeavyColor(int r, int g, int b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }
  @Override
  public int getRed() {
    return r;
  }

  @Override
  public int getGreen() {
    return g;
  }

  @Override
  public int getBlue() {
    return b;
  }

  @Override
  public int getRGB() {
    return (r << 16) ^ (g << 8) ^ b;
  }
}
