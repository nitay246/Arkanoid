import biuoop.DrawSurface;

import java.awt.Color;
import java.awt.Polygon;

/**
 * The type Direct hit background.
 *
 * @author Nitay Damti 209458413
 * @version 19.0.2
 * @since 2023-14-06
 */
public class DirectHitBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        // Set the background color
        d.setColor(Color.BLUE);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        // Draw a sun
        d.setColor(Color.YELLOW);
        d.fillCircle(100, 100, 60);
        d.setColor(Color.ORANGE);
        d.fillCircle(100, 100, 50);

        // Draw grass
        d.setColor(Color.GREEN);
        d.fillRectangle(0, d.getHeight() - 100, d.getWidth(), 100);

        // Draw a house
        int houseX = 500;
        int houseY = d.getHeight() - 250;
        int houseWidth = 200;
        int houseHeight = 200;

        // Draw walls
        d.setColor(Color.GRAY);
        d.fillRectangle(houseX, houseY, houseWidth, houseHeight);
        d.setColor(Color.BLACK);
        d.drawRectangle(houseX, houseY, houseWidth, houseHeight);

        // Draw roof
        d.setColor(Color.orange);
        int[] roofXPoints = {houseX - 20, houseX + houseWidth / 2, houseX + houseWidth + 20};
        int[] roofYPoints = {houseY, houseY - 80, houseY};
        Polygon roof = new Polygon(roofXPoints, roofYPoints, 3);
        d.fillPolygon(roof);

        // Draw window
        d.setColor(Color.WHITE);
        d.fillRectangle(houseX + 40, houseY + 40, 40, 40);

        // Draw door
        d.setColor(Color.WHITE);
        d.fillRectangle(houseX + houseWidth - 60, houseY + houseHeight - 80, 40, 80);

        // Draw clouds
        d.setColor(Color.WHITE);
        d.fillCircle(200, 150, 40);
        d.fillCircle(240, 150, 40);
        d.fillCircle(280, 150, 40);
        d.fillCircle(220, 120, 40);
        d.fillCircle(260, 120, 40);
        d.fillCircle(240, 90, 40);
    }

    @Override
    public void timePassed() {
        // Leave empty if no time-dependent behavior is needed
    }
}
