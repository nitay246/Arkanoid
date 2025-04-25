import biuoop.DrawSurface;

import java.awt.Color;
import java.awt.Polygon;

/**
 * The type Green 3 background.
 *
 * @author Nitay Damti 209458413
 * @version 19.0.2
 * @since 2023-14-06
 */
public class Green3Background implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        // Set the background color
        d.setColor(new Color(44, 62, 80));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        // Draw a moon
        drawMoon(d, new Color(255, 255, 255));

        // Draw mountains
        drawMountain(d, new Color(50, 50, 50), new Color(90, 90, 90),
                d.getHeight() - 200, d.getWidth(), 300);
        drawMountain(d, new Color(20, 20, 20), new Color(60, 60, 60),
                d.getHeight() - 100, d.getWidth(), 200);

        // Draw a castle
        drawCastle(d, d.getWidth() / 2 - 100, d.getHeight() - 280, new Color(109, 109, 109));

        // Draw clouds
        drawCloud(d, 200, 150, 40);
        drawCloud(d, 300, 180, 50);
        drawCloud(d, 400, 140, 35);
    }

    @Override
    public void timePassed() {
        // Leave empty if no time-dependent behavior is needed
    }

    private void drawMoon(DrawSurface d, Color outerColor) {
        d.setColor(outerColor);
        d.fillCircle(100, 120, 60);
        d.setColor(new Color(44, 62, 80));
        d.fillCircle(100 - 60 / 2, 120, 60);
    }

    private void drawMountain(DrawSurface d, Color baseColor, Color peakColor, int y, int width, int height) {
        int[] xPoints = {0, width / 2, width};
        int[] yPoints = {y + height, y, y + height};
        d.setColor(baseColor);
        Polygon polygon1 = new Polygon(xPoints, yPoints, 3);
        d.fillPolygon(polygon1);
        d.setColor(peakColor);
        int[] peakXPoints = {width / 2 - width / 10, width / 2, width / 2 + width / 10};
        int[] peakYPoints = {y + height - height / 6, y - height / 3, y + height - height / 6};
        Polygon polygon2 = new Polygon(peakXPoints, peakYPoints, 3);
        d.fillPolygon(polygon2);
    }

    private void drawCastle(DrawSurface d, int x, int y, Color color) {
        d.setColor(color);
        d.fillRectangle(x, y, 200, 280);
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, 200, 280);
        int towerWidth = 200 / 4;
        int towerHeight = 280 / 2;
        int towerX = x + towerWidth / 2;
        int towerY = y - towerHeight + 280;
        d.fillRectangle(towerX, towerY, towerWidth, towerHeight);
        d.fillRectangle(towerX + towerWidth * 2, towerY, towerWidth, towerHeight);
        d.setColor(color.darker());
        int doorWidth = 200 / 4;
        int doorHeight = 280 / 3;
        int doorX = x + 200 / 2 - doorWidth / 2;
        int doorY = y + 280 - doorHeight;
        d.fillRectangle(doorX, doorY, doorWidth, doorHeight);
    }

    private void drawCloud(DrawSurface d, int x, int y, int radius) {
        d.setColor(Color.lightGray);
        d.fillCircle(x, y, radius);
        d.fillCircle(x + radius, y, radius);
        d.fillCircle(x - radius, y, radius);
        d.fillCircle(x, y + radius, radius);
        d.fillCircle(x, y - radius, radius);
        d.fillCircle(x + radius / 2, y + radius / 2, radius);
        d.fillCircle(x + radius / 2, y - radius / 2, radius);
        d.fillCircle(x - radius / 2, y + radius / 2, radius);
        d.fillCircle(x - radius / 2, y - radius / 2, radius);
    }
}
