package org.mandelbrot.controller;

import org.mandelbrot.utils.Constants;
import org.mandelbrot.view.container.ImageContainer;
import org.mandelbrot.view.rectangle.Rectangle;

import javax.swing.*;
import java.awt.event.*;

/**
 * The controller for all user interactions in the image (key press, click, drag, ...).
 */
public class ImageHandler extends MouseAdapter implements KeyListener {
    /**
     * The starting x position when the user begins dragging on the image.
     */
    private int startX;

    /**
     * The starting y position when the user begins dragging on the image.
     */
    private int startY;

    /**
     * The ending x position when the user begins dragging on the image.
     */
    private int endX;

    /**
     * The ending y position when the user begins dragging on the image.
     */
    private int endY;

    /**
     * The associated image container the handler listens to and trigger the redrawing.
     */
    private final ImageContainer image;

    /**
     * Constructor function for the ImageHandler class.
     * @param image The associated image container the handler listens to and trigger the redrawing.
     */
    public ImageHandler(ImageContainer image) {
        this.image = image;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(SwingUtilities.isLeftMouseButton(e)) {
            startX = e.getX();
            startY = e.getY();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(!SwingUtilities.isLeftMouseButton(e) || (e.getModifiersEx() & InputEvent.SHIFT_DOWN_MASK) == InputEvent.SHIFT_DOWN_MASK) {
            return;
        }
        endX = e.getX();
        endY = e.getY();
        Rectangle rect = new Rectangle(startX, endX, startY, endY);
        image.setRect(rect);
        image.drawRect();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        endX = e.getX();
        endY = e.getY();
        if(!SwingUtilities.isLeftMouseButton(e)) {
            return;
        }
        if((e.getModifiersEx() & InputEvent.SHIFT_DOWN_MASK) == InputEvent.SHIFT_DOWN_MASK) {
            image.setLeft(getLeftPosition(endX - (double) Constants.WIDTH / 2));
            image.setTop(getTopPosition(endY- (double) Constants.HEIGHT / 2));
            image.calculateImage();
            return;
        }
        int topX = Math.min(endX, startX);
        int topY = Math.min(endY, startY);

        int dx = Math.abs(endX - startX);
        int dy = Math.abs(endY - startY);

        int width = Constants.WIDTH;
        int height = Constants.HEIGHT;

        if(e.getButton() == MouseEvent.BUTTON1) {
            if(endX == startX && endY == startY) {
                zoomIn(topX, topY);
            } else {
                image.setLeft(getLeftPosition(topX));
                image.setTop(getTopPosition(topY));
                double newZoom = ((double) Math.max(dx, dy) / (dx > dy ? width: height)) * image.getZoom();
                image.setZoom(newZoom);
            }
        }
        image.setRect(null);
        image.calculateImage();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'i':
                zoomIn((int) image.getXCenter(), (int) image.getYCenter());
                break;
            case 'o':
                zoomOut((int) image.getXCenter(), (int) image.getYCenter());
                break;
            case 'p':
                image.incrementColorPalette();
                break;
            case 'c':
                image.incrementNbSteps();
                break;
            case 't':
                image.incrementNbThreads();
                break;
            default:
                return;
        }
        image.calculateImage();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            image.reset();
            image.calculateImage();
            return;
        }

        if(!e.isShiftDown()) {
            return;
        }

        if(e.isShiftDown()) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_P:
                    image.decrementColorPalette();
                    break;
                case KeyEvent.VK_C:
                    image.decrementNbSteps();
                    break;
                case KeyEvent.VK_T:
                    image.decrementNbThreads();
                    break;
                default:
                    return;
            }
            image.calculateImage();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    /**
     * Helper function used to get the top position in the image of a certain coordinate.
     * @param y The y coordinate from which we want to retrieve the top position.
     * @return The top position in the image of the coordinate.
     */
    private double getTopPosition(double y) {
        return image.getTop() + y * image.getZoom();
    }

    /**
     * Helper function used to get the left position in the image of a certain coordinate.
     * @param x The x coordinate from which we want to retrieve the left position.
     * @return The left position in the image of the coordinate.
     */
    private double getLeftPosition(double x) {
        return image.getLeft() + x * image.getZoom();
    }

    /**
     * Function used to zoom out on the image.
     * @param topX The center x coordinate from which we want to zoom out.
     * @param topY The center y coordinate from which we want to zoom out.
     */
    private void zoomOut(int topX, int topY) {
        image.setLeft(getLeftPosition(topX - Constants.WIDTH));
        image.setTop(getTopPosition(topY - Constants.HEIGHT));
        image.setZoom(image.getZoom() * 2);
    }

    /**
     * Function used to zoom in on the image.
     * @param topX The center x coordinate from which we want to zoom in.
     * @param topY The center y coordinate from which we want to zoom in.
     */
    private void zoomIn(int topX, int topY) {
        image.setLeft(getLeftPosition(topX - (double) Constants.WIDTH / 4));
        image.setTop(getTopPosition(topY - (double) Constants.HEIGHT / 4));
        image.setZoom(image.getZoom() / 2);
    }
}
