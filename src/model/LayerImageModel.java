package model;

import java.io.IOException;
import model.image.Image;
import model.image.LayeredImage;

public abstract class LayerImageModel extends AbstractImageProcessorModel implements ImageProcessorModel{

  private final Image image = new LayeredImage();

  @Override
  public Image getImageState() throws IllegalStateException {
    return null;
  }

  @Override
  public void applyFilter(String filter) throws IllegalStateException {

  }
}
