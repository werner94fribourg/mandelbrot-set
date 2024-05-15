package org.mandelbrot.view.container;

import org.mandelbrot.model.Complex;
import org.mandelbrot.utils.Constants;
import org.mandelbrot.utils.Utils;
import org.mandelbrot.view.label.ImageLabel;
import org.mandelbrot.view.rectangle.Rectangle;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static org.mandelbrot.utils.Constants.*;

/**
 * The content of the frame, containing the mandelbrot set image and the actual status of the frame (number of threads, palette, ...).
 */
public class ImageContainer extends JPanel {
    /**
     * The y position of the top left corner of the image.
     */
    private double top = -1.13;

    /**
     * The x position of the top left corner of the image.
     */
    private double left = -2.0;

    /**
     * The zooming of the image.
     */
    private double zoom = 1.0 / INITIAL_ZOOM_SCALE;
    /**
     * The image that will be displayed the mandelbrot set.
     */
    private final BufferedImage image;

    /**
     * The number of steps to perform in the calculation to determine the color of a pixel in the mandelbrot set.
     */
    private int nbSteps;

    /**
     * The number of cores that are running to compute the mandelbrot set.
     */
    private int nbCores;

    /**
     * The thread pool from which we will execute the parallel computing.
     */
    private ForkJoinPool threadPool;

    /**
     * The total duration a computation has taken to display the mandelbrot set.
     */
    private long duration;

    /**
     * The bottom bar displaying the actual status of the mandelbrot set application (number of threads, display palette chosen, ...).
     */
    private final JLabel status;

    /**
     * The Label wrapper component for the mandelbrot set display.
     */
    private final ImageLabel imageLabel;

    /**
     * The currently chosen active color palette when drawing the mandelbrot set.
     */
    private static int activePalette = 0;

    /**
     * The Constructor function of the ImageContainer class.
     */
    public ImageContainer() {
        super();
        this.image = new BufferedImage(Constants.WIDTH, Constants.HEIGHT, BufferedImage.TYPE_INT_RGB);
        nbSteps = INITIAL_NB_STEPS;
        nbCores = MAX_NB_CORES / 2;
        threadPool = new ForkJoinPool(nbCores);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        imageLabel = new ImageLabel(new ImageIcon(image));
        this.add(imageLabel);
        status = new JLabel(String.format(STATUS_WRAPPER, "Init..."));
        this.add(status);
        this.updateStatus();
    }

    /**
     * Function used to compute the color value of all pixels in the image.
     */
    public final void calculateImage() {
        threadPool.execute(() -> {
            long startTime = System.nanoTime();
            IntStream.range(0, Constants.HEIGHT).parallel().forEach((int y) -> {
                double ci = y * zoom + top;
                IntStream.range(0, Constants.WIDTH).parallel().forEach((int x) -> {
                    double cr = x * zoom + left;
                    Complex origin = new Complex(cr, ci);
                    int color = Utils.getMandelbrotPixelColor(origin, nbSteps, activePalette);

                    if(color == -1) return;

                    image.setRGB(x, y, color);
                    this.getParent().repaint();
                });
            });
            duration = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime);
            this.updateStatus();
        });
    }

    /**
     * Function used to return the y position of the top left corner of the image.
     * @return The y position of the top left corner of the image.
     */
    public double getTop() {
        return top;
    }

    /**
     * Function used to modify the y position of the top left corner of the image.
     * @param top The new y position of the top left corner of the image.
     */
    public void setTop(double top) {
        this.top = top;
    }

    /**
     * Function used to return the x position of the top left corner of the image.
     * @return The x position of the top left corner of the image.
     */
    public double getLeft() {
        return left;
    }

    /**
     * Function used to modify the x position of the top left corner of the image.
     * @param left The new x position of the top left corner of the image.
     */
    public void setLeft(double left) {
        this.left = left;
    }

    /**
     * Function used to get the zooming of the image.
     * @return The zooming of the image.
     */
    public double getZoom() {
        return zoom;
    }

    /**
     * Function used to modify the zooming of the image.
     * @param zoom The new zooming of the image.
     */
    public void setZoom(double zoom) {
        this.zoom = zoom;
    }

    /**
     * Function used to get the x center of the image.
     * @return The x center of the image.
     */
    public double getXCenter() {
        return top + (double) Constants.WIDTH / 2;
    }

    /**
     * Function used to get the y center of the image.
     * @return The y center of the image.
     */
    public double getYCenter() {
        return left + (double) Constants.HEIGHT / 2;
    }

    /**
     * Function used to reinitialize the x and y top left corner position, the zooming and the number of steps to the initial ones.
     */
    public void reset() {
        top = -1.13;
        left = -2.0;
        zoom = 1.0 / INITIAL_ZOOM_SCALE;
        nbSteps = INITIAL_NB_STEPS;
    }

    /**
     * Function used to increment the number of steps to perform in the color calculation by 256 steps.
     */
    public void incrementNbSteps() {
        this.nbSteps += 256;
        this.updateStatus();
    }

    /**
     * Function used to decrement the number of steps to perform in the color calculation by 256 steps.
     */
    public void decrementNbSteps() {
        this.nbSteps -= 256;
        this.updateStatus();
    }

    /**
     * Function used to increment the number of running threads in the color calculation by doubling it.
     */
    public void incrementNbThreads() {
        if(this.nbCores == MAX_NB_CORES) return;
        if(this.nbCores < 3) this.nbCores++;
        else this.nbCores *= 2;
        threadPool.shutdown();
        threadPool = new ForkJoinPool(nbCores);
        this.updateStatus();
    }

    /**
     * Function used to decrement the number of running threads in the color calculation by halving it.
     */
    public void decrementNbThreads() {
        if(this.nbCores == 1) return;
        if(this.nbCores > 3) this.nbCores /= 2;
        else this.nbCores--;

        threadPool.shutdown();
        threadPool = new ForkJoinPool(nbCores);
        this.updateStatus();
    }

    /**
     * Function used to set the drawing rectangle when performing dragging.
     * @param rect The rectangle we want to draw on the image.
     */
    public void setRect(Rectangle rect) {
        imageLabel.setRect(rect);
    }

    /**
     * Function used to repaint the graphic once the rectangle has been drawn.
     */
    public void drawRect() {
        this.getParent().repaint();
    }

    /**
     * Function used to modify the chosen palette, by selecting the next one.
     */
    public void incrementColorPalette() {
        if(activePalette == 3) return;
        ++activePalette;
    }

    /**
     * Function used to modify the chosen palette, by selecting the previous one.
     */
    public void decrementColorPalette() {
        if(activePalette == 0) return;
        --activePalette;
    }

    /**
     * Function used to update the text that is written in the status bar.
     */
    public void updateStatus() {
        this.status.setText(String.format(STATUS_WRAPPER,String.format("Size: %dx%d - [T]=%d - [C]=%d - [P]=%d - Time=%dms", Constants.WIDTH, Constants.HEIGHT, this.nbCores, this.nbSteps, activePalette + 1, duration)));
    }

}
