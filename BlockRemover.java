/**
 * The type Block remover.
 *
 * @author Nitay Damti 209458413
 * @version 19.0.2
 * @since 2023-14-06
 */
public class BlockRemover implements HitListener {
    public static final int DECREASE_ONE = 1;
    private final GameLevel game;
    private final Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param game          the game
     * @param removedBlocks the removed blocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(DECREASE_ONE);
    }
}