import biuoop.DrawSurface;
import java.awt.Color;
import java.awt.Polygon;

/**
 * The type Wide easy background.
 *
 * @author Nitay Damti 209458413
 * @version 19.0.2
 * @since 2023-14-06
 */
public class WideEasyBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        // Set the background color
        d.setColor(new Color(0, 77, 153));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        // Draw a sun
        d.setColor(Color.YELLOW);
        d.fillCircle(100, 100, 60);
        d.setColor(new Color(255, 204, 0));
        d.fillCircle(100, 100, 50);

        // Draw mountains
        d.setColor(new Color(102, 51, 0));

        // Draw the pyramid shape
        int pyramidHeight = 300;
        int pyramidWidth = 600;
        int pyramidX = d.getWidth() / 2 - pyramidWidth / 2;
        int pyramidY = d.getHeight() - pyramidHeight;

        Polygon pyramid = new Polygon(
                new int[]{pyramidX, pyramidX + pyramidWidth / 2, pyramidX + pyramidWidth},
                new int[]{pyramidY + pyramidHeight, pyramidY, pyramidY + pyramidHeight},
                3);
        d.fillPolygon(pyramid);

        // Draw clouds
        d.setColor(new Color(180, 180, 180));
        d.fillCircle(650, 150, 40);
        d.fillCircle(590, 150, 40);
        d.fillCircle(630, 150, 40);
        d.fillCircle(570, 120, 40);
        d.fillCircle(610, 120, 40);
        d.fillCircle(590, 90, 40);
    }

    @Override
    public void timePassed() {
    }
}
