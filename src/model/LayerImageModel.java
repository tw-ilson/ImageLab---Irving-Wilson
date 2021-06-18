package model;

import java.util.Objects;
import model.image.Image;
import model.image.LayeredImage;
import model.image.SimpleLayeredImage;

public class LayerImageModel extends AbstractImageProcessorModel implements
    ImageProcessorLayerModel {

  private LayeredImage image = new SimpleLayeredImage();

  @Override
  public int[] getImagePixels() throws IllegalStateException {
    return new int[0];
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

    Image filtered = builder.getFilter(filter).apply(image.getCurrentLayer());
    image.editCurrentLayer(filtered);
  }

  @Override
  public void createLayer(String layerName) {
    Objects.requireNonNull(layerName);
    image.addLayer(layerName);
  }

  @Override
  public void setCurrentLayer(String layerName) {
    Objects.requireNonNull(layerName);
    image.setCurrentLayer(layerName);
  }

  @Override
  public void editCurrentLayer(Image toEdit) {
    Objects.requireNonNull(toEdit);
    image.editCurrentLayer(toEdit);
  }

}
