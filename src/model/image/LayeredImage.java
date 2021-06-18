package model.image;

public interface LayeredImage extends Image {

  interface ILayerInfo { }

  /**
   * addLayer adds a new layer to the LayeredImage with the specified filename.
   *
   * @param layerName (the name of the new layer)
   */
  public void addLayer(String layerName);

  /**
   * returnTopMostVisibleLayer returns a copy of the top most visible image in the LayeredImage
   * @return Color[] (the pixel array of colors to be processed by the model)
   * @throws IllegalStateException if there are no visible layers in the layeredImage
   */
  public Image topMostVisibleLayer() throws IllegalStateException;


  /**
   * gets the image that is the currently selected layer.
   *  @return
   *  @throws IllegalStateException
   */
  public Image getCurrentLayer() throws IllegalStateException;

  /**
   * getLayer sets the "current layer", meaning the one that is being worked with, to the layer with
   * the specified layername.
   *
   * @param layerName (the name of the layer)
   * @throws IllegalArgumentException if the layer that the client is trying to grab does not
   * exist.
   */
  public void setCurrentLayer(String layerName) throws IllegalArgumentException;

  /**
   * Sets the visibility of the current layer to the boolean value passed as argument.
   * @param visible is this layer visible?
   */
  public void setVisibility(boolean visible);

  /**
   * changeVisibility changes the visibility of the specified layer.
   * @param img
   */
  public void editCurrentLayer(Image img) throws IllegalArgumentException;

  /**
   * Gives the number of layers in this layeredImage. 0 if none.
   * @return the number of layers.
   */
  public int numLayers();
}
