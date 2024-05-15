package org.mandelbrot.model;

import java.util.HashMap;
import java.util.Map;

/**
 * The representation of a complex number.
 */
public class Complex {

    /**
     * The real part of the complex number.
     */
    private final double real;

    /**
     * The imaginary part of the complex number.
     */
    private final double image;

    /**
     * Constructor function for the Complex class.
     * @param real The real part of the complex number.
     * @param image The imaginary part of the complex number.
     */
    public Complex(double real, double image) {
        this.real = real;
        this.image = image;
    }

    /**
     * Function used to calculate the mod of the complex number.
     * @return The mod of the complex number
     */
    public double mod() {
        return Math.hypot(real, image);
    }

    /**
     * Function used to perform an addition between two complex numbers.
     * @param b The other complex number we want to add to the first one.
     * @return The resulting complex number of the addition.
     */
    public Complex add(Complex b) {
        double real = this.real + b.real;
        double image = this.image + b.image;
        return new Complex(real, image);
    }

    /**
     * Function used to perform a multiplication between two complex numbers.
     * @param b The other complex number we want to multiply to the first one.
     * @return The resulting complex number of the multiplication.
     */
    public Complex mult(Complex b) {
        double real = this.real * b.real - this.image * b.image;
        double image = this.real * b.image + this.image * b.real;
        return new Complex(real, image);
    }

    /**
     * Function used to compute the power of the complex number a certain number of time and stop before if the mod reaches a value bigger than 2.
     * @param maxSteps The power of the complex number we want to compute.
     * @return A map containing the value of the last computed power and the step where it stopped computation.
     */
    public Map<Complex, Integer> computeMandelbrot(int maxSteps) {
        Map<Complex, Integer> map = new HashMap<>();
        Complex z = new Complex(0, 0);
        int step = 0;
        while (step < maxSteps && z.mod() < 2) {
            z = z.mult(z).add(this);
            step++;
        }
        map.put(z, step);
        return map;
    }
}
