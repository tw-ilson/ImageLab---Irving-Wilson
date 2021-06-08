package model.ColorUtils;

public class Color {
  private int color;


  public Color(int color) {
    this.color = color;
  }

  public Color(int r, int g, int b) {
    r = r << 16;
    g = g << 8;
    this.color = r & g & b;
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
