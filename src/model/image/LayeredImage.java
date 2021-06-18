package model.image;

public interface LayeredImage extends Image {

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
  public Image returnTopMostVisibleLayer() throws IllegalStateException;


  // the current layer will be set in the model?
  /**
   * getLayer sets the "current layer", meaning the one that is being worked with, to the layer with
   * the specified layername.
   *
   * @param layerName (the name of the layer)
   * @throws IllegalArgumentException if the layer that the client is trying to grab does not
   * exist.
   */
  public Image getCurrentLayer(String layerName) throws IllegalArgumentException;


  /**
   * changeVisibility changes the visibility of the specified layer.
   */
  public void changeVisibility(String layerName) throws IllegalArgumentException;


  /**
   * setLayer sets the current layer to contain the information within the image
   * that is passed.
   *
   * @param image (the image which the layer is to be set to).
   * @throws IllegalArgumentException if the given image is false.
   */
  public void setLayer(Image image) throws IllegalArgumentException;

}
