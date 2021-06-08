package model;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import model.Filters.Blur;
import model.Filters.IFilter;
import model.Filters.Sharpen;
import model.IO.AbstractImageProcessorIO;

public abstract class AbstractImageProcessorModel extends AbstractImageProcessorIO implements
    ImageProcessorModel {
  final static Map<String, IFilter> filters = new HashMap<>();
  public AbstractImageProcessorModel() {
    filters.put("blur", new Blur());
    filters.put("sharpen", new Sharpen());
  }

}
