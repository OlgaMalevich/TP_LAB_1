import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.Stack;

class MyWindow extends JFrame {
    private MyFrame drawPanel;
    private JRadioButton radioOneD, radioNonPoligon, radioRegPoligon, radioNonRegPoligon;
    private JComboBox comboOneD, comboNonRegPoligon, comboNonPoligon;
    private JFormattedTextField textRegPoligon;
    private ButtonGroup menuGroup;
    private Stack<Point> mousePoints;
    private JTextArea infoWindow;
    private String toDraw;
    private Color borderColor, backgroundColor;
    private LinkedList<Figure> figures;
    private LinkedList<Zigzag> zigzags;

    MyWindow() throws ParseException {
        super("Window");

        String[] itemsOneD = {"Линия", "Луч", "Отрезок", "Зигзаг"};
        String[] itemsNonRegPoligon = {"Прямоугольник", "Ромб", "Треугольник(произв.)", "Трегольник(равнобедр.)"};
        String[] itemsNonPoligon = {"Эллипс", "Окружность"};
        setBounds(0, 0, 1110, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        mousePoints = new Stack<>();
        toDraw = "Nothing";

        figures = new LinkedList<>();
        zigzags = new LinkedList<>();

        ItemListener radioItemListener = event -> {
            switch (((JRadioButton) event.getSource()).getName()) {
                case "oneD":
                    if (radioOneD.isSelected()) {
                        comboOneD.setEnabled(true);
                    } else {
                        comboOneD.setEnabled(false);
                    }
                    break;
                case "nonRegPoligon":
                    if (radioNonRegPoligon.isSelected()) {
                        comboNonRegPoligon.setEnabled(true);
                    } else {
                        comboNonRegPoligon.setEnabled(false);
                    }
                    break;
                case "regPoligon":
                    if (radioRegPoligon.isSelected()) {
                        textRegPoligon.setEnabled(true);
                    } else {
                        textRegPoligon.setEnabled(false);
                    }
                    break;
                case "nonPoligon":
                    if (radioNonPoligon.isSelected()) {
                        comboNonPoligon.setEnabled(true);
                    } else {
                        comboNonPoligon.setEnabled(false);
                    }
                    break;
            }
        };

        menuGroup = new ButtonGroup();
        radioOneD = new JRadioButton("Одномерные: ", false);
        radioOneD.setName("oneD");
        radioOneD.setActionCommand("oneD");
        radioOneD.addItemListener(radioItemListener);
        Color myGreen = new Color(204, 255, 204);
        radioOneD.setBackground(myGreen);
        radioRegPoligon = new JRadioButton("2D, полигон(регулярный):", false);
        radioRegPoligon.setBackground(myGreen);
        radioRegPoligon.setName("regPoligon");
        radioRegPoligon.setActionCommand("regPoligon");
        radioRegPoligon.addItemListener(radioItemListener);
        radioNonRegPoligon = new JRadioButton("2D, полигон(нерегулярный):", false);
        radioNonRegPoligon.setBackground(myGreen);
        radioNonRegPoligon.setName("nonRegPoligon");
        radioNonRegPoligon.setActionCommand("nonRegPoligon");
        radioNonRegPoligon.addItemListener(radioItemListener);
        radioNonPoligon = new JRadioButton("2D, не полигон: ", false);
        radioNonPoligon.setBackground(myGreen);
        radioNonPoligon.setName("nonPoligon");
        radioNonPoligon.setActionCommand("nonPoligon");
        radioNonPoligon.addItemListener(radioItemListener);
        menuGroup.add(radioOneD);
        menuGroup.add(radioRegPoligon);
        menuGroup.add(radioNonRegPoligon);
        menuGroup.add(radioNonPoligon);

        comboOneD = new JComboBox(itemsOneD);
        comboOneD.addActionListener(comboListener);
        comboOneD.setEnabled(false);
        comboOneD.setPopupVisible(false);
        comboNonRegPoligon = new JComboBox(itemsNonRegPoligon);
        comboNonRegPoligon.addActionListener(comboListener);
        comboNonRegPoligon.setEnabled(false);
        comboNonPoligon = new JComboBox(itemsNonPoligon);
        comboNonPoligon.addActionListener(comboListener);
        comboNonPoligon.setEnabled(false);
        textRegPoligon = new JFormattedTextField(NumberFormat.getIntegerInstance());
        textRegPoligon.setColumns(5);
        textRegPoligon.addActionListener(textListener);
        textRegPoligon.setEnabled(false);

        JPanel menuPanel = new JPanel();
        menuPanel.setBounds(800, 0, 300, 700);
        menuPanel.setBackground(myGreen);
        menuPanel.setLayout(new FlowLayout());
        JLabel labelCount = new JLabel("Количество сторон: ");
        JTextArea infoLable = new JTextArea("Информация:", 1, 25);
        infoLable.setBackground(myGreen);
        infoWindow = new JTextArea("Выберите фигуру.", 3, 25);
        infoWindow.setLineWrap(true);
        infoWindow.setWrapStyleWord(true);
        infoWindow.setEditable(false);

        menuPanel.add(radioOneD);
        menuPanel.add(comboOneD);
        menuPanel.add(radioNonPoligon);
        menuPanel.add(comboNonPoligon);
        menuPanel.add(radioNonRegPoligon);
        menuPanel.add(comboNonRegPoligon);
        menuPanel.add(radioRegPoligon);
        menuPanel.add(labelCount);
        menuPanel.add(textRegPoligon);
        menuPanel.add(infoLable);
        menuPanel.add(new JScrollPane(infoWindow));

        drawPanel = new MyFrame();
        drawPanel.setBounds(0, 0, 800, 700);

        add(drawPanel);
        add(menuPanel);
    }

    private ActionListener comboListener = e -> {
        String s = (String) ((JComboBox) e.getSource()).getSelectedItem();
        toDraw = s;
        System.out.println(s);
        borderColor = JColorChooser.showDialog(this, "Цвет границы", Color.black);
        if (!(menuGroup.getSelection().getActionCommand().equals("oneD"))) {
            backgroundColor = JColorChooser.showDialog(this, "Цвет заливки", Color.black);
        }
        if (s != null) {
            switch (s) {
                case "Линия":
                    infoWindow.setText("Кликните две точки для задания направления прямой. ");
                    break;
                case "Луч":
                    infoWindow.setText("Кликните точку начала и направление луча.");
                    break;
                case "Отрезок":
                    infoWindow.setText("Кликните точку начала и точку конца отрезка.");
                    break;
                case "Зигзаг":
                    infoWindow.setText("Кликните точки излома зигзага. Двойной щелчок - завершение рисования.");
                    break;
                case "Прямоугольник":
                    break;
                case "Ромб":
                    break;
                case "Треугольник(произв.)":
                    break;
                case "Треугольник(равнобедр.)":
                    break;
                case "Эллипс":
                    break;
                case "Окружность":
                    break;
            }
        }
    };
    private ActionListener textListener = e -> {
        borderColor = JColorChooser.showDialog(this, "Цвет границы", Color.black);
        backgroundColor = JColorChooser.showDialog(this, "Цвет заливки", Color.black);
        System.out.println((((JFormattedTextField) e.getSource()).getText()));
    };

    class MyFrame extends JPanel {
        MyFrame() {
            super();
            setBackground(Color.white);
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
                                figures.add(new Line(startPoint, endPoint, borderColor));
                                figures.getLast().draw(drawPanel.getGraphics());
                                infoWindow.setText("Нарисовано.");
                            }
                            break;
                        case "Луч":
                            if (mousePoints.size() == 2) {
                                System.out.println("Зашел луч\n");
                                Point endPoint = mousePoints.pop();
                                Point startPoint = mousePoints.pop();
                                figures.add(new Ray(startPoint, endPoint, borderColor));
                                figures.getLast().draw(drawPanel.getGraphics());
                                infoWindow.setText("Нарисовано.");
                            }
                            break;
                        case "Отрезок":
                            if (mousePoints.size() == 2) {
                                System.out.println("Зашел отрезок\n");
                                Point endPoint = mousePoints.pop();
                                Point startPoint = mousePoints.pop();
                                figures.add(new Segment(startPoint, endPoint, borderColor));
                                figures.getLast().draw(drawPanel.getGraphics());
                                infoWindow.setText("Нарисовано.");
                            }
                            break;
                        case "Зигзаг":
                            if (mousePoints.peek().equals(zigzagPoint)) {
                                System.out.println("Зашел зигзаг\n");
                                mousePoints.pop();
                                if (mousePoints.size() >= 2) {
                                    zigzags.add(new Zigzag(mousePoints, borderColor));
                                    zigzags.getLast().draw(drawPanel.getGraphics());
                                    infoWindow.setText("Нарисовано.");
                                } else {
                                    infoWindow.setText("Недостаточно точек.");
                                }
                            }
                            break;
                        case "Прямоугольник":
                            break;
                        case "Ромб":
                            break;
                        case "Треугольник(произв.)":
                            break;
                        case "Треугольник(равнобедр.)":
                            break;
                        case "Эллипс":
                            break;
                        case "Окружность":
                            break;
                    }
                }
            });
        }

        public void paint(Graphics g) {
            for (Figure figure : figures) {
                figure.draw(g);
            }
            for(Zigzag zigzag:zigzags){
                zigzag.draw(g);
            }
        }
    }
}