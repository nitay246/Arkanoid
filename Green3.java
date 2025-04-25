import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Green 3.
 *
 * @author Nitay Damti 209458413
 * @version 19.0.2
 * @since 2023-14-06
 */
public class Green3 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(-4, -4));
        velocities.add(new Velocity(4, -4));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }
    @Override
    public Color paddleColor() {
        return Color.yellow;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Green3Background();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Color[] colors = {Color.red, Color.yellow, Color.green, Color.blue, Color.cyan};
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10 - i; j++) {
                int x = 280 + (j * 50) + (i * 50);
                int y = 125 + i * 20;
                Block block = new Block(new Rectangle(new Point(x, y), 50, 20), colors[i]);
                blocks.add(block);
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
