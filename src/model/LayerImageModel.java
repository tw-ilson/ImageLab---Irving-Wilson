package model;

import java.io.IOException;
import model.filters.Filter;
import model.filters.FilterBuilder;
import model.image.Image;
import model.image.LayeredImage;
import model.image.SimpleImage;

public class LayerImageModel extends AbstractImageProcessorModel implements ImageProcessorModel {

  // should the model track the current image
  private Image image = new LayeredImage();
  FilterBuilder builder = new FilterBuilder();


  // why is this necessary
  // do we need this at all;
  @Override
  public Image getImageState() throws IllegalStateException {
    return null;
  }

  // applies to the current layer

  @Override
  public void applyFilter(String filter) throws IllegalStateException {
    Filter toApply = builder.getFilter(filter);

  }

  @Override
  public String export(String f, String name) throws IllegalStateException, IOException {
    return null;
  }
}
