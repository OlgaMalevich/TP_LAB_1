import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Stack;

class MyWindow extends JFrame {
    private MyFrame drawPanel;
    private JRadioButton radioOneD, radioNonPoligon, radioRegPoligon, radioNonRegPoligon;
    private JComboBox comboOneD, comboNonRegPoligon, comboNonPoligon;
    private JFormattedTextField textRegPoligon;
    private JTextArea infoWindow;

    MyWindow() throws ParseException {
        super("Window");

        ButtonGroup menuGroup = new ButtonGroup();
        JPanel menuPanel = new JPanel();
        Stack<Point> mousePoints = new Stack<>();

        String[] itemsOneD = {"Линия", "Луч", "Отрезок", "Зигзаг"};
        String[] itemsNonRegPoligon = {"Прямоугольник", "Ромб", "Треугольник(произв.)", "Треугольник(равнобедр.)"};
        String[] itemsNonPoligon = {"Эллипс", "Окружность"};
        setBounds(0, 0, 1110, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

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
                        infoWindow.setText("Введите количество сторон.");
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

        ActionListener comboListener = e -> {
            String s = (String) ((JComboBox) e.getSource()).getSelectedItem();
            drawPanel.setToDraw(s);
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
                        infoWindow.setText("Кликните точку центра и точку одного из углов.");
                        break;
                    case "Ромб":
                        infoWindow.setText("Кликните точку центра и точки двух смежных вершин(по x и по y).");
                        break;
                    case "Треугольник(произв.)":
                        infoWindow.setText("Кликните три точки вершин.");
                        break;
                    case "Треугольник(равнобедр.)":
                        infoWindow.setText("Кликните вершину и одну из точек основания.");
                        break;
                    case "Эллипс":
                        infoWindow.setText("Кликните точку центра и две точки, задающие радиусы(по x и по y).");
                        break;
                    case "Окружность":
                        infoWindow.setText("Кликните точку центра и точку, задающую радиус.");
                        break;
                }
            }
        };
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
        textRegPoligon.setEnabled(false);
        ActionListener textListener = e -> {
            infoWindow.setText("Кликните точку центра и одну из вершин.");
            drawPanel.setRegCount(Integer.parseInt((((JFormattedTextField) e.getSource()).getText())));
            drawPanel.setToDraw("Правильный");
        };
        textRegPoligon.addActionListener(textListener);

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
        JButton moveButton = new JButton("Передвинуть");
        moveButton.addActionListener(e -> {
            if(radioOneD.isSelected())
                infoWindow.setText("Введите новую точку начала.");
            else
                infoWindow.setText("Введите новую точку центра фигуры.");
            drawPanel.setToDraw("move");
        });
        menuPanel.add(moveButton);
        JButton locationButton = new JButton("Показать расположение");
        locationButton.addActionListener(e -> {
            drawPanel.setToDraw("location");
            drawPanel.locationLast();
        });
        menuPanel.add(locationButton);
        drawPanel = new MyFrame(mousePoints, infoWindow);
        drawPanel.setBounds(0, 0, 800, 700);

        add(drawPanel);
        add(menuPanel);
    }
}