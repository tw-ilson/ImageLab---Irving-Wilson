package model;

import java.util.Stack;
import model.ColorUtils.Color;
import model.image.Image;
import model.image.SimpleImage;

public class SimpleImageProcessorModel extends AbstractImageProcessorModel{
  Stack<Image> imageVersions;


  @Override
  public Image getImageState() throws IllegalStateException {
    return imageVersions.peek();
  }

  @Override
  public void applyFilter(String filter, Image i) {
    if(!filters.containsKey(filter)) {
      throw new IllegalArgumentException("Filter does not exist.");
    }
    Image nextVersion = new SimpleImage(i);

    nextVersion = filters.get(filter).apply(i);

    imageVersions.push(nextVersion);
  }

  @Override
  public void importImage(String fileName) {
    super.importImage(fileName);
    imageVersions.push(this.sourceImage);
  }

  @Override
  public String export(FileType f, String name) throws IllegalStateException {
    return null;
  }
}
