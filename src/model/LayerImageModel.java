package model;

import java.io.IOException;
import model.image.Image;
import model.image.LayeredImage;
import model.image.SimpleLayeredImage;

public class LayerImageModel extends AbstractImageProcessorModel implements ImageProcessorModel {

  private LayeredImage image = new SimpleLayeredImage();

  @Override
  public Image getImageState() throws IllegalStateException {
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
    if (image.numLayers() > 0) {
      return exportHelp(f, name, image.getCurrentLayer());
    } else {
      throw new IllegalStateException();
    }
  }
}
