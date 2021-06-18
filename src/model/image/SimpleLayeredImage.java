package model.image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import model.color.Color;
import model.color.LightColor;

/**
 * A type of layered Image that specifies the name, order, image, and visibility for each layer.
 */
public class SimpleLayeredImage implements LayeredImage {

  private int width;
  private int height;
  private HashMap<String, LayerInfo> layerTable;
  private String current;

  // the width and the height are set upon the import of the first image
  // into the program.
  public SimpleLayeredImage() {
    // width and height are both equal to 0 at the beginning
    // only set with the first imported image
    layerTable = new HashMap<String, LayerInfo>();
  }
  public SimpleLayeredImage(int w, int h) {
    if (w < 1 || h < 1) {
      throw new IllegalArgumentException("Cannot pass width and height arguments that are less"
          + "than 1.");
    }
    this.width = w;
    this.height = w;
    layerTable = new HashMap<String, LayerInfo>();
  }

  public SimpleLayeredImage(Image... layers) throws IllegalArgumentException {
    this.width = layers[0].getWidth();
    this.height = layers[0].getHeight();
    this.layerTable = new HashMap<>();

    // doesn't add to the layer until after create layer
    for (int i = 0; i < layers.length; i++) {
      if (layers[i].getWidth() != width || layers[i].getHeight() != height) {
        throw new IllegalArgumentException("cannot create layer images of varying dimensions.");
      }
      this.layerTable.put("layer" + i, new LayerInfo(i, layers[i], true));
    }
  }


  /**
   * A structure to hold the important information about a particular layer.
   */
  private class LayerInfo implements ILayerInfo {

    private int inOrder;
    private Image pixels;
    private boolean visible;

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

  /**
   * In this layered version of an image, this method 'blends' by seeking the average of all pixels
   * across layers.
   *
   * @return a blended array of all layers.
   * @throws IllegalStateException
   */
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
        blended[width * y + x] = new LightColor(r, g, b);
      }
    }
    return new Color[0];
  }

  @Override
  public Color getPixel(int x, int y) {
    return this.topMostVisibleLayer().getPixel(x, y);
  }

  @Override
  public int getWidth() {
    return this.topMostVisibleLayer().getWidth();
  }

  @Override
  public int getHeight() {
    return this.topMostVisibleLayer().getHeight();
  }


  @Override
  public void addLayer(String layerName) {
    Objects.requireNonNull(layerName);
    if (layerTable.containsKey(layerName)) {
      throw new IllegalArgumentException("Layer with the given name already exists.");
    }
    layerTable.put(layerName, new LayerInfo(numLayers() + 1, null, true));
  }


  @Override
  public Image topMostVisibleLayer() throws IllegalStateException {
    //if (layerTable
    for (LayerInfo li : this.layerTable.values()) {
      if (li.inOrder == numLayers()) {
        return li.pixels;
      }
    }
    throw new IllegalStateException("There are no visible layers.");
  }

  @Override
  public void setCurrentLayer(String layerName) throws IllegalArgumentException {
    Objects.requireNonNull(layerName);
    if (!layerTable.containsKey(layerName)) {
      throw new IllegalArgumentException("Layer does not exist.");
    }
    this.current = layerName;
  }

  public Image getCurrentLayer() {
    return layerTable.get(current).pixels;
  }

  @Override
  public void setVisibility(boolean visibility) throws IllegalArgumentException {
    if (layerTable.size() < 1) {
      throw new IllegalArgumentException("No layers created");
    }
    layerTable.get(current).visible = visibility;
  }

  @Override
  public int numLayers() {
    return this.layerTable.size();
  }

  @Override
  public boolean getVisibility(String layerName) {
    LayerInfo toCheck = layerTable.get(layerName);
    return toCheck.visible;
  }


  @Override
  public void editCurrentLayer(Image img) {
    Objects.requireNonNull(img);
    if (img.getWidth() != width || img.getHeight() != height || width == 0
        || height == 0) {
      throw new IllegalArgumentException("Image is not the right size, or is not instantiated.");
    }
    LayerInfo oldCurrent = layerTable.get(current);
    this.layerTable.put(current, new LayerInfo(oldCurrent.inOrder, img, oldCurrent.visible));
  }
}