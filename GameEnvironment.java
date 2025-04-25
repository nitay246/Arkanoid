import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The type Game environment.
 *
 * @author Nitay Damti 209458413
 * @version 19.0.2
 * @since 2023-14-06
 */
public class GameEnvironment {

    private final List<Collidable> collidables;

    /**
     * Instantiates a new Game environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }
    /**
     * Gets collidables.
     *
     * @return the collidables
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * Gets closest collision.
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // Create a list to store all possible collision information
        List<CollisionInfo> collisionInfoList = new ArrayList<>();

        // Loop through all collidables and check if they intersect with the given trajectory
        for (Collidable c : collidables) {
            Rectangle rect = c.getCollisionRectangle();
            Point intersection = trajectory.closestIntersectionToStartOfLine(rect);

            // If the trajectory intersects with the collidable, add collision information to the list
            if (intersection != null) {
                collisionInfoList.add(new CollisionInfo(intersection, c));
            }
        }

        // If no collision has been found, return null
        if (collisionInfoList.isEmpty()) {
            return null;
        }

        // Use stream and comparator to find the closest collision point to the start of the trajectory
        return collisionInfoList.stream()
                .min(Comparator.comparingDouble(collisionInfo -> trajectory
                        .start().distance(collisionInfo.collisionPoint()))).get();
    }

}