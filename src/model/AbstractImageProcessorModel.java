package model;

import java.io.FileNotFoundException;
import model.image.Image;


public abstract class AbstractImageProcessorModel extends AbstractImageProcessorIO implements ImageProcessorModel  {

  protected Image sourceImage;

  @Override
  public void importImage(String fileName) throws IllegalArgumentException {
    if (fileName == null) {
      throw new IllegalArgumentException("Cannot pass a null argument for filename.");
    }
    try {
      sourceImage = readPPM(fileName);
    } catch (FileNotFoundException e) {
      System.out.println("File \"" + fileName + "\" can not be found.");
    }
  }

}
