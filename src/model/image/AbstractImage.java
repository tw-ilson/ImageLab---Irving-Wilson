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
