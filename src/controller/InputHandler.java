package controller;

/**
 * Handles inputs in the form of a Readable. Different implementations of this interface are created
 * depending on the
 */
public interface InputHandler {

  /**
   * scans input and executes requested operations.
   * @param in
   * @param out
   */
  void scanInput(Readable in, Appendable out);
}
