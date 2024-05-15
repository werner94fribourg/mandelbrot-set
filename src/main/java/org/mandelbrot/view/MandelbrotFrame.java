package org.mandelbrot.view;

import org.mandelbrot.controller.ImageHandler;
import org.mandelbrot.view.container.ImageContainer;

import javax.swing.*;

/**
 * The main frame of the running application.
 */
public class MandelbrotFrame extends JFrame {
    /**
     * The Constructor function of the MandelbrotFrame class.
     * @param title The title we give to the frame.
     */
    public MandelbrotFrame(String title) {
        super(title);
        ImageContainer imageContainer = new ImageContainer();

        this.getContentPane().add(imageContainer);
        this.pack();

        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        ImageHandler handler = new ImageHandler(imageContainer);
        imageContainer.addMouseListener(handler);
        imageContainer.addMouseMotionListener(handler);
        this.addKeyListener(handler);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        imageContainer.calculateImage();
    }
}
