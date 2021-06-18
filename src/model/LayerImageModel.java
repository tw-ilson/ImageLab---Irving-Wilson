package model;

import controller.InputHandler;
import java.io.IOException;
import java.util.Objects;
import model.image.Image;
import model.image.LayeredImage;
import model.image.SimpleLayeredImage;

public class LayerImageModel extends AbstractImageProcessorModel implements
    ImageProcessorLayerModel {

  // so this is obviously null
  private LayeredImage image = new SimpleLayeredImage();

  @Override
  public int[] getImagePixels() throws IllegalStateException {
    return new int[0];
  }

  @Override
  public int getImageWidth() throws IllegalStateException {
    return 0;
  }

  @Override
  public int getImageHeight() throws IllegalStateException {
    return 0;
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



  // is this functionality necessary, when do we edit the given layer
  @Override
  public void editCurrentLayer(Image img) {

  }
}
