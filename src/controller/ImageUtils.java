package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import javax.imageio.ImageIO;
import model.color.Color;
import model.color.LightColor;
import model.image.Image;
import model.image.SimpleImage;

/**
 * This is an interface to handle Input/Output operations for an image Processing model. Enables
 * images to be imported and exported to and from the model in this API.
 */
public class ImageUtils {

  /**
   * Reads a file specified by the path passed as argument.
   *
   * @param filename the name of the file.
   * @return the image read from this file.
   * @throws IOException if an io exception occurs.
   */
  public static Image read(String filename) throws IOException {
    Scanner sc;
    sc = new Scanner(new FileInputStream(filename));

    StringBuilder builder = new StringBuilder();

    String token;
    if (sc.hasNext()) {
      token = sc.next();
    } else {
      throw new IOException("File is empty. Aborting.");
    }
    while (token.startsWith("#")) {
      if (sc.hasNext()) {
        token = sc.next();
      } else {
        throw new IOException("This file contains only comments!??");
      }
    }

    if (token.equals("P3")) {
      return readPPM(sc);
    } else {
      return readNotPPM(filename);
    }
  }

  /**
   * writes to a file of the specified type and name using the image provided. Used to 'save'.
   *
   * @param filetype the type of file to write, lowercase (ie. "jpeg" "ppm" "png").
   * @param filename the name of the file to write.
   * @param img      the Image to write into the file.
   * @throws IOException              if an IO error occurs
   * @throws IllegalArgumentException if any of the arguments are null
   */
  public static String write(String filetype, String filename, Image img)
      throws IOException, IllegalArgumentException {
    if (img == null || filetype == null || filename == null) {
      throw new IllegalArgumentException("Cannot pass null arguments to this method.");
    }
    File toWrite = new File(filename);
    toWrite.createNewFile();
    if (!toWrite.exists()) {
      throw new IOException();
    }
    if (filetype.equals("ppm")) {
      writePPM(toWrite, img);
    } else {
      BufferedImage buf = new BufferedImage(img.getWidth(), img.getHeight(),
          BufferedImage.TYPE_INT_RGB);
      int[] rgbArray = Arrays.stream(img.pixArray())
          .mapToInt(color -> color.getRGB()
              & 0xffffff) //extracts bytes from Colors ands sets Alpha to 100%
          .toArray();
      buf.setRGB(0, 0, buf.getWidth(), buf.getHeight(), rgbArray, 0, buf.getWidth());
      ImageIO.write(buf, filetype, toWrite);
    }

    return "Successfully exported " + filetype + " image: " + filename;
  }


  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param sc a scanner for this ASCII PPM file.
   */
  private static Image readPPM(Scanner sc) throws FileNotFoundException {

    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (!s.isBlank() && s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    int width = sc.nextInt();
    //System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    //System.out.println("Height of image: " + height);
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

    for (Color c : pixels) {
      Objects.requireNonNull(c);
    }

    return new SimpleImage(pixels, width, height);
  }

  /**
   * Writes the contents of an image to PPM file.
   *
   * @param file  the file to write to
   * @param image the image to write
   * @throws IOException if an IO error occurs
   */
  private static void writePPM(File file, Image image) throws IOException {
    if (image == null || file == null) {
      throw new IllegalArgumentException("Cannot pass null arguments.");
    }
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
    for (Color i : pixels) {
      writer.append(
          i.getRed() + " " +
              i.getGreen() + " " +
              i.getBlue() + " ");
      writer.append(System.lineSeparator());
    }
    writer.close();
  }

  /**
   * Reads a file that is not of type PPM. (Uses {@link ImageIO}, defer to that for information on
   * supported types
   *
   * @param filename
   * @return the Image gotten from this file.
   * @throws IOException if an io error occurs
   */
  private static Image readNotPPM(String filename) throws IOException {
    BufferedImage image = ImageIO.read(new FileInputStream(filename));
    Color[] toEdit = new LightColor[image.getWidth() * image.getHeight()];
    Image toReturn = new SimpleImage(toEdit, image.getWidth(), image.getHeight());
    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        toEdit[j * image.getWidth() + i] = new LightColor(image.getRGB(i, j));
      }
    }
    return toReturn;
  }
}
