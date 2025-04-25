/**
 * The type Ball remover.
 *
 * @author Nitay Damti 209458413
 * @version 19.0.2
 * @since 2023-14-06
 */
public class BallRemover implements HitListener {
    public static final int DECREASE_ONE = 1;
    private final GameLevel game;
    private final Counter remainingBalls;

    /**
     * Instantiates a new Ball remover.
     *
     * @param game          the game
     * @param removedBlocks the removed blocks
     */
    public BallRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBalls = removedBlocks;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(DECREASE_ONE);
    }
}