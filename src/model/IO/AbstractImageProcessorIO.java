package model.IO;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import model.FileType;
import model.ImageUtil;
import model.image.Image;

public abstract class AbstractImageProcessorIO implements ImageProcessorIO{

  Image sourceImage;

  @Override
  public void importImage(String  fileName) throws IllegalArgumentException {
    try {
      sourceImage = ImageUtil.readPPM(fileName);
    } catch (FileNotFoundException e) {
      System.out.println("File \"" + fileName +"\" can not be found.");
    }
  }
}
