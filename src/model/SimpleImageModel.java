package model;

import java.util.Stack;
import model.filters.Filter;
import model.image.Image;
import model.image.SimpleImage;

/**
 * DEPRECATED
 * An Image processor model that sores a stack of revision versions of the image provided as a
 * source.
 */
@Deprecated
public class SimpleImageModel extends AbstractImageProcessorModel {

  // instead, we are working with layers where each layer is an image
  // so, a hashmap

  Stack<Image> imageVersions = new Stack<>();


  @Override
  public Image getImage() throws IllegalStateException {
    if (imageVersions == null || imageVersions.isEmpty()) {
      throw new IllegalStateException("There is no image to work with yet.");
    }
    return imageVersions.peek();
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
  public void resize(int w, int h) throws IllegalStateException, IllegalArgumentException {
    if (w < 1 || h < 1 || w > this.getImageWidth() | h > this.getImageHeight()) {
      throw new IllegalArgumentException("Invalid Dimensions.");
    }
    Image current = new SimpleImage(this.getImage());
    current.resize(w, h);
    imageVersions.push(current);
  }

  @Override
  public void mosaic(int numSeeds) {
    imageVersions.push(new SimpleImage(imageVersions.peek().mosaic(numSeeds)));
  }

}
