package org.mandelbrot.utils;

import org.mandelbrot.model.Complex;

import java.util.Map;

/**
 * Store of all global utility functions used in the application. The class is final to avoid heritage.
 */
public final class Utils {

    /**
     * Private constructor for the Utils class to avoid instantiation.
     */
    private Utils() {
        throw new AssertionError("Non-instantiable class.");
    }

    /**
     * Utility function used to compute the mandelbrot algorithm from a starting complex number and return the resulting associated color.
     * @param z0 The complex number for which we want to compute the power.
     * @param detail The maximal power we want to compute for the complex number
     * @param activePalette The active color palette we want to use to compute the color
     * @return The color to display at the emplacement of the complex number.
     */
    public static int getMandelbrotPixelColor(Complex z0, int detail, int activePalette) {
        Map<Complex, Integer> map = z0.computeMandelbrot(detail);
        Complex end = map.keySet().stream().findAny().orElse(null);

        if(end == null) return -1;
        int finalValue = map.get(end);

        int color = 0x000000;
        int[] palette = Constants.PALETTES[activePalette];

        if(end.mod() > 2) {
            color = palette[finalValue % 64];
        }
        return color;
    }
}
