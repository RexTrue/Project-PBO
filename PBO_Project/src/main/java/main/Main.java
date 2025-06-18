package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Geometri.benda2D.*;
import Geometri.benda3D.*;

public class Main extends JFrame implements Runnable {
    private JComboBox<String> shapeComboBox;
    private JComboBox<String> subShapeComboBox;
    private JPanel inputPanel;
    private JLabel labelInput1, labelInput2, labelHasil;
    private JTextField field1, field2;
    private JButton btnHitung;

    public Main() {
        setTitle("Geometry Calculator");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Geometry Calculator", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.PINK);
        title.setBackground(new Color(30, 30, 50));
        title.setOpaque(true);
        add(title, BorderLayout.NORTH);

        JPanel topPanel = new JPanel();
        JButton btn2D = new JButton("2D ▼");
        JButton btn3D = new JButton("3D ▼");
        topPanel.add(btn2D);
        topPanel.add(btn3D);
        add(topPanel, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel(new GridLayout(6, 2));
        shapeComboBox = new JComboBox<>();
        subShapeComboBox = new JComboBox<>();

        inputPanel = new JPanel(new GridLayout(2, 2));
        labelInput1 = new JLabel("Input 1:");
        labelInput2 = new JLabel("Input 2:");
        field1 = new JTextField();
        field2 = new JTextField();
        labelHasil = new JLabel("Hasil: -");

        btnHitung = new JButton("Hitung");

        inputPanel.add(labelInput1);
        inputPanel.add(field1);
        inputPanel.add(labelInput2);
        inputPanel.add(field2);

        centerPanel.add(new JLabel("Bentuk: "));
        centerPanel.add(shapeComboBox);
        centerPanel.add(new JLabel("Sub-Bentuk: "));
        centerPanel.add(subShapeComboBox);
        centerPanel.add(new JLabel("Input: "));
        centerPanel.add(inputPanel);
        centerPanel.add(btnHitung);
        centerPanel.add(labelHasil);

        add(centerPanel, BorderLayout.SOUTH);

        btn2D.addActionListener(e -> {
            shapeComboBox.removeAllItems();
            subShapeComboBox.removeAllItems();
            shapeComboBox.addItem("Belah Ketupat");
            shapeComboBox.addItem("Jajaran Genjang");
            shapeComboBox.addItem("Lingkaran");
            shapeComboBox.addItem("Layang Layang");
            shapeComboBox.addItem("Persegi");
            shapeComboBox.addItem("Persegi Panjang");
            shapeComboBox.addItem("Segitiga");
            shapeComboBox.addItem("Trapesium");
        });

        btn3D.addActionListener(e -> {
            shapeComboBox.removeAllItems();
            subShapeComboBox.removeAllItems();
            shapeComboBox.addItem("Prisma");
            shapeComboBox.addItem("Limas");
            shapeComboBox.addItem("Bola");
            shapeComboBox.addItem("Tabung");
        });

        shapeComboBox.addActionListener(e -> {
            String selected = (String) shapeComboBox.getSelectedItem();
            subShapeComboBox.removeAllItems();

            if ("Lingkaran".equals(selected)) {
                subShapeComboBox.addItem("Lingkaran");
                subShapeComboBox.addItem("Juring Lingkaran");
                subShapeComboBox.addItem("Tembereng Lingkaran");
            } else if ("Bola".equals(selected)) {
                subShapeComboBox.addItem("Bola");
                subShapeComboBox.addItem("Keratan Bola");
            } else {
                subShapeComboBox.addItem("-");
            }
            updateInputFields(selected);
        });

        btnHitung.addActionListener(e -> {
            Thread thread = new Thread(this);
            thread.start();
        });
    }

    private void updateInputFields(String shape) {
        switch (shape) {
            case "Lingkaran":
            case "Bola":
            case "Tabung":
                labelInput1.setText("Jari-jari:");
                labelInput2.setText("Tinggi:");
                break;
            case "Persegi":
                labelInput1.setText("Sisi:");
                labelInput2.setText("-");
                break;
            case "Persegi Panjang":
            case "Jajaran Genjang":
            case "Trapesium":
                labelInput1.setText("Panjang:");
                labelInput2.setText("Lebar:");
                break;
            default:
                labelInput1.setText("Input 1:");
                labelInput2.setText("Input 2:");
        }
    }

    @Override
    public void run() {
        try {
            String shape = (String) shapeComboBox.getSelectedItem();
            double input1 = Double.parseDouble(field1.getText());
            double hasil = 0;

            switch (shape) {
                case "Lingkaran":
                    Lingkaran lingkaran = new Lingkaran(input1);
                    hasil = lingkaran.hitungLuas();
                    break;
                case "Bola":
                    Bola bola = new Bola(input1);
                    hasil = bola.hitungVolume();
                    break;
                default:
                    hasil = 0;
            }
            labelHasil.setText("Hasil: " + hasil);
        } catch (Exception ex) {
            labelHasil.setText("Error input tidak valid.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
