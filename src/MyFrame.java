import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Stack;

class MyFrame extends JPanel {
    private Stack<Point> mousePoints;
    private LinkedList<Figure> figures;
    private LinkedList<Zigzag> zigzags;
    private Color borderColor, backgroundColor;
    private String toDraw;
    private int count;

    MyFrame(Stack<Point> mPoints, JTextArea infoWindow) {
        super();
        setBackground(Color.white);
        mousePoints = mPoints;
        figures = new LinkedList<>();
        zigzags = new LinkedList<>();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point zigzagPoint = new Point();
                if (toDraw.equals("Зигзаг") && mousePoints.size() != 0)
                    zigzagPoint = mousePoints.peek();
                mousePoints.push(new Point(e.getX(), e.getY()));
                System.out.println("Тык (" + e.getX() + ", " + e.getY() + ")\n");
                switch (toDraw) {
                    case "Линия":

                        if (mousePoints.size() == 2) {
                            System.out.println("Зашел прямая\n");
                            Point endPoint = mousePoints.pop();
                            Point startPoint = mousePoints.pop();
                            borderColor = JColorChooser.showDialog(null, "Цвет границы", Color.black);
                            figures.add(new Line(startPoint, endPoint, borderColor));
                            figures.getLast().draw(getGraphics());
                            infoWindow.setText("Нарисовано.");
                        }
                        break;
                    case "Луч":
                        if (mousePoints.size() == 2) {
                            System.out.println("Зашел луч\n");
                            Point endPoint = mousePoints.pop();
                            Point startPoint = mousePoints.pop();
                            borderColor = JColorChooser.showDialog(null, "Цвет границы", Color.black);
                            figures.add(new Ray(startPoint, endPoint, borderColor));
                            figures.getLast().draw(getGraphics());
                            infoWindow.setText("Нарисовано.");
                        }
                        break;
                    case "Отрезок":
                        if (mousePoints.size() == 2) {
                            System.out.println("Зашел отрезок\n");
                            Point endPoint = mousePoints.pop();
                            Point startPoint = mousePoints.pop();
                            borderColor = JColorChooser.showDialog(null, "Цвет границы", Color.black);
                            figures.add(new Segment(startPoint, endPoint, borderColor));
                            figures.getLast().draw(getGraphics());
                            infoWindow.setText("Нарисовано.");
                        }
                        break;
                    case "Зигзаг":
                        if (mousePoints.peek().equals(zigzagPoint)) {
                            System.out.println("Зашел зигзаг\n");
                            mousePoints.pop();
                            if (mousePoints.size() >= 2) {
                                borderColor = JColorChooser.showDialog(null, "Цвет границы", Color.black);
                                zigzags.add(new Zigzag(mousePoints, borderColor));
                                zigzags.getLast().draw(getGraphics());
                                infoWindow.setText("Нарисовано.");
                            } else {
                                infoWindow.setText("Недостаточно точек.");
                            }
                        }
                        break;
                    case "Прямоугольник":
                        if (mousePoints.size() == 2) {
                            System.out.println("Зашел прямоугольник\n");
                            Point secondPoint = mousePoints.pop();
                            Point centerPoint = mousePoints.pop();
                            borderColor = JColorChooser.showDialog(null, "Цвет границы", Color.black);
                            backgroundColor = JColorChooser.showDialog(null, "Цвет заливки", Color.black);
                            figures.add(new Rectangle(centerPoint, secondPoint, borderColor, backgroundColor));
                            figures.getLast().draw(getGraphics());
                            infoWindow.setText("Нарисовано.");
                        }
                        break;
                    case "Ромб":
                        if (mousePoints.size() == 3) {
                            System.out.println("Зашел ромб\n");
                            Point secondPoint = mousePoints.pop();
                            Point firstPoint = mousePoints.pop();
                            Point centerPoint = mousePoints.pop();
                            borderColor = JColorChooser.showDialog(null, "Цвет границы", Color.black);
                            backgroundColor = JColorChooser.showDialog(null, "Цвет заливки", Color.black);
                            figures.add(new Romb(centerPoint, firstPoint, secondPoint, borderColor, backgroundColor));
                            figures.getLast().draw(getGraphics());
                            infoWindow.setText("Нарисовано.");
                        }
                        break;
                    case "Треугольник(произв.)":
                        if (mousePoints.size() == 3) {
                            System.out.println("Зашел треугольник\n");
                            Point point3 = mousePoints.pop();
                            Point point2 = mousePoints.pop();
                            Point point1 = mousePoints.pop();
                            borderColor = JColorChooser.showDialog(null, "Цвет границы", Color.black);
                            backgroundColor = JColorChooser.showDialog(null, "Цвет заливки", Color.black);
                            figures.add(new Triangle(point1, point2, point3, borderColor, backgroundColor));
                            figures.getLast().draw(getGraphics());
                            infoWindow.setText("Нарисовано.");
                        }
                        break;
                    case "Треугольник(равнобедр.)":
                        if (mousePoints.size() == 2) {
                            System.out.println("Зашел треугольник\n");
                            Point point2 = mousePoints.pop();
                            Point point1 = mousePoints.pop();
                            borderColor = JColorChooser.showDialog(null, "Цвет границы", Color.black);
                            backgroundColor = JColorChooser.showDialog(null, "Цвет заливки", Color.black);
                            figures.add(new Isosceles(point1, point2, borderColor, backgroundColor));
                            figures.getLast().draw(getGraphics());
                            infoWindow.setText("Нарисовано.");
                        }
                        break;
                    case "Эллипс":
                        if (mousePoints.size() == 3) {
                            System.out.println("Зашел эллипс\n");
                            Point endPoint = mousePoints.pop();
                            Point startPoint = mousePoints.pop();
                            Point centerPoint = mousePoints.pop();
                            borderColor = JColorChooser.showDialog(null, "Цвет границы", Color.black);
                            backgroundColor = JColorChooser.showDialog(null, "Цвет заливки", Color.black);
                            figures.add(new Ellipse(centerPoint, startPoint, endPoint, borderColor, backgroundColor));
                            figures.getLast().draw(getGraphics());
                            infoWindow.setText("Нарисовано.");
                        }
                        break;
                    case "Окружность":
                        if (mousePoints.size() == 2) {
                            System.out.println("Зашел окружность\n");
                            Point newPoint = mousePoints.pop();
                            Point centerPoint = mousePoints.pop();
                            borderColor = JColorChooser.showDialog(null, "Цвет границы", Color.black);
                            backgroundColor = JColorChooser.showDialog(null, "Цвет заливки", Color.black);
                            figures.add(new Circle(centerPoint, newPoint, borderColor, backgroundColor));
                            figures.getLast().draw(getGraphics());
                            infoWindow.setText("Нарисовано.");
                        }
                        break;
                    case "Правильный":
                        if (mousePoints.size() == 2) {
                            System.out.println("Зашел правильный\n");
                            Point newPoint = mousePoints.pop();
                            Point centerPoint = mousePoints.pop();
                            borderColor = JColorChooser.showDialog(null, "Цвет границы", Color.black);
                            backgroundColor = JColorChooser.showDialog(null, "Цвет заливки", Color.black);
                            figures.add(new RegularPolygon(count,centerPoint, newPoint, borderColor, backgroundColor));
                            figures.getLast().draw(getGraphics());
                            infoWindow.setText("Нарисовано.");
                        }
                        break;
                }
            }
        });
    }

    void setToDraw(String str) {
        toDraw = str;
    }
    void setRegCount(int count) {this.count = count;}

    public void paint(Graphics g) {
        for (Figure figure : figures) {
            figure.draw(g);
        }
        for (Zigzag zigzag : zigzags) {
            zigzag.draw(g);
        }
    }
}