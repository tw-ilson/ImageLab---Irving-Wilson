package model.image;


import java.util.Arrays;
import java.util.stream.Collectors;
import model.color.Color;
import model.color.LightColor;


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
    if (pixArray == null) {
      throw new IllegalStateException("No array of pixels to work with");
    }
    return pixArray.clone();
  }

  @Override
  public Color getPixel(int x, int y) throws IllegalArgumentException {
    if (x > width - 1 || x < 0 || y > height - 1 || y < 0) {
      throw new IllegalArgumentException("Invalid width or height.");
    }
    return pixArray[width * y + x];
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  protected Color[] resizedRaster(int w, int h) throws IllegalArgumentException {
    Color[] resizedRaster = new LightColor[w * h];
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int mapLocW = (int) Math.floor(((float) x / width) * w);
        int mapLocH = (int) Math.floor(((float) y / height) * h);
        resizedRaster[w * mapLocH + mapLocW] = getPixel(x, y);
      }
    }
    return resizedRaster;
  }
}
