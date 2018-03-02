import java.awt.*;
public abstract class OneDimension extends Figure {
    protected Point startPoint;
    protected Point endPoint;
    public OneDimension(Point point1, Point point2, Color color) {
        startPoint = point1;
        endPoint = point2;
        borderColor = color;
    }
    public Point getStartPoint(){
        return startPoint;
    }
    public void setStartPoint(Point point1) {
        startPoint = point1;
    }
    public Point getEndPoint() {
        return endPoint;
    }
    public void setEndPoint(Point point2) {
        endPoint = point2;
    }
    public String location() {
        return "Начало: (" + startPoint.x+", "+startPoint.y+"), конец: ("+endPoint.x+", "+endPoint.y+")";
    }
    public void move(Point point1) {
        endPoint.x += point1.x-startPoint.x;
        endPoint.y += point1.y-startPoint.y;
        startPoint = new Point(point1);
    }

    public void draw(Graphics g) {
        g.setColor(borderColor);
        g.drawLine(startPoint.x,startPoint.y,endPoint.x,endPoint.y);
    }

}