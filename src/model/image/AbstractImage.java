package model.image;


import java.util.Objects;
import model.ColorUtils.Color;
import model.ColorUtils.LightColor;


/**
 * Abstract class solidifies general properties of Image implementations.
 */
public abstract class AbstractImage implements Image {

  protected int width;
  protected int height;
  protected Color[] pixArray;

  /**
   * empty default constructor.
   */
  public AbstractImage() {
    super();
  }

  @Override
  public Color[] pixArray() throws IllegalStateException {
    return pixArray.clone();
  }

  @Override
  public Color getPixel(int x, int y) {
    if(x > width - 1 || x < 0 || y > height - 1 || y < 0) {
      throw new IllegalArgumentException("Invalid width or height.");
    }
    return pixArray[width * y + x];
  }

  @Override
  public void setPixel(int x, int y, Color c) {
    if(x > width - 1 || x < 0 || y > height - 1 || y < 0) {
      throw new IllegalArgumentException("Invalid width or height.");
    }
    Objects.requireNonNull(c);
    pixArray[width*y + x] = c;
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
