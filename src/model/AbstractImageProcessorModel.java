package model;

import model.filters.FilterBuilder;

/**
 * Abstract class for an Image Processor model. Ensures that models have a FilterBuilder instance to
 * work with.
 */
public abstract class AbstractImageProcessorModel implements
    ImageProcessorModel {

  protected FilterBuilder builder = new FilterBuilder();
}
