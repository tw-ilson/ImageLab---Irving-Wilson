package model.processors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import model.image.Image;

public interface IProcessor {

  public Image read(String filename) throws FileNotFoundException;
  public void write(File file, Image image) throws IOException;

}
