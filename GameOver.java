import biuoop.DrawSurface;

/**
 * The type Game over.
 *
 * @author Nitay Damti 209458413
 * @version 19.0.2
 * @since 2023-14-06
 */
public class GameOver implements Animation {
    private final Counter counter;

    /**
     * Instantiates a new Game over.
     *
     * @param counter the counter
     */
    public GameOver(Counter counter) {
        this.counter = counter;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(d.getHeight() / 3, d.getHeight() / 2, "Game Over. Your score is " + counter.getValue(), 32);
    }
    @Override
    public boolean shouldStop() {
        return false;
    }
}