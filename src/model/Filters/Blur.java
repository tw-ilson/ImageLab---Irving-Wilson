package model.Filters;

import java.util.Objects;
import model.image.Image;

public class Blur implements Filter {
  @Override
  public Image apply(Image i) {
    Objects.requireNonNull(i);

    Image toReturn = null;

    return toReturn;
    // execute matrix transformation
  }
}
