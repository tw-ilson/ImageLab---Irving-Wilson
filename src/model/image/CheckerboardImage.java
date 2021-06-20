package model.image;


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

    for (int i = 0; i < pixArray.length; i++) {
      if (i % 20 < 10 ^ (i / (10 * widthTiles)) % 20 < 10) {
        pixArray[i] = new LightColor(0xffffff);
      } else {
        pixArray[i] = new LightColor(0);
      }
    }
  }

}
