package model;

import model.image.Image;

/**
 * This interface contains the functionality for the LayerImageModel, while also containing
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


  /* should we be able to load the current layer
  public void editCurrentLayer();*/

}
