package model.Filters;

import java.util.Objects;
import model.ImageProcessorModel;
import model.image.Image;

public class Blur implements IFilter{
  @Override
  public Image apply(Image i) {
    Objects.requireNonNull(i);

    Image toReturn = null;

    return toReturn;
    // execute matrix transformation
  }
}
