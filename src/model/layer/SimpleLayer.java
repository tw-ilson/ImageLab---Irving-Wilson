package model.layer;

import java.util.Objects;
import model.image.Image;

/**
 * Represents a Layer that the Image-Processing program works with
 */
public class SimpleLayer implements Layer {

  private final Image image;
  private final boolean isVisible;

  public SimpleLayer(Image image, boolean isVisible) {
    Objects.requireNonNull(image);
    Objects.requireNonNull(isVisible);
    this.image = image;
    this.isVisible = isVisible;
  }


  @Override
  public Layer changeVisibility() {
    // should it be a copy of the image, or just passed the image in the layer?
    return new SimpleLayer(this.image, !this.isVisible);
  }
}
