import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The type Score indicator.
 *
 * @author Nitay Damti 209458413
 * @version 19.0.2
 * @since 2023-14-06
 */
public class ScoreIndicator implements Sprite {
    /**
     * The constant HEIGHT.
     */
    public static final int HEIGHT = 20, /**
     * The Width.
     */
    WIDTH = 800, /**
     * The Font size.
     */
    FONT_SIZE = 15, /**
     * The Two divider.
     */
    TWO_DIVIDER = 2;

    private final ScoreTrackingListener scoreCounter;
    private final String levelName;

    /**
     * Instantiates a new Score indicator.
     *
     * @param scoreCounter the score counter
     * @param levelName    the level name
     */
    public ScoreIndicator(ScoreTrackingListener scoreCounter, String levelName) {
        this.scoreCounter = scoreCounter;
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        Point p1 = new Point(0, 0);
        Rectangle score = new Rectangle(p1, WIDTH, HEIGHT);
        Block scoreBlock = new Block(score, Color.white);
        //Draw rectangle
        d.fillRectangle((int) scoreBlock.getCollisionRectangle().getUpperLeft().getX(),
                (int) (scoreBlock.getCollisionRectangle().getUpperLeft().getY()),
                (int) scoreBlock.getCollisionRectangle().getWidth(),
                (int) scoreBlock.getCollisionRectangle().getHeight());
        // Set the color for the text
        d.setColor(Color.black);
        int x = (int) (scoreBlock.getCollisionRectangle().getUpperLeft().getX()
                + scoreBlock.getCollisionRectangle().getWidth() / TWO_DIVIDER);
        int y = (int) (scoreBlock.getCollisionRectangle().getUpperLeft().getY()
                + scoreBlock.getCollisionRectangle().getHeight() / 1.2);
        String scoreText = "Score: " + scoreCounter.getScore().getValue();
        String levelText = "Level Name: " + this.levelName;
        d.drawText(x, y, scoreText, FONT_SIZE);
        d.drawText(x + 200, y, levelText, FONT_SIZE);
    }

    @Override
    public void timePassed() {
    }

    /**
     * Add to game.
     *
     * @param game the game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
