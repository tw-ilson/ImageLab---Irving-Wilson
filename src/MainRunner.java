import java.io.IOException;
import model.FileType;
import model.ImageProcessorModel;
import model.SimpleImageProcessorModel;
import model.image.CheckerboardImage;

public class MainRunner {

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
    model.applyFilter("sepia");
    try {
      model.export(FileType.PPM, "sepia.ppm");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
