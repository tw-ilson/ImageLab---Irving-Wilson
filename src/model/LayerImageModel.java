package model;

import controller.InputHandler;
import controller.LayerModelInputHandler;
import java.io.IOException;
import model.image.Image;
import model.image.LayeredImage;
import model.image.SimpleLayeredImage;

public class LayerImageModel extends AbstractImageProcessorModel implements ImageProcessorModel {

  private LayeredImage image = new SimpleLayeredImage();

  @Override
  public int[] getImagePixels() throws IllegalStateException {
    return image.topMostVisibleLayer().pixArray();
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
  public InputHandler produceInputHandler() {
    return new LayerModelInputHandler();
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
