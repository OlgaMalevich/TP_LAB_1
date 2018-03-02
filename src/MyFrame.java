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
    private String toDraw, oldToDraw;
    private int count;
    private JTextArea infoWindow;

    MyFrame(Stack<Point> mPoints, JTextArea infoWindow) {
        super();
        setBackground(Color.white);
        mousePoints = mPoints;
        figures = new LinkedList<>();
        zigzags = new LinkedList<>();

        this.infoWindow = infoWindow;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (toDraw.equals("location"))
                    return;
                Point zigzagPoint = new Point();
                if (toDraw.equals("Зигзаг") && mousePoints.size() != 0)
                    zigzagPoint = mousePoints.peek();
                mousePoints.push(new Point(e.getX(), e.getY()));
                switch (toDraw) {
                    case "Линия":
                        if (mousePoints.size() == 2) {
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
                            Point newPoint = mousePoints.pop();
                            Point centerPoint = mousePoints.pop();
                            borderColor = JColorChooser.showDialog(null, "Цвет границы", Color.black);
                            backgroundColor = JColorChooser.showDialog(null, "Цвет заливки", Color.black);
                            figures.add(new RegularPolygon(count, centerPoint, newPoint, borderColor, backgroundColor));
                            figures.getLast().draw(getGraphics());
                            infoWindow.setText("Нарисовано.");
                        }
                        break;
                    case "move":
                        if (mousePoints.size() == 1) {
                            if (oldToDraw.equals("Зигзаг")) {
                                zigzags.getLast().move(mousePoints.pop());
                                zigzags.getLast().draw(getGraphics());
                            } else {
                                figures.getLast().move(mousePoints.pop());
                                figures.getLast().draw(getGraphics());
                            }
                            infoWindow.setText("Перенесено.");
                            paint(getGraphics());
                        }
                        break;
                }
            }
        });
    }

    void setToDraw(String str) {
        if (str.equals("location")) {
            toDraw = str;
            return;
        } else {
            if (str.equals("move")) {
                toDraw = str;
                return;
            } else {
                oldToDraw = str;
                toDraw = str;
            }
        }
    }

    void setRegCount(int count) {
        this.count = count;
    }

    void locationLast() {
        if (oldToDraw.equals("Зигзаг"))
            infoWindow.setText(zigzags.getLast().location());
        else
            infoWindow.setText(figures.getLast().location());
    }

    public void paint(Graphics g) {
        super.paint(g);
        for (Figure figure : figures) {
            figure.draw(g);
        }
        for (Zigzag zigzag : zigzags) {
            zigzag.draw(g);
        }
    }
}