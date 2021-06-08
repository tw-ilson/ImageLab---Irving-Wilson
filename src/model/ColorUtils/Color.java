package model.ColorUtils;

public class Color {
  private int color;
  private int r;
  private int g;
  private int b;

  public Color(int color) {
    this.color = color;
  }

  public Color(int r, int g, int b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }

  public int getRed() {
    return color >> 16;
  }

  public int getGreen() {
    return color >> 8 & 0xff;
  }

  public int getBlue() {
    return color & 0xff;
  }


}
