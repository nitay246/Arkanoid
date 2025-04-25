/**
 * The type Rectangle.
 *
 * @author Nitay Damti 209458413
 * @version 19.0.2
 * @since 2023-14-06
 */
public class Rectangle {
    /**
     * The constant RIGHT_LINE.
     */
    public static final int RIGHT_LINE = 1, LEFT_LINE = 3, TOP_LINE = 0, BOTTOM_LINE = 2, LINES_NUMBER = 4;
    private final double width, height;
    private final Point upperLeft;
    private final Line[] lines = new Line[LINES_NUMBER];

    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        // Create a new rectangle with location and width/height.
        this.height = height;
        this.width = width;
        this.upperLeft = upperLeft;
        Point upperRight = new Point(getUpperLeft().getX() + this.width, getUpperLeft().getY());
        Point downLeft = new Point(getUpperLeft().getX(), getUpperLeft().getY() + this.height);
        Point downRight = new Point(getUpperLeft().getX() + this.width, getUpperLeft().getY() + this.height);
        this.lines[TOP_LINE] = new Line(getUpperLeft(), upperRight);
        this.lines[RIGHT_LINE] = new Line(upperRight, downRight);
        this.lines[BOTTOM_LINE] = new Line(downLeft, downRight);
        this.lines[LEFT_LINE] = new Line(getUpperLeft(), downLeft);
    }

    /**
     * Intersection points java . util . list.
     *
     * @param line the line
     * @return the java . util . list
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        // Return a (possibly empty) List of intersection points with the specified line.
        java.util.List<Point> pointArray = new java.util.ArrayList<>();
        for (int i = 0; i < LINES_NUMBER; i++) {
            if (line.isIntersecting(this.lines[i])) {
                pointArray.add(line.intersectionWith(this.lines[i]));
            }
        }
        return pointArray;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Get lines line [ ].
     *
     * @return the line [ ]
     */
    public Line[] getLines() {
        return this.lines;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Gets upper right.
     *
     * @return the upper right
     */
    public Point getUpperRight() {
        return new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
    }

}