package controller;

import java.util.Scanner;
import view.ImageProcessorTextView;
import view.ImageProcessorView;

public class LayerModelInputHandler implements InputHandler {

  @Override
  public void scanInput(Readable in, Appendable out) {
    Scanner scan = new Scanner(in);
    ImageProcessorView view = new ImageProcessorTextView(out);
  }
}
