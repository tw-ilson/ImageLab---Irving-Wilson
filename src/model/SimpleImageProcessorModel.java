package model;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Stack;
import model.image.Image;

public class SimpleImageProcessorModel extends AbstractImageProcessorModel{
  Stack<Image> imageVersions;

  @Override
  public Color[] getImageState() throws IllegalStateException {
    return imageVersions.peek().pixArray();
  }

  @Override
  public void applyFilter(String filter) {

  }

  @Override
  public String export(FileType f, String name) throws IllegalStateException {
    try {
      File toWrite = new File(name);
      switch (f) {
        case PPM:
          toWrite.createNewFile();
          ImageUtil.writePPM(toWrite, imageVersions.peek());
          System.out.println("Successfully saved new " + f + " file: " + name);
      }
    } catch (IOException e) {

    }
  }
}
