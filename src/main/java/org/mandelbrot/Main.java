package org.mandelbrot;

import org.mandelbrot.utils.Constants;
import org.mandelbrot.view.MandelbrotFrame;
import java.awt.*;

/**
 * The main running program for the Mandelbrot Set Project. It took some inspirations from existing solutions.
 *
 * @see <a href="https://www.reddit.com/r/java/comments/p38yqq/java_threads_and_the_mandelbrot_set">Inspiring reddit Post</a>
 * @see <a href="https://github.com/catree/SimpleMandelbrotDemo">Inspiring Github Project</a>
 * @see <a href="https://introcs.cs.princeton.edu/java/32class/Mandelbrot.java.html">Princeton University Mandelbrot Main Program</a>
 * @see <a href="https://introcs.cs.princeton.edu/java/32class/Complex.java.html">Princeton University Mandelbrot Class for Complex Number Representation</a>
 */
public class Main {

    /**
     * The main execution function of the application.
     * @param args The console arguments passed to the function.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new MandelbrotFrame("The Mandelbrot Set");
        });
    }
}