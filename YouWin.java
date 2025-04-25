import biuoop.DrawSurface;

/**
 * The type You win.
 *
 * @author Nitay Damti 209458413
 * @version 19.0.2
 * @since 2023-14-06
 */
public class YouWin implements Animation {
    private final Counter counter;

    /**
     * Instantiates a new You win.
     *
     * @param counter the counter
     */
    public YouWin(Counter counter) {
        this.counter = counter;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(d.getWidth() / 3, d.getHeight() / 2, "You win! Your score is " + counter.getValue(), 32);
    }
    @Override
    public boolean shouldStop() {
        return false;
    }
}