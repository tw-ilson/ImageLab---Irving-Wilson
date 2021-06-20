package model.image;

import model.color.Color;

public interface LayeredImage extends Image {


  String[] listLayers();


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
   * Sets the visibility of the specified layer to the specified value.
   *
   * @param layerName  (the name of the layer to change)
   * @param visibility (the visibility that the client wishes to set the image to).
   */
  public void setVisibility(String layerName, boolean visibility) throws IllegalArgumentException;

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
   * Blends together all of the layers, creating an almalga
   *
   * @return
   * @throws IllegalStateException
   */
  public Color[] blend() throws IllegalStateException;


  /**
   * removeLayer removes the layer with the given layerName.
   *
   * @throws IllegalArgumentException (if the given layer does not exist).
   */
  public void removeLayer(String layerName) throws IllegalArgumentException;

  /**
   * getVisibility returns the visibility of the given layer.
   * @param layerName (the layer to return the visibility of).
   * @return boolean (the visibility of the given layer).
   * @throws IllegalArgumentException (if the given layer is null or doesn't exist).
   */
  public boolean getVisibility(String layerName) throws IllegalArgumentException;
}
