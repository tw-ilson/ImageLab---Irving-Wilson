package model;

import controller.InputHandler;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Stack;
import model.filters.Filter;
import model.image.Image;
import model.image.SimpleImage;

/**
 * An Image processor model that sores a stack of revision versions of the image provided as a
 * source.
 */
public class SimpleImageModel extends AbstractImageProcessorModel {

  // instead, we are working with layers where each layer is an image
  // so, a hashmap

  Stack<Image> imageVersions = new Stack<>();


  @Override
  public int[] getImagePixels() throws IllegalStateException {
    if (imageVersions == null || imageVersions.isEmpty()) {
      throw new IllegalStateException("There is no image to work with yet.");
    }
    return Arrays.stream(imageVersions.peek().pixArray()).mapToInt(color -> color.getRGB())
        .toArray();
  }

  @Override
  public int getImageWidth() throws IllegalStateException {
    return imageVersions.peek().getWidth();
  }

  @Override
  public int getImageHeight() throws IllegalStateException {
    return imageVersions.peek().getHeight();
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
  public InputHandler produceInputHandler() {
    return;
  }

  @Override
  public void importImage(String filename) throws IllegalArgumentException {
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
  public String export(String f, String name) throws IllegalStateException, IOException {
    //Objects.requireNonNull(imageVersions.peek());
    return exportHelp(f, name, imageVersions.peek());
  }
}
