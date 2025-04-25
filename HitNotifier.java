/**
 * The interface Hit notifier.
 *
 * @author Nitay Damti 209458413
 * @version 19.0.2
 * @since 2023-14-06
 */

public interface HitNotifier {
    /**
     * Add hit listener.
     *
     * @param hl the hl
     */
// Add hl as a listener to hit events.
    void addHitListener(HitListener hl);

    /**
     * Remove hit listener.
     *
     * @param hl the hl
     */
// Remove hl from the list of listeners to hit events.
    void removeHitListener(HitListener hl);
}