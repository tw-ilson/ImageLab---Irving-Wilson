package model;

import java.io.FileNotFoundException;
import model.image.Image;


public abstract class AbstractImageProcessorModel implements ImageProcessorModel, ImageProcessorIO {

  protected Image sourceImage;

  @Override
  public void importImage(String fileName) throws IllegalArgumentException {
    try {
      sourceImage = ImageUtil.readPPM(fileName);
    } catch (FileNotFoundException e) {
      System.out.println("File \"" + fileName + "\" can not be found.");
    }
  }

}
