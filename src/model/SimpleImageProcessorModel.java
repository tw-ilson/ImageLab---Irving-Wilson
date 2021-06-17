package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

  // instead, we are working with layers where each layer is an image
  // so, a hashmap

  Stack<Image> imageVersions = new Stack<>();
  FilterBuilder builder = new FilterBuilder();


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

    Filter toApply = builder.getFilter(filter);
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

  // these should be separate function objects, rather than one big export function
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
