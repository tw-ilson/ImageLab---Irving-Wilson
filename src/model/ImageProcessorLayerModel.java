package model;

import model.image.Image;

/**
 * This interface contains the functionality for the LayeredImageModel, while also containing
 * compatibility with the existing models.
 */
public interface ImageProcessorLayerModel extends ImageProcessorModel {

  /**
   * Creates a new Layer
   *
   * @param layerName (the distinct name of the new layer)
   */
  public void createLayer(String layerName);


  /**
   * Sets the current layer that the client is working with to the layer with the given layerName if
   * it exists.
   *
   * @param layerName (the name of the layer to set to current)
   */
  public void setCurrentLayer(String layerName);


  /**
   * edits the current layer based on the image passed to editCurrentLayer.
   * @param toEdit (the image which the client wishes to base the current layer upon)
   */
  public void editCurrentLayer(Image toEdit);

}
