package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import model.filters.FilterBuilder;
import model.image.Image;

/**
 * Abstract class for an Image Processor model. Provides a method to import image from file.
 */
public abstract class AbstractImageProcessorModel implements
    ImageProcessorModel {

  protected FilterBuilder builder = new FilterBuilder();
}
