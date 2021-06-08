package model.Filters;

import model.Image;
import model.ImageProcessorModel;

public interface IFilter {
  public void apply(Image i);
  int max = 255;
  int min = 0;
}
