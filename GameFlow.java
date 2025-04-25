
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * The type Game flow.
 *
 * @author Nitay Damti 209458413
 * @version 19.0.2
 * @since 2023-14-06
 */
public class GameFlow {

    private final AnimationRunner animationRunner;
    private final biuoop.KeyboardSensor keyboardSensor;
    private final Counter scoreCounter;

    /**
     * Instantiates a new Game flow.
     *
     * @param animationRunner the animation runner
     * @param keyboardSensor  the keyboard sensor
     */
    public GameFlow(AnimationRunner animationRunner, KeyboardSensor keyboardSensor) {
        this.animationRunner = animationRunner;
        this.keyboardSensor = keyboardSensor;
        this.scoreCounter = new Counter(0);
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean flag = false;
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.animationRunner, this.keyboardSensor, this.scoreCounter);

            level.initialize();

            while (!level.shouldStop()) {
                level.run();
            }

            if (level.getBallsCounter().getValue() == 0) {
                Animation gameOver = new GameOver(this.scoreCounter);
                Animation keyPress = new KeyPressStoppableAnimation(this.keyboardSensor, "space", gameOver);
                this.animationRunner.run(keyPress);
                flag = true;
                break;
            }
        }
        if (flag) {
            this.animationRunner.getGui().close();
        }
        Animation gameOver = new YouWin(this.scoreCounter);
        Animation keyPress = new KeyPressStoppableAnimation(this.keyboardSensor, "space", gameOver);
        this.animationRunner.run(keyPress);
        this.animationRunner.getGui().close();
    }
}
