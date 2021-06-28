package model.image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import model.color.Color;
import model.color.LightColor;

/**
 * An Image that consists of layers. A layer specifies a name, order, image, and visibility. In this
 * LayeredImage, width and height must be the same across layers, and a "current" layer must be
 * specified in order to do certain operations. A layer becomes the current layer when it is added.
 */
public class SimpleLayeredImage implements LayeredImage {

  private int width;
  private int height;
  private HashMap<String, LayerInfo> layerTable;
  private String current;

  /**
   * Initializes a simple layered image with no information. Width and height and layer information
   * are set as layers are added to the layer table.
   */
  public SimpleLayeredImage() {
    // width and height are both equal to -1 at the beginning
    // only set with the first imported image
    this.width = -1;
    this.height = -1;
    layerTable = new HashMap<String, LayerInfo>();
  }


  /**
   * Initializes a simpleLayeredImage with a list of the given layerNames, and a group of images.
   *
   * @param layerNames the names of the layers to create
   * @param layers     the images to add to the layers
   * @throws IllegalArgumentException if the images are of varying dimensions.
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
   * A structure to hold the important information about a particular layer. This can be thought of
   * as a row in the layer table.
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

  @Override
  public void removeLayer(String layerName) throws IllegalArgumentException {
    if (!layerTable.containsKey(layerName)) {
      throw new IllegalArgumentException("Layer does not exist");
    }
    for (String layer : this.layerTable.keySet()) {
      if (layerTable.get(layer).inOrder > layerTable.get(layerName).inOrder) {
        layerTable.replace(layer,
            new LayerInfo(layerTable.get(layer).inOrder - 1, layerTable.get(layer).pixels,
                layerTable.get(layer).visible));
      }
    }
    this.layerTable.remove(layerName);
    ArrayList<String> toCheck = new ArrayList<String>();
    for (String layer : this.layerTable.keySet()) {
      toCheck.add(layer);
    }
    for (int i = toCheck.size() - 1; i >= 0; i--) {
      if (layerTable.get(toCheck.get(i)).visible) {
        this.current = toCheck.get(i);
      }
    }
  }

  @Override
  public boolean getVisibility(String layerName) throws IllegalArgumentException {
    if (layerName == null || !layerTable.containsKey(layerName)) {
      throw new IllegalArgumentException("Invalid layerName");
    }
    return layerTable.get(layerName).visible;
  }


  @Override
  public Color[] pixArray() throws IllegalStateException {
    LayerInfo toCheck = layerTable.get(this.current);
    if (toCheck.pixels == null) {
      throw new IllegalStateException("Image has not been imported.");
    }
    Image toGrab = toCheck.pixels;
    return toGrab.pixArray();
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
  public LayeredImage resize(int w, int h) throws IllegalStateException, IllegalArgumentException {
    SimpleLayeredImage toReturn = new SimpleLayeredImage();
    toReturn.layerTable.putAll(this.layerTable);
    toReturn.height = h;
    toReturn.width = w;
    toReturn.current = this.current;
    if (layerTable.size() == 0 || current == null) {
      throw new IllegalStateException("No layers created to change");
    }
    for (String key : layerTable.keySet()) {
      LayerInfo old = layerTable.get(key);
      layerTable.replace(key, new LayerInfo(old.inOrder, old.pixels.resize(w, h), old.visible));
    }
    width = w;
    height = h;
    return toReturn;
  }

  @Override
  public ArrayList<Image> allVisibleImages() {
    ArrayList<Image> toReturn = new ArrayList<>();
    for (LayerInfo info: layerTable.values()) {
      if (info.pixels != null && info.visible) {
        toReturn.add(info.pixels);
      }
    }
    return toReturn;
  }

  @Override
  public Image mosaic(int numSeeds) throws IllegalStateException {
    SimpleLayeredImage toReturn = new SimpleLayeredImage();
    toReturn.layerTable.putAll(this.layerTable);
    toReturn.height = this.height;
    toReturn.width = this.width;
    toReturn.current = this.current;
    if (layerTable.size() == 0 || current == null || layerTable.get(current).pixels == null) {
      throw new IllegalStateException("No layers created to change");
    }
    LayerInfo oldInfo = layerTable.get(current);
    Image newMosaic = oldInfo.pixels.mosaic(numSeeds);
    return newMosaic;
  }

  @Override
  public String[] listLayers() {
    return layerTable.keySet().toArray(new String[numLayers()]);
  }

  @Override
  public void addLayer(String layerName) throws IllegalArgumentException{
    Objects.requireNonNull(layerName);
    if (layerTable.containsKey(layerName)) {
      throw new IllegalArgumentException("Layer with the given name already exists.");
    }
    layerTable.put(layerName, new LayerInfo(numLayers() + 1, null, true));
    this.current = layerName;
  }

  @Override
  public Image topMostVisibleLayer() throws IllegalStateException {
    if (layerTable.size() == 0) {
      throw new IllegalStateException("No layers created.");
    }
    Image toSave = null;
    LayerInfo toReturn = new LayerInfo(-1, null, false);
    for (LayerInfo info : this.layerTable.values()) {
      if (info.inOrder > toReturn.inOrder && info.visible && info.pixels != null) {
        toReturn = new LayerInfo(info.inOrder, info.pixels, true);
        toSave = info.pixels;
      }
    }
    toReturn.pixels = toSave;
    if (toReturn.pixels != null) {
      return toReturn.pixels;
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
    if (layerTable.size() == 0) {
      throw new IllegalArgumentException("LayerTable contains no layers.");
    }
    if (layerTable.get(current).pixels == null || current == null || !(layerTable
        .get(current).visible)) {
      try {
        this.topMostVisibleLayer();
      } catch (IllegalStateException e) {
        throw new IllegalArgumentException("No visible images");
      }
    }
    return layerTable.get(current).pixels;
  }

  @Override
  public void setVisibility(String layerName, boolean visibility) throws IllegalArgumentException {
    if (!layerTable.containsKey(layerName) || layerName == null) {
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
  public void editCurrentLayer(Image img) throws IllegalStateException {
    Objects.requireNonNull(img);
    if (layerTable.size() == 0 || current == null) {
      throw new IllegalStateException("No layers created");
    }
    if (this.width == -1 || this.height == -1) {
      this.width = img.getWidth();
      this.height = img.getHeight();
    }
    if (layerTable.size() != 1 && (img.getWidth() != width || img.getHeight() != height)) {
      throw new IllegalArgumentException("Image is not the right size, or is not instantiated.");
    }
    LayerInfo oldCurrent = layerTable.get(current);
    this.layerTable.replace(current, new LayerInfo(oldCurrent.inOrder, img, oldCurrent.visible));
  }
}
