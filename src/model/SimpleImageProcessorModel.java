package model;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Stack;
import model.filters.Filter;
import model.filters.FilterBuilder;
import model.image.Image;
import model.image.SimpleImage;

/**
 * An Image processor model that sores a stack of revision versions of the image provided as a
 * source.
 */
public class SimpleImageProcessorModel extends AbstractImageProcessorModel {

  Stack<Image> imageVersions = new Stack<>();

  @Override
  public Image getImageState() throws IllegalStateException {
    if (imageVersions == null || imageVersions.isEmpty()) {
      throw new IllegalStateException("There is no image to work with yet.");
    }
    return imageVersions.peek();
  }

  @Override
  public void applyFilter(String filter) throws IllegalStateException {
    if (imageVersions.isEmpty()) {
      throw new IllegalStateException();
    }

    FilterBuilder filterBuilder = new FilterBuilder();
    Filter toApply = filterBuilder.getFilter(filter);
    Image nextImage = new SimpleImage(imageVersions.peek());
    imageVersions.add(toApply.apply(nextImage));
  }

  @Override
  public void importImage(String filename) {
    super.importImage(filename);
    imageVersions.clear();
    imageVersions.push(sourceImage);
  }

  @Override
  public void importImage(Image image) throws IllegalArgumentException {
    Objects.requireNonNull(image);
    super.importImage(image);
    imageVersions.clear();
    imageVersions.push(image);
  }

  @Override
  public String export(FileType f, String name) throws IllegalStateException, IOException {
    File toWrite = new File(name);
    toWrite.createNewFile();
    switch (f) {
      case PPM:
        writePPM(toWrite, this.imageVersions.peek());
        break;
      case JPEG:
        //to be added later, possibly
        throw new IOException("as of now, unsupported export type.");
      case PNG:
        //to be added later, possibly
        throw new IOException("as of yet, unsupported export type.");
      default:
        throw new IOException("unsupported export type.");
    }
    return "Successfully exported " + f + " image: " + name;
  }
}
