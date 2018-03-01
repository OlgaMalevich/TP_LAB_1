import java.awt.*;

public class RegularPolygon extends Polygon {
    public Point point;

    public RegularPolygon(int count, Point centerP, Point secondP, Color borColor, Color bkColor) {
        super(borColor, bkColor);
        this.count = count;
        center = centerP;
        point = secondP;
        int radius = (int) Math.sqrt((center.x - point.x) * (center.x - point.x) + (center.y - point.y) * (center.y - point.y));
        double a, b, z = 0;
        int i = 0;
        xPoints = new int[count];
        yPoints = new int[count];
        double angle = 360.0 / count;
        while (i < count) {
            a = Math.cos(z / 180 * Math.PI);
            b = Math.sin(z / 180 * Math.PI);
            xPoints[i] = center.x + (int) (a * radius);
            yPoints[i] = center.y - (int) (b * radius);
            z = z + angle;
            i++;
        }
    }
}