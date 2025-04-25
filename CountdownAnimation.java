import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Countdown animation.
 *
 * @author Nitay Damti 209458413
 * @version 19.0.2
 * @since 2023-14-06
 */
public class CountdownAnimation implements Animation {
    public static final int DIVIDER = 2, SEC = 3;
    private final long startTime;
    private final int countFrom;
    private final SpriteCollection gameScreen;
    private boolean stop;
    private final LevelInformation levelInformation;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     * @param info         the info
     */
    public CountdownAnimation(int countFrom, SpriteCollection gameScreen, LevelInformation info) {
        this.levelInformation = info;
        this.startTime = System.currentTimeMillis();
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        double elapsedTime = (System.currentTimeMillis() - startTime) / 750.0;
        int currentNumber = (int) elapsedTime;


        // Draw the game screen
        this.levelInformation.getBackground().drawOn(d);
        gameScreen.drawAllOn(d);

        // Display the current countdown number
        d.setColor(Color.WHITE);
        if (currentNumber >= SEC) {
            d.drawText(d.getWidth() / DIVIDER, d.getHeight() / DIVIDER, "Go", 32);
        } else {
            d.drawText(d.getWidth() / DIVIDER, d.getHeight() / DIVIDER, String.valueOf(Math.abs(SEC - currentNumber)),
                    32);
        }
        // Check if the countdown is finished
        if (currentNumber > countFrom) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }
}
