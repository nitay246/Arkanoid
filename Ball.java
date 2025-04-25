import biuoop.DrawSurface;

/**
 * The type Ball.
 *
 * @author Nitay Damti 209458413
 * @version 19.0.2
 * @since 2023-14-06
 */
public class Ball implements Sprite {
    private Point center;
    private final int r;
    private final java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment environment;

    /**
     * Instantiates a new Ball.
     *
     * @param center the center
     * @param r      the r
     * @param color  the color
     */
// constructor
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * Sets environment.
     *
     * @param environment the environment
     */
    public void setEnvironment(GameEnvironment environment) {
        this.environment = environment;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Sets velocity.
     *
     * @param v the v
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }
    /**
     * Draw the ball on the given DrawSurface.
     *
     * @param surface the surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        // Set the color of the ball on the surface
        surface.setColor(this.getColor());
        // Convert the center coordinates of the ball to integers
        int x = this.getX();
        int y = this.getY();
        // Fill a circle with the given coordinates and radius on the surface
        surface.fillCircle(x, y, this.getSize());
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Move one step.
     */
    public void moveOneStep() {
        // Calculate the new center point of the ball based on its current velocity
        Point newCenter = new Point(center.getX() + velocity.getDx(), center.getY() + velocity.getDy());
        // Check if the ball will collide with any of the obstacles in the game environment
        Line trajectory = new Line(center, newCenter);
        CollisionInfo collision = this.environment.getClosestCollision(trajectory);
        if (collision != null) {
            // If the ball collide with an obstacle, adjust its velocity
            this.velocity = collision.collisionObject().hit(this, collision.collisionPoint(), this.velocity);

        }
        // If the ball doesn't collide with an obstacle, simply move it to the new center point
        this.center = velocity.applyToPoint(center);
    }


    /**
     * Add to game.
     *
     * @param game the game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * Remove from game.
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
