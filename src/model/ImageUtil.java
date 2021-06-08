package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import model.ColorUtils.Color;
import model.image.Image;
import model.image.SimpleImage;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

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
    System.out.println("Maximum value of a color in this file (usually 256): " + maxValue);

    Color[] pixels = new Color[width * height];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        System.out.println("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
        pixels[height * i + j] = new Color(r, g, b);
      }
    }
    return new SimpleImage(pixels, width, height);
  }

  public static void writePPM(File file, Image image) throws IOException {
    if (!file.exists() || file.length() !=0) {
      throw new IOException();
    }
    FileWriter writer = new FileWriter(file);
    writer.append("P3" + System.lineSeparator());
    writer.append("# " + file.getName());
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
  }

  //demo main
  public static void main(String[] args) {
    String filename;

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "sample.ppm";
    }
    try {
      ImageUtil.readPPM(filename);
    } catch (FileNotFoundException e) {
      System.out.println("File "+filename+ " not found!");
    }
  }

}

