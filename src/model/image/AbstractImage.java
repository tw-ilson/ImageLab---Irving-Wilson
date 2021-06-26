package model.image;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
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

  /**
   * Calculates a color from the surrounding 4.
   *
   * @return the resulting color.
   */
  private Color resizeRasterHelp(float mapLocX, float mapLocY) {
    Color cA = this.getPixel((int) mapLocX, (int) mapLocY);
    Color cB = this.getPixel((int) (mapLocX + 1), (int) mapLocY);
    Color cC = this.getPixel((int) mapLocX, (int) (mapLocY + 1));
    Color cD = this.getPixel((int) (mapLocX + 1), (int) (mapLocY + 1));

    int mRed = Math
        .round(cB.getRed() * (mapLocX - (int) mapLocX) + cA.getRed() * ((int) (mapLocX + 1)
            - mapLocX));
    int nRed = Math
        .round(cD.getRed() * (mapLocX - (int) mapLocX) + cC.getRed() * ((int) (mapLocX + 1)
            - mapLocX));
    int red = (int) (nRed * (mapLocY - (int) mapLocY) + mRed * ((int) (mapLocY + 1)
        - mapLocY));

    int mGreen = Math
        .round(cB.getGreen() * (mapLocX - (int) mapLocX) + cA.getGreen() * ((int) (mapLocX + 1)
            - mapLocX));
    int nGreen = Math
        .round(cD.getGreen() * (mapLocX - (int) mapLocX) + cC.getGreen() * ((int) (mapLocX + 1)
            - mapLocX));
    int green = (int) (nGreen * (mapLocY - (int) mapLocY) + mGreen * ((int) (mapLocY + 1)
        - mapLocY));

    int mBlue = Math
        .round(cB.getBlue() * (mapLocX - (int) mapLocX) + cA.getBlue() * ((int) (mapLocX + 1)
            - mapLocX));
    int nBlue = Math
        .round(cD.getBlue() * (mapLocX - (int) mapLocX) + cC.getBlue() * ((int) (mapLocX + 1)
            - mapLocX));
    int blue = (int) (nBlue * (mapLocY - (int) mapLocY) + mBlue * ((int) (mapLocY + 1)
        - mapLocY));

    return new LightColor(red, green, blue);
  }

  protected Color[] resizedRaster(int w, int h) throws IllegalArgumentException {
    Color[] resizedRaster = new LightColor[w * h];
    for (int y = 0; y < h; y++) {
      for (int x = 0; x < w; x++) {
        float mapLocX = ((float) x / w) * width;
        float mapLocY = ((float) y / h) * height;
        resizedRaster[w * y + x] = resizeRasterHelp(mapLocX, mapLocY);
      }
    }
    return resizedRaster;
  }


  protected Color[] imageMosaic(int numSeeds) {
    Random rand = new Random();
    Color[] mosaic = new LightColor[this.pixArray.length];
    ArrayList<Integer> coords = new ArrayList<>();
    Color[] seeds = new LightColor[numSeeds];

    // 1.) create list of random seeds
    for (int i = 0; i < numSeeds; i++) {
      boolean hasAdded = false;
      while (!hasAdded) {
        int x = rand.nextInt(width);
        int y = rand.nextInt(height);
        if (!new ArrayList(Arrays.asList(seeds)).contains(this.getPixel(x, y))) {
          seeds[i] = this.getPixel(x, y);
          coords.add(x);
          coords.add(y);
          hasAdded = true;
        }
      }
    }

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int minDist = width * height;
        int distToCheck;
        int z = 0;
        for (int j = 0; j < seeds.length; j++) {
          distToCheck = (int) (Math.pow((Math.pow(x - coords.get(z), 2) +
              Math.pow(y - coords.get(z + 1), 2)), 0.5));
          if (distToCheck < minDist) {
            mosaic[width * y + x] = seeds[j];
            minDist = distToCheck;
          }
          z += 2;
        }
      }
    }
    return mosaic;
  }
}
