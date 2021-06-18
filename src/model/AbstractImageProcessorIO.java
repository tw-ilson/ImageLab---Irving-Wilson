package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import model.color.Color;
import model.color.LightColor;
import model.image.Image;
import model.image.SimpleImage;

/**
 * Abstract class for IO operations in an Image Processor. Contains methods for reading and writing
 * to a PPM file (as of HW5).
 */
public abstract class AbstractImageProcessorIO implements ImageProcessorIO {

  protected static Image read(String filename) throws IOException {
    File imageFile = new File(filename);

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

    String token = sc.next();
    if (token.equals("P3")) {
      readPPM(sc);
    } else {
      readNotPPM(filename);
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());
    return null;
  }

  /**
   * writes
   *
   * @param filetype
   * @param toWrite
   * @param img
   * @throws IOException
   * @throws IllegalArgumentException
   */
  protected static void write(String filetype, File toWrite, Image img)
      throws IOException, IllegalArgumentException {

    if (!toWrite.exists()) {
      throw new IOException();
    }
    if (filetype.equals("ppm")) {
      writePPM(toWrite, img);
    } else {
      ImageWriter writer = ImageIO.getImageWritersByFormatName(filetype).next();

      if (toWrite.length() > 0) {
        writer.abort();
      }
    }
  }

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param sc a scanner for this ASCII PPM file.
   */
  protected static Image readPPM(Scanner sc) throws FileNotFoundException {

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
  protected static void writePPM(File file, Image image) throws IOException {

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
   * @return
   * @throws IOException
   */
  public static Image readNotPPM(String filename) throws IOException {
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

  protected String exportHelp(String filetype, String name, Image toExport) throws IOException {
    File toWrite = new File(name);
    toWrite.createNewFile();
    switch (filetype.toLowerCase()) {
      case "ppm":
        writePPM(toWrite, toExport);
        break;
      case "jpeg":
        write(filetype, toWrite, toExport);
      case "png":
        write(filetype, toWrite, toExport);
      default:
        throw new IOException("unsupported export type.");
    }
    return "Successfully exported " + filetype + " image: " + name;
  }

}



