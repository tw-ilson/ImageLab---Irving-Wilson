package model;

import java.util.Stack;
import model.ColorUtils.Color;
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

    if(!imageVersions.contains(i)) {
      imageVersions.add(i);
    }

    Image newVersion = toApply.apply(i);
    imageVersions.add(newVersion);

  }

  @Override
  public String export(FileType f, String name) throws IllegalStateException {
    return null;
  }
}
