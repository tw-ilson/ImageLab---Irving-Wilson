package model.Filters;

import java.util.Objects;
import model.ImageProcessorModel;
import model.image.Image;

public class Blur implements IFilter{
  @Override
  public void apply(Image i) {
    Objects.requireNonNull(i);

    Image toReturn = null;

    // execute matrix transformation
  }
}
