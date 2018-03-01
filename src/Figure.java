import java.awt.*;

public abstract class Figure {
    public Point center;
    public Color borderColor;
    public Point getCenter() {return center;}
    public void setCenter(Point center) {this.center = center;}
    public Figure() {}
    public Color getBorderColor() {
        return borderColor;
    }
    public void setBorderColor(Color cl) {
        borderColor = cl;
    }
    abstract public void draw(Graphics g);
    protected void setStartPoint(Point p){;}
    protected void setEndPoint(Point p){;}

}