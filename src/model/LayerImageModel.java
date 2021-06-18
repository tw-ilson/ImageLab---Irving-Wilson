package model;

import model.image.Image;
import model.image.LayeredImage;
import model.image.SimpleLayeredImage;

public abstract class LayerImageModel extends AbstractImageProcessorModel implements ImageProcessorModel{

  private LayeredImage image = new SimpleLayeredImage();

  @Override
  public Image getImageState() throws IllegalStateException {
    return null;
  }

  @Override
  public void applyFilter(String filter) throws IllegalStateException {

  }
}
