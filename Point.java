/**
 * The type Point.
 * Represents a point in a two-dimensional Cartesian coordinate system.
 *
 * @author Nitay Damti 209458413
 * @version 19.0.2
 * @since 2023-14-06
 */
public class Point {
    /**
     * The X.
     */
    private final double x;
    /**
     * The Y.
     */
    private final double y;

    /**
     * Instantiates a new Point.
     *
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
// Constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance double.
     * Calculates and returns the distance between this point and another point.
     *
     * @param other the other point
     * @return the distance between this point and the other point
     */
    public double distance(Point other) {
        double x1 = this.x - other.x;
        double y1 = this.y - other.y;
        return Math.sqrt((x1 * x1) + (y1 * y1));
    }

    /**
     * Equals boolean.
     * Compares this point with another point for equality.
     *
     * @param other the other point
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        double epsilon = 1e-10;
        return (Math.abs(this.getX() - other.getX()) < epsilon) && (Math.abs(this.getY() - other.getY()) < epsilon);
    }

    /**
     * Gets x.
     *
     * @return the x coordinate of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return the y coordinate of this point
     */
    public double getY() {
        return this.y;
    }
}
