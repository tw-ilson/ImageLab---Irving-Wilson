package model.image;

import model.color.Color;

public interface LayeredImage extends Image {

  interface ILayerInfo {

  }

  /**
   * addLayer adds a new layer to the LayeredImage with the specified filename.
   *
   * @param layerName (the name of the new layer)
   */
  public void addLayer(String layerName);

  /**
   * returnTopMostVisibleLayer returns a copy of the top most visible image in the LayeredImage
   *
   * @return Color[] (the pixel array of colors to be processed by the model)
   * @throws IllegalStateException if there are no visible layers in the layeredImage
   */
  public Image topMostVisibleLayer() throws IllegalStateException;


  /**
   * gets the image that is the currently selected layer.
   *
   * @return
   * @throws IllegalStateException
   */
  public Image getCurrentLayer() throws IllegalStateException;

  /**
   * getLayer sets the "current layer", meaning the one that is being worked with, to the layer with
   * the specified layername.
   *
   * @param layerName (the name of the layer)
   * @throws IllegalArgumentException if the layer that the client is trying to grab does not
   *                                  exist.
   */
  public void setCurrentLayer(String layerName) throws IllegalArgumentException;

  /**
   * Switches the visibility of the provided layer.
   * @param layerName (the layer that the client wishes to change the visibility of).
   */
  public void setVisibility(String layerName);

  /**
   * changeVisibility changes the visibility of the specified layer.
   *
   * @param img
   */
  public void editCurrentLayer(Image img) throws IllegalArgumentException;

  /**
   * Gives the number of layers in this layeredImage. 0 if none.
   *
   * @return the number of layers.
   */
  public int numLayers();


  /**
   * Returns the visibility of the given image.
   *
   * @return boolean (visibility of the current image)
   */
  public boolean getVisibility(String layerName);


  /**
   * Blends together all of the layers, creating an almalga
   * @return
   * @throws IllegalStateException
   */
  public Color[] blend() throws IllegalStateException;
}
