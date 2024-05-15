package org.mandelbrot.view.rectangle;

/**
 * The rectangle we want to display in the frame when the user performs dragging.
 */
public class Rectangle {
    /**
     * The first x coordinate of the rectangle.
     */
    private final double x1;

    /**
     * The second x coordinate of the rectangle.
     */
    private final double x2;

    /**
     * The first y coordinate of the rectangle.
     */
    private final double y1;

    /**
     * The second y coordinate of the rectangle.
     */
    private final double y2;

    /**
     * The Constructor function of the Rectangle class.
     * @param x1 The first x coordinate of the rectangle.
     * @param x2 The second x coordinate of the rectangle.
     * @param y1 The first y coordinate of the rectangle.
     * @param y2 The second y coordinate of the rectangle.
     */
    public Rectangle(double x1, double x2, double y1, double y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    /**
     * Function used to get the left position of the rectangle (i.e. the smallest x).
     * @return The left position of the rectangle.
     */
    public int getLeft() {
        return (int) Math.min(x1, x2);
    }

    /**
     * Function used to get the top position of the rectangle (i.e. the smallest y).
     * @return The top position of the rectangle.
     */
    public int getTop() {
        return (int) Math.min(y1, y2);
    }

    /**
     * Function used to get the right position of the rectangle (i.e. the biggest x).
     * @return The right position of the rectangle.
     */
    public int getRight() {
        return (int) Math.max(x1, x2);
    }

    /**
     * Function used to get the bottom position of the rectangle (i.e. the biggest y).
     * @return The bottom position of the rectangle.
     */
    public int getBottom() {
        return (int) Math.max(y1, y2);
    }

    /**
     * Function to get the width of the rectangle.
     * @return The width of the rectangle.
     */
    public int getWidth() {
        return getRight() - getLeft();
    }

    /**
     * Function to get the height of the rectangle.
     * @return The height of the rectangle.
     */
    public int getHeight() {
        return getBottom() - getTop();
    }
}
