package model;

import java.io.File;
import java.io.IOException;
import java.util.Stack;
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
  public void importImage(String filename) {
    super.importImage(filename);
    imageVersions.clear();
    imageVersions.push(sourceImage);
  }

  @Override
  public void importImage(Image image) throws IllegalArgumentException {
    this.sourceImage = image;
    imageVersions.clear();
    imageVersions.push(image);
  }

  @Override
  public String export(FileType f, String name) throws IllegalStateException, IOException {
      File toWrite = new File(name);
      toWrite.createNewFile();
      switch (f) {
        case PPM:
          ImageUtil.writePPM(toWrite, this.imageVersions.peek());
          break;
        default:
          throw new IOException("unsupported export type.");
      }
      return "Successfully exported " + f + " image: " + name;
  }
}
