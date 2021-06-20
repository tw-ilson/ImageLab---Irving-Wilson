package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import model.filters.FilterBuilder;
import model.image.Image;

/**
 * Abstract class for an Image Processor model. Ensures that models have a FilterBuilder instance to
 * work with.
 */
public abstract class AbstractImageProcessorModel implements
    ImageProcessorModel {

  protected FilterBuilder builder = new FilterBuilder();
}
