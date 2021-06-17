package model.image;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import model.color.Color;
import model.color.LightColor;

public class LayeredImage implements Image {

  int width;
  int height;
  HashMap<String, LayerInfo> layerTable;

  public LayeredImage(int w, int h) {
    this.width = w;
    this.height = w;
    layerTable = new HashMap<String, LayerInfo>();
  }

  public LayeredImage(Image... layers) throws IllegalArgumentException {
    this.width = layers[0].getWidth();
    this.height = layers[0].getHeight();
    this.layerTable = new HashMap<>();
    for (int i = 0; i < layers.length; i++) {
      if (layers[i].getWidth() != width || layers[i].getHeight() != height) {
        throw new IllegalArgumentException("cannot layer images of varying dimensions.");
      }
      this.layerTable.put("layer" + i, new LayerInfo(i, layers[i], true));
    }

  }

  @Override
  public Color[] pixArray() throws IllegalStateException {
    List<LayerInfo> layers = new ArrayList<LayerInfo>(
        layerTable.values().stream()
            .filter(l -> l.visible).collect(Collectors.toList()));
    Color[] blended = new LightColor[width * height];
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        double r = 0;
        double g = 0;
        double b = 0;
        for (LayerInfo layer : layers) {
          Color cur = layer.pixels.getPixel(x, y);
            r += (double) cur.getRed() / layers.size();
            g += (double) cur.getGreen() / layers.size();
            b += (double) cur.getBlue() / layers.size();
        }
        blended[width*y + x] = new LightColor(r, g, b);
      }
    }
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
    Image pixels;
    boolean visible;

    /**
     * Constructs a LayerInfo object.
     *
     * @param inOrder the depth ordering of this layer, with 0 being the "back" and greater numbers
     *                being closer to the front.
     * @param pixels  the pixel array of the layer.
     * @param visible flags whether or not this layer is visible.
     */
    public LayerInfo(int inOrder, Image pixels, boolean visible) {
      this.inOrder = inOrder;
      this.pixels = pixels;
      this.visible = visible;
    }
  }
}
