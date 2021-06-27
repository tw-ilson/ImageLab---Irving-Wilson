package view;

import java.io.IOException;
import java.util.Objects;
import model.image.Image;

/**
 * A text view for an image processing model, which displays the user interaction in the console.
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
  public void displayImage(Image image) throws IOException{
    Objects.requireNonNull(image);
    output.append("Image width" + image.getWidth() + "\n");
    output.append("Image height" + image.getHeight() + "\n");
  }

  @Override
  public void displayLayers(String[] layerNames) throws IOException {
    for (String s: layerNames) {
      output.append(s + "/n");
    }
  }
}
