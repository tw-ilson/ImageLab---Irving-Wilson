package model;

import java.util.Objects;
import model.image.Image;
import model.image.LayeredImage;
import model.image.SimpleLayeredImage;

/**
 * the LayeredImageModel class represents the model that can work with a layered image, which
 * is in essence a stack of simple images (a pixel array, width, and height) that can each be
 * manipulated with color and kernel filters. The one can save any image from the layered image,
 * provided that it is visible.
 */
public class LayeredImageModel extends AbstractImageProcessorModel implements
    ImageProcessorLayerModel {

  private LayeredImage image = new SimpleLayeredImage();

  @Override
  public Image getImage() throws IllegalArgumentException {
    return image.getCurrentLayer();
  }

  @Override
  public int getImageWidth() throws IllegalStateException {
    return image.getWidth();
  }

  @Override
  public int getImageHeight() throws IllegalStateException {
    return image.getHeight();
  }

  @Override
  public void applyFilter(String filter) throws IllegalStateException {
    if (image.numLayers() == 0) {
      throw new IllegalStateException("No image to apply to.");
    }
    if (builder.hasFilter(filter)) {
      try {
        Image filtered = builder.getFilter(filter).apply(image.getCurrentLayer());
        image.editCurrentLayer(filtered);
      } catch (IllegalStateException e) {
        throw new IllegalStateException("Cannot pass a null image to the apply method.");
      }
    } else {
      throw new IllegalArgumentException("Filter does not exist.");
    }
  }

  @Override
  public void resize(int w, int h) throws IllegalStateException, IllegalArgumentException {

  }

  @Override
  public void removeLayer(String layerName) throws IllegalArgumentException {
    image.removeLayer(layerName);
  }

  @Override
  public String[] listLayers() {
    return image.listLayers();
  }

  @Override
  public boolean getVisibility(String layerName) {
    return image.getVisibility(layerName);
  }

  @Override
  public void createLayer(String layerName) {
    Objects.requireNonNull(layerName);
    image.addLayer(layerName);
  }

  @Override
  public void setCurrentLayer(String layerName) throws IllegalArgumentException {
    Objects.requireNonNull(layerName);
    image.setCurrentLayer(layerName);
  }

  @Override
  public void editCurrentLayer(Image toEdit) {
    Objects.requireNonNull(toEdit);
    image.editCurrentLayer(toEdit);
  }

  @Override
  public void setVisibility(String layerName, boolean visibility) throws IllegalStateException {
    image.setVisibility(layerName, visibility);
  }


}
