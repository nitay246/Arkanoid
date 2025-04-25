import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Ass 6 game.
 *
 * @author Nitay Damti 209458413
 * @version 19.0.2
 * @since 2023-14-06
 */
public class Ass6Game {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        AnimationRunner animationRunner = new AnimationRunner();
        KeyboardSensor keyboardSensor = animationRunner.getGui().getKeyboardSensor();
        GameFlow game = new GameFlow(animationRunner, keyboardSensor);
        List<LevelInformation> gameLevels;
        if (args.length == 0) {
            gameLevels = List.of(new DirectHit(), new WideEasy(), new Green3());
        } else {
            gameLevels = new ArrayList<>();
            for (String arg: args) {
                try {
                int levelNumber = Integer.parseInt(arg);
                if (levelNumber >= 1 && levelNumber <= 3) {
                    // Add the corresponding level based on the levelNumber
                    switch (levelNumber) {
                        case 1 -> gameLevels.add(new DirectHit());
                        case 2 -> gameLevels.add(new WideEasy());
                        case 3 -> gameLevels.add(new Green3());
                        default -> throw new IllegalStateException("Unexpected value: " + levelNumber);
                    }
                }
            } catch (NumberFormatException ignored) {
                }
            }
        }
        if (gameLevels.isEmpty()) {
            gameLevels = List.of(new DirectHit(), new WideEasy(), new Green3());
        }
        game.runLevels(gameLevels);
    }
}
