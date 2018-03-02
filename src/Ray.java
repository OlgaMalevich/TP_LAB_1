import java.awt.*;

public class Ray extends Segment {
    public Ray(Point point1, Point point2, Color color){
        super(point1, point2, color);
        endPoint = extend(point1, point2);
       }
protected Point extend(Point fromP, Point toP){
        Point extendedP;
        if(fromP.x<toP.x){
            if(fromP.y<toP.y){
                if(get_y(fromP, toP, 800)<=700){
                    extendedP = new Point(800,get_y(fromP, toP, 800));
                } else {
                    extendedP = new Point(get_x(fromP, toP, 700), 700);
                }
            }else{
                if(get_y(fromP, toP, 800)>=0){
                    extendedP = new Point(800,get_y(fromP, toP, 800));
                } else {
                    extendedP = new Point(get_x(fromP, toP, 0), 0);
                }
            }
        }else{
            if(fromP.y<toP.y){
                if(get_y(fromP, toP, 0)<=700){
                    extendedP = new Point(0,get_y(fromP, toP, 0));
                } else {
                    extendedP = new Point(get_x(fromP, toP, 700), 700);
                }
            }else{
                if(get_y(fromP, toP, 0)>=0){
                    extendedP = new Point(0, get_y(fromP, toP, 0));
                } else {
                    extendedP = new Point(get_x(fromP, toP, 0), 0);
                }
            }
        }
        return extendedP;
}
protected int get_x(Point fromP, Point toP, int y){
    int x = (toP.x*(-1)*fromP.y - fromP.x*(-1)*toP.y+(fromP.x-toP.x)*(-1)*y)/((-1)*fromP.y-(-1)*toP.y);
    return x;
}
protected int get_y (Point fromP,Point toP, int x){
        int y = (-1)*(toP.x*(-1)*fromP.y - fromP.x*(-1)*toP.y+(-1)*(toP.y-fromP.y)*x)/(toP.x-fromP.x);
        return y;
    }
    public void move(Point point){
    super.move(point);
    endPoint = extend(startPoint, endPoint);
    }
}