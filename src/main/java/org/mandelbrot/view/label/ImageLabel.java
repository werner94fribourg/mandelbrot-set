package org.mandelbrot.view.label;

import javax.swing.*;
import java.awt.*;

import org.mandelbrot.view.rectangle.Rectangle;

/**
 * The label containing the mandelbrot set display and the dragging rectangle.
 */
public class ImageLabel extends JLabel {
    private Rectangle rect = null;

    /**
     * The Constructor function of the ImageLabel class.
     * @param image The image icon we want to include in the label.
     */
    public ImageLabel(Icon image) {
        super(image);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        if(rect == null) return;
        g.drawRect(rect.getLeft(), rect.getTop(), rect.getWidth(), rect.getHeight());
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }
}
