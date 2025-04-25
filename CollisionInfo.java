/**
 * The type Collision info.
 *
 * @author Nitay Damti 209458413
 * @version 19.0.2
 * @since 2023-14-06
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * Instantiates a new Collision info.
     *
     * @param collisionPoint  the collision point
     * @param collisionObject the collision object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Collision point.
     *
     * @return the point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Collision object collidable.
     *
     * @return the collidable
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
