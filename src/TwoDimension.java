import java.awt.*;

public abstract class TwoDimension extends Figure {
    public Color backgroundColor;
    public TwoDimension() {}
    public Color getBackgroundColor() {return backgroundColor;}

    public void setBackgroundColor(Color backgroundColor) { this.backgroundColor = backgroundColor;}

    public void move(Point newCenter) { }
}