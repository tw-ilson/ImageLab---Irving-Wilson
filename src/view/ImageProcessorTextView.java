package view;

import java.io.IOException;
import java.util.Objects;
import model.image.Image;

/**
 * A text view for an image processing model.
 */
public class ImageProcessorTextView implements ImageProcessorView {

  Appendable output;

  public ImageProcessorTextView(Appendable output) {
    this.output = output;
  }

  @Override
  public void giveMessage(String message) throws IOException {
    Objects.requireNonNull(message);
    output.append(message);
  }

  @Override
  public void displayImage(Image image) {

  }
}
