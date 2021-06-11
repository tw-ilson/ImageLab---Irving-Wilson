package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import model.ColorUtils.Color;
import model.ColorUtils.LightColor;
import model.image.Image;
import model.image.SimpleImage;

public abstract class AbstractImageProcessorIO implements ImageProcessorIO {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static Image readPPM(String filename) throws FileNotFoundException {
    Scanner sc;

    sc = new Scanner(new FileInputStream(filename));

    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    //System.out.println("Maximum value of a color in this file (usually 256): " + maxValue);

    Color[] pixels = new LightColor[width * height];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        //System.out.println("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
        pixels[width * i + j] = new LightColor(r, g, b);
      }
    }

    for (Color c: pixels) {
      Objects.requireNonNull(c);
    }

    return new SimpleImage(pixels, width, height);
  }

  public static void writePPM(File file, Image image) throws IOException {
    if (!file.exists()) {
      throw new IOException();
    }

    FileWriter writer = new FileWriter(file);

    if (file.length() > 0) {
      writer.flush();
    }

    writer.append("P3" + System.lineSeparator());
    writer.append("# " + file.getName() + System.lineSeparator());
    writer.append(image.getWidth() + " " + image.getHeight() + System.lineSeparator());
    writer.append("" + 256 + System.lineSeparator());

    Color[] pixels = image.pixArray();
    for (Color i: pixels) {
      writer.append(
          i.getRed() + " " +
              i.getGreen() + " " +
              i.getBlue() + " ");
      writer.append(System.lineSeparator());
    }
    writer.close();
  }
}
