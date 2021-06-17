package model;

import java.io.IOException;
import model.FileType;
import model.image.Image;

/**
 * This is an interface to handle Input/Output operations for an image Processing model. Enables
 * images to be imported and exported to and from the model in this API.
 */
public interface ImageProcessorIO {

  /**
   * Imports the specified image file to be stored locally.
   *
   * @throws IllegalArgumentException if the file passed as argument is not supported.
   */
  public void importImage(String filename) throws IllegalArgumentException;

  /**
   * Imports the specified image to be stored locally.
   *
   * @throws IllegalArgumentException if the image passed as argument is not valid.
   */
  public void importImage(Image image) throws IllegalArgumentException;

  /**
   * Exports the stored image as a ??? NOTE: possibly add enumerated argument to represent supported
   * file formats.
   * @param f the type of file to export as.
   * @param name the name of the image file to be created.
   * @return the compiled image
   * @throws IllegalStateException if the export requested is not possible
   */
  public String export(FileType f, String name) throws IllegalStateException, IOException;

}
