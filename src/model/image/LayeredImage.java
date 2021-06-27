package model.image;

import java.util.ArrayList;
import model.color.Color;

/**
 * An Image that consists of some number of layers. Adds additional functionality that cannot be
 * supported by Image interface. Each layer contains the same information as a SimpleImage, with an
 * array of pixels, the width, and the height. Each layer can be worked with individually as if it
 * were an image in itself.
 */
public interface LayeredImage extends Image {


  /**
   * listLayers is a method which returns an Array of all of the layerNames.
   *
   * @return (String[]) of all the layernames.
   */
  String[] listLayers();


  /**
   * addLayer adds a new layer to the LayeredImage with the specified filename.
   *
   * @param layerName (the name of the new layer)
   * @throws IllegalArgumentException if the layer of that name already exists.
   */
  void addLayer(String layerName) throws IllegalArgumentException;

  /**
   * returnTopMostVisibleLayer returns a copy of the top most visible image in the LayeredImage.
   *
   * @return Color[] (the pixel array of colors to be processed by the model)
   * @throws IllegalStateException if there are no visible layers in the layeredImage
   */
  Image topMostVisibleLayer() throws IllegalStateException;


  /**
   * gets the image that is the currently selected layer.
   * @return the image at the current layer.
   * @throws IllegalStateException if the current layer cannot be gotten
   */
  Image getCurrentLayer() throws IllegalStateException;

  /**
   * getLayer sets the "current layer", meaning the one that is being worked with, to the layer with
   * the specified layername.
   *
   * @param layerName (the name of the layer)
   * @throws IllegalArgumentException if the layer that the client is trying to grab does not
   *                                  exist.
   */
  void setCurrentLayer(String layerName) throws IllegalArgumentException;

  /**
   * Sets the visibility of the specified layer to the specified value.
   *
   * @param layerName  (the name of the layer to change)
   * @param visibility (the visibility that the client wishes to set the image to).
   */
  void setVisibility(String layerName, boolean visibility) throws IllegalArgumentException;

  /**
   * changeVisibility changes the visibility of the specified layer.
   *
   * @param img the image to replace at the current layer
   */
  void editCurrentLayer(Image img) throws IllegalArgumentException;

  /**
   * Gives the number of layers in this layeredImage. 0 if none.
   *
   * @return the number of layers.
   */
  int numLayers();


  /**
   * Blends together all of the layers, creating an "average" of all the currently visible images.
   * @return the blended array of colors.
   * @throws IllegalStateException the images cannot be blended.
   */
  Color[] blend() throws IllegalStateException;


  /**
   * removeLayer removes the layer with the given layerName.
   *
   * @throws IllegalArgumentException (if the given layer does not exist).
   */
  void removeLayer(String layerName) throws IllegalArgumentException;

  /**
   * getVisibility returns the visibility of the given layer.
   *
   * @param layerName (the layer to return the visibility of).
   * @return boolean (the visibility of the given layer).
   * @throws IllegalArgumentException (if the given layer is null or doesn't exist).
   */
  boolean getVisibility(String layerName) throws IllegalArgumentException;

  /**
   * Resizes all layers of the current layered image to the requested size.
   * @param w the requested width to resize to.
   * @param h the requested height to resize to.
   * @return the new Layered Image.
   * @throws IllegalStateException
   * @throws IllegalArgumentException
   */
  LayeredImage resize(int w, int h) throws IllegalStateException, IllegalArgumentException;

  /**
   * allVisibleImages returns a list of all the visible images in the layeredImage.
   * @return ArrayList (Image) of all of the visible images in the layered image.
   */
  ArrayList<Image> allVisibleImages();
}
