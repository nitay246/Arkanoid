/**
 * The type Score tracking listener.
 *
 * @author Nitay Damti 209458413
 * @version 19.0.2
 * @since 2023-14-06
 */
public class ScoreTrackingListener implements HitListener {
    /**
     * The constant SCORE_INCREASE.
     */
    public static final int SCORE_INCREASE = 5;
    private final Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(SCORE_INCREASE);
    }

    /**
     * Get score counter.
     *
     * @return the counter
     */
    public Counter getScore() {
        return currentScore;
    }
}
