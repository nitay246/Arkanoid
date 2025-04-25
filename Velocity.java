/**
 * The type Velocity.
 * This class represents a 2D velocity vector with a horizontal component (dx)
 * and a vertical component (dy).
 * It provides methods for creating and manipulating velocity vectors.
 *
 * @author Nitay Damti 209458413
 * @version 19.0.2
 * @since 2023-14-06
 */
public class Velocity {
    public static final int ANGLE_REPAIR = 90;
    private final double dx;
    private final double dy;

    /**
     * Instantiates a new Velocity.
     *
     * @param dx the horizontal component of the velocity
     * @param dy the vertical component of the velocity
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Creates a new Velocity object from an angle and speed.
     *
     * @param angle the angle in degrees
     * @param speed the speed of the velocity vector
     * @return the created Velocity object
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // Convert angle to radians and calculate dx and dy
        double radians = Math.toRadians(angle - ANGLE_REPAIR);
        double dx = Math.cos(radians) * speed;
        double dy = Math.sin(radians) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Applies the velocity to a given point.
     *
     * @param p the point to which the velocity is applied
     * @return a new point with updated position after applying the velocity
     */
    public Point applyToPoint(Point p) {
        // Update the position of the point by adding the dx and dy
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * Gets the horizontal component of the velocity.
     *
     * @return the horizontal component of the velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets the vertical component of the velocity.
     *
     * @return the vertical component of the velocity
     */
    public double getDy() {
        return this.dy;
    }
}
