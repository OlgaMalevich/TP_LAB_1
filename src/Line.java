import java.awt.*;

public class Line extends Ray {
    public Line() {
        super();
    }
    public Line(Point point1, Point point2, Color color){
        super(point1, point2, color);
        endPoint = extend(point1, point2);
        startPoint = extend(point2, point1);
    }
}