import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Sprite collection.
 *
 * @author Nitay Damti 209458413
 * @version 19.0.2
 * @since 2023-14-06
 */
public class SpriteCollection {

    private final ArrayList<Sprite> spritesList;

    /**
     * Instantiates a new Sprite collection.
     */
    public SpriteCollection() {
        this.spritesList = new ArrayList<>();
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.spritesList.add(s);
    }

    /**
     * Notify all time passed.
     *
     * @return the sprites list
     */
    public ArrayList<Sprite> getSpritesList() {
        return this.spritesList;
    }

    /**
     * Notify all time passed.
     */
    public void notifyAllTimePassed() {
        List<Sprite> sprites = new ArrayList<>(this.spritesList);
        // call timePassed() on all sprites.
        for (Sprite s : sprites) {
            s.timePassed();
        }
    }

    /**
     * Draw all on.
     *
     * @param d the d
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : spritesList) {
            s.drawOn(d);
        }
    }
}