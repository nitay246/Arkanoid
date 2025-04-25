import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type Paddle.
 *
 * @author Nitay Damti 209458413
 * @version 19.0.2
 * @since 2023-14-06
 */
public class Paddle implements Sprite, Collidable {
    /**
     * The constant RIGHT_LINE.
     */
    public static final int RIGHT_LINE = 1, /**
     * The Left line.
     */
    LEFT_LINE = 3, /**
     * The Top line.
     */
    TOP_LINE = 0, /**
     * The Right boundry.
     */
    RIGHT_BOUNDRY = 785,
    /**
     * The Left boundry.
     */
    LEFT_BOUNDRY = 15, /**
     * The Two.
     */
    TWO = 2, /**
     * The Three.
     */
    THREE = 3, /**
     * The Four.
     */
    FOUR = 4, /**
     * The Five.
     */
    FIVE = 5, /**
     * The Sixty angle.
     */
    SIXTY_ANGLE = 60, /**
     * The Thirty angle.
     */
    THIRTY_ANGLE = 30;

    private final biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private final Color color;
    /**
     * The Speed.
     */
    private final int speed;

    /**
     * Instantiates a new Paddle.
     *
     * @param rect     the rect
     * @param keyboard the keyboard
     * @param color    the color
     * @param speed    the speed
     */
    public Paddle(Rectangle rect, KeyboardSensor keyboard, Color color, int speed) {
        this.rect = rect;
        this.keyboard = keyboard;
        this.color = color;
        this.speed = speed;
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        double newPaddleX = this.rect.getUpperLeft().getX() - this.speed;
        if (newPaddleX < LEFT_BOUNDRY) {
            return;
        }
        double paddleY = this.rect.getUpperLeft().getY();
        this.rect = new Rectangle(new Point(newPaddleX, paddleY),
                this.rect.getWidth(), this.rect.getHeight());
    }

    /**
     * Move right.
     */
    public void moveRight() {
        double newPaddleX = this.rect.getUpperRight().getX() + this.speed;
        if (newPaddleX > RIGHT_BOUNDRY) {
            return;
        }
        double paddleY = this.rect.getUpperLeft().getY();
        this.rect = new Rectangle(new Point(this.rect.getUpperLeft().getX() + this.speed, paddleY),
                this.rect.getWidth(), this.rect.getHeight());
    }


    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(biuoop.KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (this.keyboard.isPressed(biuoop.KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        Block.fillRec(d, this.rect);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // current x-velocity of the ball
        double newDx = currentVelocity.getDx();
        // current y-velocity of the ball
        double newDy = currentVelocity.getDy();
        // the top line of the collidable object
        Line upperLine = this.rect.getLines()[TOP_LINE];
        // the length of one fifth of the top line
        double fifthLen = upperLine.length() / FIVE;
        // the speed of the ball
        double ballSpeed = Math.sqrt((newDx * newDx) + (newDy * newDy));

        // if the ball collided with the top line and is moving downwards
        if (upperLine.isInLine(upperLine, collisionPoint) && newDy > 0) {
            // change the angle of the ball's velocity based on where it hit the top line
            if (collisionPoint.getX() < upperLine.start().getX() + fifthLen) {
                return Velocity.fromAngleAndSpeed(-SIXTY_ANGLE, ballSpeed);
            } else if (collisionPoint.getX() >= upperLine.start().getX() + fifthLen
                    && collisionPoint.getX() < upperLine.start().getX() + (TWO * fifthLen)) {
                return Velocity.fromAngleAndSpeed(-THIRTY_ANGLE, ballSpeed);
            } else if (collisionPoint.getX() >= upperLine.start().getX() + (TWO * fifthLen)
                    && collisionPoint.getX() < upperLine.start().getX() + (THREE * fifthLen)) {
                return new Velocity(newDx, -newDy);
            } else if (collisionPoint.getX() >= upperLine.start().getX() + (THREE * fifthLen)
                    && collisionPoint.getX() < upperLine.start().getX() + (FOUR * fifthLen)) {
                return Velocity.fromAngleAndSpeed(THIRTY_ANGLE, ballSpeed);
            } else if (collisionPoint.getX() + (FOUR * fifthLen) >= upperLine.start().getX()) {
                return Velocity.fromAngleAndSpeed(SIXTY_ANGLE, ballSpeed);
            }
        }

        // if the ball collided with either the left or right line of the collidable object
        if (this.rect.getLines()[RIGHT_LINE].isInLine(this.rect.getLines()[RIGHT_LINE], collisionPoint) && newDx < 0
                || this.rect.getLines()[LEFT_LINE].isInLine(this.rect.getLines()[LEFT_LINE],
                collisionPoint) && newDx > 0) {
            // reverse the x-velocity of the ball
            newDx = -newDx;
        }

        // return the new velocity of the ball
        return new Velocity(newDx, newDy);
    }


    /**
     * Add to game.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}