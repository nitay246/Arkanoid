import java.util.List;

/**
 * The type Line.
 *
 * @author Nitay Damti 209458413
 * @version 19.0.2
 * @since 2023-14-06
 */
public class Line {
    private final Point start, end;
    private final double epsilon = 1e-10;

    /**
     * Instantiates a new Line.
     *
     * @param start the start
     * @param end   the end
     */
// constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates a new Line.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Length double.
     *
     * @return the double
     */
    public double length() {
        return this.start.distance(end);
    }
    /**
     * Start point.
     *
     * @return the point
     */
    public Point start() {
        return this.start;
    }

    /**
     * End point.
     *
     * @return the point
     */
    public Point end() {
        return this.end;
    }

    /**
     * Is intersecting boolean.
     *
     * @param other the other
     * @return the boolean
     */
    public boolean isIntersecting(Line other) {
        Point p = intersectionWith(other);
        if (p != null) {
            return true;
        } else {
            return this.isInLine(other, this.start) || this.isInLine(other, this.end);
        }
    }

    /**
     * Is in line boolean.
     *
     * @param line the line
     * @param p    the p
     * @return the boolean
     */
    public boolean isInLine(Line line, Point p) {
        double minX = Math.min(line.start.getX(), line.end.getX());
        double maxX = Math.max(line.start.getX(), line.end.getX());
        double minY = Math.min(line.start.getY(), line.end.getY());
        double maxY = Math.max(line.start.getY(), line.end.getY());
        double x = p.getX();
        double y = p.getY();
        return (x > minX || (Math.abs(x - minX) < epsilon))
                && (x < maxX || Math.abs(x - maxX) < epsilon)
                && (y > minY || (Math.abs(y - minY) < epsilon))
                && (y < maxY || Math.abs(y - maxY) < epsilon);
    }

    /**
     * Intersection with point.
     *
     * @param other the other
     * @return the point
     */
    public Point intersectionWith(Line other) {
        // If the two lines are equal, there is no intersection
        if (this.equals(other)) {
            return null;
        }
        double x1 = start.getX();
        double y1 = start.getY();
        double x2 = end.getX();
        double y2 = end.getY();
        double x3 = other.start().getX();
        double y3 = other.start().getY();
        double x4 = other.end().getX();
        double y4 = other.end().getY();

        // Calculate the slopes of the two lines
        double slope1 = (y3 - y4) * (x1 - x2);
        double slope2 = (y1 - y2) * (x3 - x4);

        // Check if the slopes are equal, indicating that the lines are parallel
        if (Math.abs(slope1 - slope2) < (epsilon)) {
            // Calculate the overlapping segment of the two lines
            double xMin = Math.max(Math.min(x1, x2), Math.min(x3, x4));
            double xMax = Math.min(Math.max(x1, x2), Math.max(x3, x4));
            double yMin = Math.max(Math.min(y1, y2), Math.min(y3, y4));
            double yMax = Math.min(Math.max(y1, y2), Math.max(y3, y4));

            // Check if there is no overlap, if so, return null
            if (xMax < xMin || yMax < yMin) {
                return null;
            }

            // Create a new Line object representing the overlapping segment
            Line overlap = new Line(xMin, yMin, xMax, yMax);

            // Check if the overlapping segment is too short - indicating
            // partial convergence
            if (overlap.length() != 0 && (overlap.length() < this.length()
                    || overlap.length() < other.length())) {
                return null;
            }
            // Return the start point of the overlapping segment
            return overlap.start;

        } else {
            // Check if either endpoint of this line is on the other line
            if (this.start.equals(other.start) || this.start.equals(other.end)) {
                return this.start;
            } else if (this.end.equals(other.start) || this.end.equals(other.end)) {
                return this.end;
            }

            // If the lines are not parallel, calculate the intersection point
            double x = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4))
                    / (slope1 - slope2);
            double y = ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4))
                    / (slope1 - slope2);

            // Create a new Point object representing the intersection point
            Point p = new Point(x, y);

            // Check if the intersection point is within both lines, if not, return null
            if (this.isInLine(this, p) && this.isInLine(other, p)) {
                return p;
            } else {
                return null;
            }
        }
    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return the boolean
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end))
                || (this.end.equals(other.start) && this.start.equals(other.end));
    }

    /**
     * Finds the closest intersection point of a line with a given rectangle
     * to the starting point of the line.
     *
     * @param rect the rectangle to check for intersection
     * @return the closest intersection point to the starting point of the line,
     * or null if there are no intersections
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // Find all intersection points between the line and the rectangle
        List<Point> intersections = rect.intersectionPoints(this);
        // If there are no intersection points, return null
        if (intersections.isEmpty()) {
            return null;
        }

        // Find the closest intersection point to the starting point of the line
        Point closestPoint = null;
        double closestDistance = Double.MAX_VALUE;

        for (Point intersectionPoint : intersections) {
            if (intersectionPoint == null) {
                continue;
            }
            double distance = intersectionPoint.distance(this.start());
            if (distance < closestDistance) {
                closestPoint = intersectionPoint;
                closestDistance = distance;
            }
        }

        return closestPoint;
    }
}