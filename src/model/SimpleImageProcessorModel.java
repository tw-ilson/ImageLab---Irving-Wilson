package model;

import java.util.Objects;
import java.util.Stack;
import model.ColorUtils.Color;
import model.Filters.Filter;
import model.image.Image;

public class SimpleImageProcessorModel extends AbstractImageProcessorModel{
  Stack<Image> imageVersions;

  @Override
  public Image getImageState() throws IllegalStateException {
    return imageVersions.peek();
  }

  @Override
  public void applyFilter(String filter, Image i) {
  }

  @Override
  public String export(FileType f, String name) throws IllegalStateException {
    return null;
  }
}
