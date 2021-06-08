package model.Filters;

import java.util.Objects;
import model.image.Image;

public class Sharpen implements IFilter{
  @Override
  public void apply(Image i) {
    Objects.requireNonNull(i);

    Image toReturn = null;

    // execute matrix transformation
    for(int j = 0; j < i.getHeight(); j++){
    }
  }
}
