package model;

import java.awt.Color;
import java.util.Stack;
import model.Filters.IFilter;
import model.image.Image;

public class SimpleImageProcessorModel extends AbstractImageProcessorModel{
  Stack<Image> imageVersions;

  @Override
  public Color[] getImageState() throws IllegalStateException {
    return imageVersions.peek().pixArray();
  }

  @Override
  public void applyFilter(String filter, Image i) {
    if(!filters.containsKey(filter)) {
      throw new IllegalArgumentException("Filter does not exist.");
    }
    IFilter toApply = filters.get(filter);
    toApply.apply(i);
  }
}
