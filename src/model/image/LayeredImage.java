package model.image;

import java.util.HashMap;
import model.color.Color;

public class LayeredImage implements Image {
  int width;
  int height;
  HashMap<String, LayerInfo> layerTable;

  public LayeredImage(int w, int h) {
    this.width = w;
    this.height = w;
    layerTable = new HashMap<String, LayerInfo>();
  }

  public LayeredImage(Image ... layers) throws IllegalArgumentException {
    this.width = layers[0].getWidth();
    this.height = layers[0].getHeight();
    this.layerTable = new HashMap<>();
    for (int i = 0; i < layers.length; i++) {
      if (layers[i].getWidth() != width || layers[i].getHeight() != height) {
        throw new IllegalArgumentException("cannot layer images of varying dimensions.");
      }
     this.layerTable.put("layer" + i, new LayerInfo(i, layers[i].pixArray(), true));
    }

  }
  @Override
  public Color[] pixArray() throws IllegalStateException {
    return new Color[0];
  }

  @Override
  public Color getPixel(int x, int y) {
    return null;
  }

  @Override
  public int getWidth() {
    return 0;
  }

  @Override
  public int getHeight() {
    return 0;
  }

  /**
   * A structure to hold the important information about a particular layer.
   */
  private class LayerInfo {
    int inOrder;
    Color[] pixels;
    boolean visible;

    /**
     * Constructs a LayerInfo object.
     * @param inOrder the depth ordering of this layer, with 0 being the "back" and greater numbers being closer to the front.
     * @param pixels the pixel array of the layer.
     * @param visible flags whether or not this layer is visible.
     */
    public LayerInfo(int inOrder, Color[] pixels, boolean visible) {
      this.inOrder = inOrder;
      this.pixels = pixels;
      this.visible = visible;
    }
  }
}
