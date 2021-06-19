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
    // width and height are both equal to -1 at the beginning
    // only set with the first imported image
    this.width = -1;
    this.height = -1;
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

  /**
   * Initializes a simpleLayeredImage with a list of the given layerNames, and a group of images
   *
   * @param layerNames
   * @param layers
   * @throws IllegalArgumentException
   */
  public SimpleLayeredImage(ArrayList<String> layerNames, Image... layers)
      throws IllegalArgumentException {
    this.width = layers[0].getWidth();
    this.height = layers[0].getHeight();
    this.layerTable = new HashMap<>();

    // doesn't add to the layer until after create layer
    for (int i = 0; i < layers.length; i++) {
      if (layers[i].getWidth() != width || layers[i].getHeight() != height) {
        throw new IllegalArgumentException("cannot create layer images of varying dimensions.");
      }
      this.layerTable.put(layerNames.get(i), new LayerInfo(i, layers[i], true));
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


  @Override
  public Color[] blend() throws IllegalStateException {
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


  // returns the pix array of the current layer
  @Override
  public Color[] pixArray() throws IllegalStateException {
    layerTable.get(this.current);
    return null;
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
    this.current = layerName;
  }

  @Override
  public Image topMostVisibleLayer() throws IllegalStateException {
    int i = 0;
    for (LayerInfo li : this.layerTable.values()) {
      if (li.inOrder == numLayers() - i && li.visible && li.pixels != null) {
        return li.pixels;
      }
      i++;
    }
    throw new IllegalStateException("There are no visible layers with valid images.");
  }

  @Override
  public void setCurrentLayer(String layerName) throws IllegalArgumentException {
    Objects.requireNonNull(layerName);
    if (!layerTable.containsKey(layerName)) {
      throw new IllegalArgumentException("Layer does not exist.");
    }
    this.current = layerName;
  }


  @Override
  public Image getCurrentLayer() throws IllegalArgumentException {
    if (layerTable.get(current).pixels == null || current == null) {
      try {
        this.topMostVisibleLayer();
      } catch (IllegalStateException e) {
        throw new IllegalArgumentException("No visible images to save.");
      }
    }
    return layerTable.get(current).pixels;
  }

  @Override
  public void setVisibility(String layerName, boolean visibility) throws IllegalArgumentException {
    Objects.requireNonNull(layerName);
    if (!layerTable.containsKey(layerName)) {
      throw new IllegalArgumentException("Layer doesn't exist");
    }
    LayerInfo toChange = layerTable.get(layerName);
    layerTable.replace(layerName, new LayerInfo(toChange.inOrder, toChange.pixels,
        visibility));
  }

  @Override
  public int numLayers() {
    return this.layerTable.size();
  }



  @Override
  public void editCurrentLayer(Image img) {
    Objects.requireNonNull(img);

    if (this.width == -1 || this.height == -1) {
      this.width = img.getWidth();
      this.height = img.getHeight();
    }

    if (img.getWidth() != width || img.getHeight() != height) {
      throw new IllegalArgumentException("Image is not the right size, or is not instantiated.");
    }

    LayerInfo oldCurrent = layerTable.get(current);
    this.layerTable.replace(current, new LayerInfo(oldCurrent.inOrder, img, oldCurrent.visible));
  }
}
