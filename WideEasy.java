import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Wide easy.
 *
 * @author Nitay Damti 209458413
 * @version 19.0.2
 * @since 2023-14-06
 */
public class WideEasy implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        int numberOfBalls = this.numberOfBalls();
        int middleIndex = numberOfBalls / 2;

        for (int i = 0; i < numberOfBalls; i++) {
            double angle = -(i - middleIndex) * 10;
            // Decreasing angle
            double radians = Math.toRadians(angle);

            // Calculate the velocity components using the angle
            double xComponent = Math.sin(radians);
            double yComponent = -Math.cos(radians);

            velocities.add(new Velocity(4 * xComponent, 4 * yComponent));
        }

        return velocities;
    }



    @Override
    public int paddleSpeed() {
        return 4;
    }

    @Override
    public int paddleWidth() {
        return 500;
    }
    @Override
    public Color paddleColor() {
        return Color.yellow;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new WideEasyBackground();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Color[] colors = {Color.red, Color.red, Color.orange, Color.orange, Color.yellow, Color.yellow, Color.green,
                Color.green, Color.green, Color.blue, Color.blue, Color.pink, Color.pink, Color.cyan, Color.cyan};
        for (int i = 0; i < 15; i++) {
            Block block = new Block(new Rectangle(new Point(20 + i * 50, 300), 60, 30), colors[i]);
            blocks.add(block);
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
