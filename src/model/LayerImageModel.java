package model;

import java.io.IOException;
import java.util.Objects;
import model.image.Image;
import model.image.LayeredImage;
import model.image.SimpleLayeredImage;

public class LayerImageModel extends AbstractImageProcessorModel implements
    ImageProcessorLayerModel {

  // so this is obviously null
  private LayeredImage image = new SimpleLayeredImage();

  // set height and width equal to the width and height of the sourceImage
  // then can only add images that contain the same dimensions

  @Override
  public Image getImageState() throws IllegalStateException {
    image = new SimpleLayeredImage(sourceImage);
    return image.topMostVisibleLayer();
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
  public String export(String f, String name) throws IllegalStateException, IOException {
    // exports the topMost visible layer from the current layer
    // If the current layer is not visible
    if (image.numLayers() > 0) {
      if (image.getVisibility(name)) {
        return exportHelp(f, name, image.getCurrentLayer());
      } else {
        return exportHelp(f, name, image.topMostVisibleLayer());
      }
    } else {
      throw new IllegalStateException();
    }
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
  public void editCurrentLayer() {
  }
}
