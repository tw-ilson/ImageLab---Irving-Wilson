package model.image;


import model.color.Color;
import model.color.LightColor;

/**
 * An image with a chekerboard pattern. Generated programatically.
 */
public class CheckerboardImage extends AbstractImage {

  /**
   * Constructs a checkerboard where each tile is 10x10 pixels.
   *
   * @param widthTiles  the width in tiles to produce
   * @param heightTiles the height in tiles to produce
   */
  public CheckerboardImage(int widthTiles, int heightTiles) {
    this.width = 10 * widthTiles;
    this.height = 10 * heightTiles;
    this.pixArray = new LightColor[100 * widthTiles * heightTiles];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        if (x % 20 < 10 ^ y % 20 < 10) {
          pixArray[y * width + x] = new LightColor(0xffffff);
        } else {
          pixArray[y * width + x] = new LightColor(0);
        }
      }
    }
  }

  /**
   * Helper constructor only to be used in resize.
   */
  private CheckerboardImage(Color[] pixarray, int w, int h) {
    this.pixArray = pixarray;
    this.width = w;
    this.height = h;
  }

  @Override
  public Image resize(int w, int h) throws IllegalArgumentException {
    return new CheckerboardImage(resizedRaster(w, h), w, h);
  }

  @Override
  public Image mosaic(int numSeeds) {
    return null;
  }
}
