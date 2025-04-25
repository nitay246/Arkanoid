import biuoop.DrawSurface;

/**
 * The interface Animation.
 *
 * @author Nitay Damti 209458413
 * @version 19.0.2
 * @since 2023-14-06
 */

public interface Animation {
    /**
     * Do one frame.
     *
     * @param d the d
     */
    void doOneFrame(DrawSurface d);

    /**
     * Should stop boolean.
     *
     * @return the boolean
     */
    boolean shouldStop();
}