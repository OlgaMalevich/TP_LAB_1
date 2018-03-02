import java.awt.*;

public class Line extends Ray {
    public Line(Point point1, Point point2, Color color){
        super(point1, point2, color);
        endPoint = extend(point1, point2);
        startPoint = extend(point2, point1);
    }
    public void move(Point point){
        super.move(point);
        endPoint = extend(startPoint, endPoint);
        startPoint = extend(endPoint, startPoint);
    }
}