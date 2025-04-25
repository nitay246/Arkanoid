import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Block.
 *
 * @author Nitay Damti 209458413
 * @version 19.0.2
 * @since 2023-14-06
 */
class Block implements Collidable, Sprite, HitNotifier {
    private final List<HitListener> hitListeners;
    public static final int RIGHT_LINE = 1, LEFT_LINE = 3, TOP_LINE = 0, BOTTOM_LINE = 2;
    private final Rectangle rect;
    private final java.awt.Color color;

    /**
     * Instantiates a new Block.
     *
     * @param rect  the rect
     * @param color the color
     */
    Block(Rectangle rect, java.awt.Color color) {
        this.rect = rect;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double newDx = currentVelocity.getDx();
        double newDy = currentVelocity.getDy();

        // Handle collision with top or bottom of paddle
        if (this.rect.getLines()[TOP_LINE].isInLine(this.rect.getLines()[TOP_LINE], collisionPoint) && newDy > 0
                || this.rect.getLines()[BOTTOM_LINE].isInLine(this.rect.getLines()[BOTTOM_LINE],
                collisionPoint) && newDy < 0) {
            newDy = -newDy;
        }

        // Handle collision with left or right side of paddle
        if (this.rect.getLines()[RIGHT_LINE].isInLine(this.rect.getLines()[RIGHT_LINE],
                collisionPoint) && newDx < 0
                || this.rect.getLines()[LEFT_LINE].isInLine(this.rect.getLines()[LEFT_LINE],
                collisionPoint) && newDx > 0) {
            newDx = -newDx;
        }
        notifyHit(hitter);
        // Return the new velocity
        return new Velocity(newDx, newDy);
    }

    /**
     * Add to game.
     *
     * @param game the game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        fillRec(d, this.rect);
    }

    /**
     * Fill rec.
     *
     * @param d    the d
     * @param rect the rect
     */
    static void fillRec(DrawSurface d, Rectangle rect) {
        d.fillRectangle((int) rect.getUpperLeft().getX(),
                (int) (rect.getUpperLeft().getY()),
                (int) rect.getWidth(), (int) rect.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) rect.getUpperLeft().getX(),
                (int) rect.getUpperLeft().getY(), (int) rect.getWidth(), (int) rect.getHeight());
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Remove from game.
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
}