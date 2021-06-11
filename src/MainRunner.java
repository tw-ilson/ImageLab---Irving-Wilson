import java.io.IOException;
import model.FileType;
import model.ImageProcessorModel;
import model.SimpleImageProcessorModel;

/**
 * Main method class for the purpose of testing.
 */
public class MainRunner {

  /**
   * Main method for the purpose of testing.
   * @param args
   */
  public static void main(String[] args) {
    String filename;
    ImageProcessorModel model = new SimpleImageProcessorModel();

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "sample.ppm";
    }

    try {
      model.importImage("manhattan-small.ppm");
    } catch (IllegalArgumentException e) {
      System.out.println("File " + filename + " not found!");
    }

    model.applyFilter("blur");


    try {
      model.export(FileType.PPM, "sharp.ppm");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
