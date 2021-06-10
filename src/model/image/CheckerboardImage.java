package model.image;

import model.ColorUtils.LightColor;

/**
 * An image with a chekerboard pattern. Generated programatically.
 */
public class CheckerboardImage extends AbstractImage{

  /**
   * Constructs a checkerboard where each tile is 10x10 pixels.
   * @param widthTiles the width in tiles to
   * @param heightTiles
   */
  public CheckerboardImage(int widthTiles, int heightTiles) {
    this.pixArray = new LightColor[10 * widthTiles * heightTiles];
    for (int i = 0; i < pixArray.length; i++) {
      if (i % 20 < 10 ^ (i/widthTiles) % 20 < 10) {
        pixArray[i] = new LightColor(0xffffff);
      } else {
        pixArray[i] = new LightColor(0);
      }
    }
  }

}
