package model;

import java.util.HashMap;
import java.util.Map;
import model.Filters.Blur;
import model.Filters.Filter;
import model.Filters.Sharpen;
import model.IO.AbstractImageProcessorIO;

public abstract class AbstractImageProcessorModel extends AbstractImageProcessorIO implements
    ImageProcessorModel {
  final static Map<String, Filter> filters = new HashMap<>();
  public AbstractImageProcessorModel() {
    filters.put("blur", new Blur());
    filters.put("sharpen", new Sharpen());
  }

}
