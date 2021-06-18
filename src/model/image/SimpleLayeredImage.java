package model.image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import model.color.Color;
import model.color.LightColor;

public class SimpleLayeredImage implements LayeredImage {

  private int width;
  private int height;
  private HashMap<String, LayerInfo> layerTable;
  private String currentName;
  private LayerInfo current;
  private ArrayList<String> layerNamesList;

  private SimpleLayeredImage() { }

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
    for (int i = 0; i < layers.length; i++) {
      if (layers[i].getWidth() != width || layers[i].getHeight() != height) {
        throw new IllegalArgumentException("cannot create layer images of varying dimensions.");
      }
      this.layerTable.put("layer" + i, new LayerInfo(i, layers[i], true));
    }

    // how to get the current image
    //this.current = layerTable.get();

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
        blended[width * y + x] = new LightColor(r, g, b);
      }
    }
    return new Color[0];
  }

  @Override
  public Color getPixel(int x, int y) {
    return this.returnTopMostVisibleLayer().getPixel(x, y);
  }

  @Override
  public int getWidth() {
    return this.returnTopMostVisibleLayer().getWidth();
  }

  @Override
  public int getHeight() {
    return this.returnTopMostVisibleLayer().getHeight();
  }

  /**
   * A structure to hold the important information about a particular layer.
   */
  private class LayerInfo {

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
  public void addLayer(String layerName) {
    Objects.requireNonNull(layerName);
    layerTable.put(layerName, new LayerInfo(0, null, true));
    layerNamesList.add(layerName);
  }


  @Override
  public Image returnTopMostVisibleLayer() throws IllegalStateException {
    for (int i = layerTable.size(); i >= 0; i--) {
      if (layerTable.get(layerNamesList.get(i)).visible) {
        return layerTable.get(layerNamesList.get(i)).pixels;
      }
    }
    throw new IllegalStateException("There are no visible layers.");
  }

  @Override
  public Image getCurrentLayer(String layerName) throws IllegalArgumentException {
    Objects.requireNonNull(layerName);
    if (!layerTable.containsKey(layerName)) {
      throw new IllegalArgumentException("Layer does not exist.");
    }
    this.currentName = layerName;
    this.current = layerTable.get(layerName);
    return layerTable.get(layerName).pixels;
  }

  @Override
  public void changeVisibility(String layerName) throws IllegalArgumentException{
    if (!layerTable.containsKey(layerName)) {
      throw new IllegalArgumentException("Layer does not exist.");
    }
    LayerInfo toEdit = layerTable.get(layerName);
    toEdit.visible = !layerTable.get(layerName).visible;
    layerTable.replace(layerName, toEdit);
  }

  @Override
  public void setLayer(Image image) throws IllegalArgumentException {
   /* LayerInfo toEdit = this.
    image.pixArray();*/
  }


}
