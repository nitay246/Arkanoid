import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Game.
 *
 * @author Nitay Damti 209458413
 * @version 19.0.2
 * @since 2023-14-06
 */
public class GameLevel implements Animation {
    public static final int SCREEN_WIDTH = 800, PADDLE_HEIGHT = 12, PADDLE_Y = 580, HUNDRED = 100;
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final biuoop.KeyboardSensor keyboardSensor;
    private final Counter blockCounter, ballsCounter, scoreCounter;
    private final AnimationRunner runner;
    private boolean running = true;
    private final LevelInformation levelInformation;


    /**
     * Instantiates a new Game.
     *
     * @param levelInformation the level information
     * @param animationRunner  the animation runner
     * @param keyboardSensor   the keyboard sensor
     * @param scoreCounter     the score counter
     */
    public GameLevel(LevelInformation levelInformation, AnimationRunner animationRunner,
                     KeyboardSensor keyboardSensor, Counter scoreCounter) {
        this.levelInformation = levelInformation;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.runner = animationRunner;
        this.keyboardSensor = keyboardSensor;
        this.blockCounter = new Counter(levelInformation.numberOfBlocksToRemove());
        this.ballsCounter = new Counter(levelInformation.numberOfBalls());
        this.scoreCounter = scoreCounter;
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize.
     * Initialize the game.
     */
    public void initialize() {
        // Create the game environment
        BlockRemover blockRemover = new BlockRemover(this, blockCounter);
        BallRemover ballRemover = new BallRemover(this, ballsCounter);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(scoreCounter);

        // Create the paddle and add it to the game
        int paddleWidth = levelInformation.paddleWidth();
        Paddle paddle = new Paddle(new Rectangle(new Point(SCREEN_WIDTH / 2.0 - (0.5 * paddleWidth), PADDLE_Y),
                paddleWidth, PADDLE_HEIGHT), keyboardSensor, this.levelInformation.paddleColor(),
                this.levelInformation.paddleSpeed());
        paddle.addToGame(this);


        // Loop to create the rows of blocks
        for (int i = 0; i < levelInformation.numberOfBlocksToRemove(); i++) {
                Block block = levelInformation.blocks().get(i);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTrackingListener);
                block.addToGame(this);
        }

        // Create the four boundary blocks and add them to the game
        Rectangle upper = new Rectangle(new Point(0, 20), 800, 20);
        Rectangle left = new Rectangle(new Point(0, 40), 20, 560);
        Rectangle bottom = new Rectangle(new Point(20, 610), 760, 20);
        Rectangle right = new Rectangle(new Point(780, 40), 20, 560);

        ScoreIndicator scoreIndicator = new ScoreIndicator(scoreTrackingListener, levelInformation.levelName());
        scoreIndicator.addToGame(this);

        Block block1 = new Block(upper, Color.gray);
        Block block2 = new Block(left, Color.gray);
        Block block3 = new Block(bottom, Color.gray);
        Block block4 = new Block(right, Color.gray);
        block3.addHitListener(ballRemover);

        List<Block> blocks = new ArrayList<>(Arrays.asList(block1, block2, block3, block4));
        // Add all blocks to the game
        for (Block b : blocks) {
            b.addToGame(this);
        }


    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    void removeCollidable(Collidable c) {
        this.environment.getCollidables().remove(c);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    void removeSprite(Sprite s) {
        this.sprites.getSpritesList().remove(s);
    }

    /**
     * Run.
     * The main game loop that runs continuously and updates the game state and rendering
     * at a fixed rate of 60 frames per second.
     */
    public void run() {
        this.createBallsOnTopOfPaddle(); // or a similar method
        this.runner.run(new CountdownAnimation(3, this.sprites, this.levelInformation));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    private void createBallsOnTopOfPaddle() {
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball(new Point(SCREEN_WIDTH / 2.0, 560), 5, Color.WHITE);
            ball.setEnvironment(this.environment);
            ball.setVelocity(this.levelInformation.initialBallVelocities().get(i));
            ball.addToGame(this);
        }
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // Get the DrawSurface object from the GUI and fill it with a blue rectangle
        this.levelInformation.getBackground().drawOn(d);

        // Draw all sprites onto the DrawSurface, show it on the GUI, and notify all sprites of the time passed
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.blockCounter.getValue() == 0) {
            this.scoreCounter.increase(HUNDRED);
            this.running = false;
        }
        if (this.ballsCounter.getValue() == 0) {
            this.running = false;
        }
        if (this.keyboardSensor.isPressed("p")) {
            Animation pause = new PauseScreen();
            Animation keyPress = new KeyPressStoppableAnimation(this.keyboardSensor, "space", pause);
            this.runner.run(keyPress);
        }
    }

    /**
     * Gets balls counter.
     *
     * @return the balls counter
     */
    public Counter getBallsCounter() {
        return ballsCounter;
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

}