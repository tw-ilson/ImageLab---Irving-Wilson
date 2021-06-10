package model.Filters;

import java.util.function.Function;
import model.image.Image;

public interface Filter extends Function<Image, Image> {
  public Image apply(Image i);
  int MAX = 255;
  int MIN = 0;


  /*protected int getClamps(double sumRed, double sumGreen, double sumBlue) {
    if (sumRed > MAX) {
      sumRed = MAX;
    } else if (sumRed < MIN) {
      sumRed = MIN;
    }
    if (sumGreen > MAX) {
      sumGreen = MAX;
    } else if (sumGreen < MIN) {
      sumGreen = MIN;
    }
    if (sumBlue > MAX) {
      sumBlue = MAX;
    } else if (sumBlue < MIN) {
      sumBlue = MIN;
    }
  }*/

}
