/*
 * javac Temperature.java
 *
 */

public class Temperature implements Comparable<Temperature> {
    private final double degree;

    public Temperature(double degreeInput) {
        if (Double.isNaN(degreeInput)) throw new IllegalArgumentException("Input Illegal");
        this.degree = degreeInput;
    }

    public int compareTo(Temperature other) {
        double EPSILON = 0.1;
        if (this.degree < other.degree - EPSILON) return -1;
        if (this.degree > other.degree + EPSILON) return 1;
        return 0;
    }

    public String toString() {
        return "Degree -> " + this.degree;
    }
}
