package model;

import java.util.Objects;
import java.util.Stack;
import model.ColorUtils.Color;
import model.Filters.Filter;
import model.Filters.FilterBuilder;
import model.image.Image;
import model.image.SimpleImage;

public class SimpleImageProcessorModel extends AbstractImageProcessorModel {

  Stack<Image> imageVersions = new Stack<>();

  @Override
  public Image getImageState() throws IllegalStateException {
    if (imageVersions == null) {
      throw new IllegalStateException("There is no image to work with yet.");
    }
    return imageVersions.peek();
  }

  @Override
  public void applyFilter(String filter, Image i) {
    Image deepCopyImage = new SimpleImage(i.pixArray(), i.getWidth(), i.getHeight());
    FilterBuilder filterBuilder = new FilterBuilder();
    Filter toApply = filterBuilder.getFilter(filter);
    imageVersions.add(toApply.apply(deepCopyImage));
  }

  @Override
  public String export(FileType f, String name) throws IllegalStateException {
    return null;
  }
}
