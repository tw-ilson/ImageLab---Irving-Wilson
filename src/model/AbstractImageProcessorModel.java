package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import model.image.Image;

/**
 * Abstract class for an Image Processor model. Provides a method to import image from file.
 */
public abstract class AbstractImageProcessorModel extends AbstractImageProcessorIO implements
    ImageProcessorModel {

  protected Image sourceImage;

  @Override
  public void importImage(String fileName) throws IllegalArgumentException {
    try {
      sourceImage = readJPG(fileName);
    } catch (IOException e) {
      System.out.println("File \"" + fileName + "\" can not be found.");
    }
  }

  @Override
  public void importImage(Image image) throws IllegalArgumentException {
    this.sourceImage = image;
  }
}
