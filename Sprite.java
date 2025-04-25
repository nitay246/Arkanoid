import biuoop.DrawSurface;

/**
 * The interface Sprite.
 *
 * @author Nitay Damti 209458413
 * @version 19.0.2
 * @since 2023-14-06
 */
public interface Sprite {
    /**
     * Draw on.
     *
     * @param d the d
     */
    void drawOn(DrawSurface d);

    /**
     * Time passed.
     */
    void timePassed();
}