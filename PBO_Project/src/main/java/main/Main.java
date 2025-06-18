package main;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import Geometri.benda2D.*;
import Geometri.benda3D.*;
import Geometri.threading.ThreadExecutor;
import java.util.concurrent.Future;

public class Main extends JFrame {
    private JComboBox<String> comboSuper;
    private JComboBox<String> comboSub;
    private JComboBox<String> comboSub3D;
    private JPanel inputPanel;
    private JTextArea resultArea;
    private JButton calculateButton;
    private JButton clearButton;
    private JButton hitungThreadButton;

    private static final String[] SUPERCLASS = {"2D", "3D"};
    private static final Map<String, String[]> SUBCLASS = new HashMap<>();
    private static final Map<String, String[]> SUBCLASS_3D = new HashMap<>();

    static {
        SUBCLASS.put("2D", new String[]{
            "Segitiga", "Persegi", "Persegi Panjang", "Lingkaran", 
            "Jajar Genjang", "Belah Ketupat", "Layang-Layang", 
            "Trapesium", "Juring Lingkaran", "Tembereng Lingkaran"
        });

        SUBCLASS.put("3D", new String[]{
            "Bola", "Tabung", "Kerucut", "Kubus", "Balok", 
            "Limas", "Prisma", "Cincin Bola", "Juring Bola", 
            "Tembereng Bola", "Kerucut Terpancung"
        });

        SUBCLASS_3D.put("Limas", new String[]{
            "Limas Persegi", "Limas Persegi Panjang", "Limas Segitiga", 
            "Limas Trapesium", "Limas Belah Ketupat", 
            "Limas Jajar Genjang", "Limas Layang-Layang"
        });

        SUBCLASS_3D.put("Prisma", new String[]{
            "Prisma Persegi", "Prisma Persegi Panjang", "Prisma Segitiga", 
            "Prisma Trapesium", "Prisma Belah Ketupat", 
            "Prisma Jajar Genjang", "Prisma Layang-Layang"
        });
    }

    public Main() {
        setTitle("Kalkulator Geometri OOP - PBO Project");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = createTopPanel();
        add(topPanel, BorderLayout.NORTH);

        inputPanel = new JPanel();
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input Parameter"));
        add(inputPanel, BorderLayout.CENTER);

        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);

        setupEventListeners();

        updateSubCombo();
    }

    private JPanel createTopPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Pilih Bentuk Geometri"));

        comboSuper = new JComboBox<>(SUPERCLASS);
        comboSub = new JComboBox<>();
        comboSub3D = new JComboBox<>();
        comboSub3D.setVisible(false);

        panel.add(new JLabel("Tipe:"));
        panel.add(comboSuper);
        panel.add(new JLabel("Bentuk:"));
        panel.add(comboSub);
        panel.add(new JLabel("Subclass:"));
        panel.add(comboSub3D);

        return panel;
    }

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Hasil Perhitungan"));

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        calculateButton = new JButton("Hitung");
        clearButton = new JButton("Clear");
        hitungThreadButton = new JButton("Hitung (Thread)");
        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(hitungThreadButton);

        // Result Area
        resultArea = new JTextArea(8, 50);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(resultArea);

        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void setupEventListeners() {
        comboSuper.addActionListener(e -> updateSubCombo());
        comboSub.addActionListener(e -> updateSub3DCombo());
        comboSub3D.addActionListener(e -> updateInputPanel());
        calculateButton.addActionListener(e -> calculateGeometry());
        clearButton.addActionListener(e -> clearAll());
        hitungThreadButton.addActionListener(e -> runThreadedCalculation());
    }

    private void updateSubCombo() {
        String selectedSuper = (String) comboSuper.getSelectedItem();
        comboSub.removeAllItems();
        for (String s : SUBCLASS.get(selectedSuper)) {
            comboSub.addItem(s);
        }
        comboSub3D.setVisible(false);
        updateInputPanel();
    }

    private void updateSub3DCombo() {
        String selectedSub = (String) comboSub.getSelectedItem();
        if (SUBCLASS_3D.containsKey(selectedSub)) {
            comboSub3D.setVisible(true);
            comboSub3D.removeAllItems();
            for (String s : SUBCLASS_3D.get(selectedSub)) {
                comboSub3D.addItem(s);
            }
        } else {
            comboSub3D.setVisible(false);
        }
        updateInputPanel();
    }

    private void updateInputPanel() {
        inputPanel.removeAll();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        String selectedShape = getSelectedShape();
        if (selectedShape == null) {
            inputPanel.revalidate();
            inputPanel.repaint();
            return;
        }
        JTextField[] inputFields = createInputFields(selectedShape);

        gbc.gridx = 0;
        gbc.gridy = 0;
        for (int i = 0; i < inputFields.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            inputPanel.add(new JLabel(getInputLabel(selectedShape, i) + ":"), gbc);
            gbc.gridx = 1;
            inputPanel.add(inputFields[i], gbc);
        }

        inputPanel.revalidate();
        inputPanel.repaint();
    }

    private String getSelectedShape() {
        String selectedSub = (String) comboSub.getSelectedItem();
        if (comboSub3D.isVisible() && comboSub3D.getSelectedItem() != null) {
            return (String) comboSub3D.getSelectedItem();
        }
        return selectedSub;
    }

    private JTextField[] createInputFields(String shape) {
        if (shape == null) {
            return new JTextField[0];
        }
        switch (shape) {
            // 2D 
            case "Segitiga":
                return new JTextField[]{new JTextField(10), new JTextField(10)};
            case "Persegi":
                return new JTextField[]{new JTextField(10)};
            case "Persegi Panjang":
                return new JTextField[]{new JTextField(10), new JTextField(10)};
            case "Lingkaran":
                return new JTextField[]{new JTextField(10)};
            case "Jajar Genjang":
                return new JTextField[]{new JTextField(10), new JTextField(10), new JTextField(10)};
            case "Belah Ketupat":
                return new JTextField[]{new JTextField(10), new JTextField(10), new JTextField(10)};
            case "Layang-Layang":
                return new JTextField[]{new JTextField(10), new JTextField(10)};
            case "Trapesium":
                return new JTextField[]{new JTextField(10), new JTextField(10), new JTextField(10)};
            case "Juring Lingkaran":
                return new JTextField[]{new JTextField(10), new JTextField(10)};
            case "Tembereng Lingkaran":
                return new JTextField[]{new JTextField(10), new JTextField(10)};
            
            // 3D 
            case "Bola":
                return new JTextField[]{new JTextField(10)};
            case "Tabung":
                return new JTextField[]{new JTextField(10), new JTextField(10)};
            case "Kerucut":
                return new JTextField[]{new JTextField(10), new JTextField(10)};
            case "Kubus":
                return new JTextField[]{new JTextField(10)};
            case "Balok":
                return new JTextField[]{new JTextField(10), new JTextField(10), new JTextField(10)};
            case "Cincin Bola":
                return new JTextField[]{new JTextField(10), new JTextField(10)};
            case "Juring Bola":
                return new JTextField[]{new JTextField(10), new JTextField(10)};
            case "Tembereng Bola":
                return new JTextField[]{new JTextField(10), new JTextField(10)};
            case "Kerucut Terpancung":
                return new JTextField[]{new JTextField(10), new JTextField(10), new JTextField(10)};
            
            // Limas
            case "Limas Persegi":
                return new JTextField[]{new JTextField(10), new JTextField(10)};
            case "Limas Persegi Panjang":
                return new JTextField[]{new JTextField(10), new JTextField(10), new JTextField(10)};
            case "Limas Segitiga":
                return new JTextField[]{new JTextField(10), new JTextField(10), new JTextField(10)};
            case "Limas Trapesium":
                return new JTextField[]{new JTextField(10), new JTextField(10), new JTextField(10), new JTextField(10)};
            case "Limas Belah Ketupat":
                return new JTextField[]{new JTextField(10), new JTextField(10), new JTextField(10), new JTextField(10)};
            case "Limas Jajar Genjang":
                return new JTextField[]{new JTextField(10), new JTextField(10), new JTextField(10), new JTextField(10), new JTextField(10)};
            case "Limas Layang-Layang":
                return new JTextField[]{new JTextField(10), new JTextField(10), new JTextField(10)};
            
            // Prisma
            case "Prisma Persegi":
                return new JTextField[]{new JTextField(10), new JTextField(10)};
            case "Prisma Persegi Panjang":
                return new JTextField[]{new JTextField(10), new JTextField(10), new JTextField(10)};
            case "Prisma Segitiga":
                return new JTextField[]{new JTextField(10), new JTextField(10), new JTextField(10)};
            case "Prisma Trapesium":
                return new JTextField[]{new JTextField(10), new JTextField(10), new JTextField(10), new JTextField(10)};
            case "Prisma Belah Ketupat":
                return new JTextField[]{new JTextField(10), new JTextField(10), new JTextField(10), new JTextField(10)};
            case "Prisma Jajar Genjang":
                return new JTextField[]{new JTextField(10), new JTextField(10), new JTextField(10), new JTextField(10), new JTextField(10)};
            case "Prisma Layang-Layang":
                return new JTextField[]{new JTextField(10), new JTextField(10), new JTextField(10)};
            
            default:
                return new JTextField[]{new JTextField(10)};
        }
    }

    private String getInputLabel(String shape, int index) {
        switch (shape) {
            case "Segitiga":
                return index == 0 ? "Alas" : "Tinggi";
            case "Persegi":
                return "Sisi";
            case "Persegi Panjang":
                return index == 0 ? "Panjang" : "Lebar";
            case "Lingkaran":
                return "Radius";
            case "Jajar Genjang":
                return index == 0 ? "Alas" : index == 1 ? "Tinggi" : "Sisi Miring";
            case "Belah Ketupat":
                return index == 0 ? "Diagonal 1" : index == 1 ? "Diagonal 2" : "Sisi";
            case "Layang-Layang":
                return index == 0 ? "Diagonal 1" : "Diagonal 2";
            case "Trapesium":
                return index == 0 ? "Alas Atas" : index == 1 ? "Alas Bawah" : "Tinggi";
            case "Juring Lingkaran":
                return index == 0 ? "Radius" : "Sudut";
            case "Tembereng Lingkaran":
                return index == 0 ? "Tali Busur" : "Jari-jari";
            
            // 3D
            case "Bola":
                return "Radius";
            case "Tabung":
                return index == 0 ? "Radius" : "Tinggi";
            case "Kerucut":
                return index == 0 ? "Radius" : "Tinggi";
            case "Kubus":
                return "Sisi";
            case "Balok":
                return index == 0 ? "Panjang" : index == 1 ? "Lebar" : "Tinggi";
            case "Cincin Bola":
                return index == 0 ? "Radius Luar" : "Radius Dalam";
            case "Juring Bola":
                return index == 0 ? "Radius" : "Sudut";
            case "Tembereng Bola":
                return index == 0 ? "Radius" : "Tinggi Tembereng";
            case "Kerucut Terpancung":
                return index == 0 ? "Radius Bawah" : index == 1 ? "Radius Atas" : "Tinggi";
            
            // Limas
            case "Limas Persegi":
                return index == 0 ? "Sisi" : "Tinggi Limas";
            case "Limas Persegi Panjang":
                return index == 0 ? "Panjang" : index == 1 ? "Lebar" : "Tinggi Limas";
            case "Limas Segitiga":
                return index == 0 ? "Alas" : index == 1 ? "Tinggi" : "Tinggi Limas";
            case "Limas Trapesium":
                return index == 0 ? "Alas Atas" : index == 1 ? "Alas Bawah" : index == 2 ? "Tinggi" : "Tinggi Limas";
            case "Limas Belah Ketupat":
                return index == 0 ? "Diagonal 1" : index == 1 ? "Diagonal 2" : index == 2 ? "Tinggi Limas" : "Sisi";
            case "Limas Jajar Genjang":
                return index == 0 ? "Alas" : index == 1 ? "Tinggi" : index == 2 ? "Tinggi Limas" : index == 3 ? "Tinggi Alas" : "Sisi Miring";
            case "Limas Layang-Layang":
                return index == 0 ? "Diagonal 1" : index == 1 ? "Diagonal 2" : "Tinggi Limas";
            
            // Prisma
            case "Prisma Persegi":
                return index == 0 ? "Sisi" : "Tinggi Prisma";
            case "Prisma Persegi Panjang":
                return index == 0 ? "Panjang" : index == 1 ? "Lebar" : "Tinggi Prisma";
            case "Prisma Segitiga":
                return index == 0 ? "Alas" : index == 1 ? "Tinggi" : "Tinggi Prisma";
            case "Prisma Trapesium":
                return index == 0 ? "Alas Atas" : index == 1 ? "Alas Bawah" : index == 2 ? "Tinggi" : "Tinggi Prisma";
            case "Prisma Belah Ketupat":
                return index == 0 ? "Diagonal 1" : index == 1 ? "Diagonal 2" : index == 2 ? "Tinggi Prisma" : "Sisi";
            case "Prisma Jajar Genjang":
                return index == 0 ? "Alas" : index == 1 ? "Tinggi" : index == 2 ? "Tinggi Prisma" : index == 3 ? "Tinggi Alas" : "Sisi Miring";
            case "Prisma Layang-Layang":
                return index == 0 ? "Diagonal 1" : index == 1 ? "Diagonal 2" : "Tinggi Prisma";
            
            default:
                return "Parameter " + (index + 1);
        }
    }

    private void calculateGeometry() {
        try {
            String selectedShape = getSelectedShape();
            double[] values = getInputValues();
            
            if (values == null) {
                JOptionPane.showMessageDialog(this, "Mohon isi semua parameter dengan nilai yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String result = performCalculation(selectedShape, values);
            resultArea.setText(result);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double[] getInputValues() {
        Component[] components = inputPanel.getComponents();
        java.util.List<Double> values = new ArrayList<>();
        
        for (Component comp : components) {
            if (comp instanceof JTextField) {
                String text = ((JTextField) comp).getText().trim();
                if (text.isEmpty()) {
                    return null;
                }
                try {
                    values.add(Double.parseDouble(text));
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        }
        
        return values.stream().mapToDouble(Double::doubleValue).toArray();
    }

    private String performCalculation(String shape, double[] values) {
        StringBuilder result = new StringBuilder();
        result.append("=== HASIL PERHITUNGAN ===\n");
        result.append("Bentuk: ").append(shape).append("\n");
        result.append("Parameter: ");
        for (int i = 0; i < values.length; i++) {
            result.append(getInputLabel(shape, i)).append(" = ").append(String.format("%.2f", values[i]));
            if (i < values.length - 1) result.append(", ");
        }
        result.append("\n\n");

        try {
            switch (shape) {
                // 2D Shapes
                case "Segitiga":
                    Segitiga segitiga = new Segitiga(values[0], values[1]);
                    result.append("Luas: ").append(String.format("%.2f", segitiga.hitungLuas())).append("\n");
                    result.append("Keliling: ").append(String.format("%.2f", segitiga.hitungKeliling())).append("\n");
                    break;
                case "Persegi":
                    Persegi persegi = new Persegi(values[0]);
                    result.append("Luas: ").append(String.format("%.2f", persegi.hitungLuas())).append("\n");
                    result.append("Keliling: ").append(String.format("%.2f", persegi.hitungKeliling())).append("\n");
                    break;
                case "Persegi Panjang":
                    PersegiPanjang persegiPanjang = new PersegiPanjang(values[0], values[1]);
                    result.append("Luas: ").append(String.format("%.2f", persegiPanjang.hitungLuas())).append("\n");
                    result.append("Keliling: ").append(String.format("%.2f", persegiPanjang.hitungKeliling())).append("\n");
                    break;
                case "Lingkaran":
                    Lingkaran lingkaran = new Lingkaran(values[0]);
                    result.append("Luas: ").append(String.format("%.2f", lingkaran.hitungLuas())).append("\n");
                    result.append("Keliling: ").append(String.format("%.2f", lingkaran.hitungKeliling())).append("\n");
                    break;
                case "Jajar Genjang":
                    JajaranGenjang jajaranGenjang = new JajaranGenjang(values[0], values[1], values[2]);
                    result.append("Luas: ").append(String.format("%.2f", jajaranGenjang.hitungLuas())).append("\n");
                    result.append("Keliling: ").append(String.format("%.2f", jajaranGenjang.hitungKeliling())).append("\n");
                    break;
                case "Belah Ketupat":
                    BelahKetupat belahKetupat = new BelahKetupat(values[0], values[1], values[2]);
                    result.append("Luas: ").append(String.format("%.2f", belahKetupat.hitungLuas())).append("\n");
                    result.append("Keliling: ").append(String.format("%.2f", belahKetupat.hitungKeliling())).append("\n");
                    break;
                case "Layang-Layang":
                    LayangLayang layangLayang = new LayangLayang(values[0], values[1]);
                    result.append("Luas: ").append(String.format("%.2f", layangLayang.hitungLuas())).append("\n");
                    result.append("Keliling: ").append(String.format("%.2f", layangLayang.hitungKeliling())).append("\n");
                    break;
                case "Trapesium":
                    Trapesium trapesium = new Trapesium(values[0], values[1], values[2]);
                    result.append("Luas: ").append(String.format("%.2f", trapesium.hitungLuas())).append("\n");
                    result.append("Keliling: ").append(String.format("%.2f", trapesium.hitungKeliling())).append("\n");
                    break;
                case "Juring Lingkaran":
                    JuringLingkaran juringLingkaran = new JuringLingkaran(values[0], values[1]);
                    result.append("Luas: ").append(String.format("%.2f", juringLingkaran.hitungLuas())).append("\n");
                    result.append("Keliling: ").append(String.format("%.2f", juringLingkaran.hitungKeliling())).append("\n");
                    break;
                case "Tembereng Lingkaran":
                    TemberengLingkaran temberengLingkaran = new TemberengLingkaran(values[0], values[1]);
                    result.append("Luas: ").append(String.format("%.2f", temberengLingkaran.hitungLuas())).append("\n");
                    result.append("Keliling: ").append(String.format("%.2f", temberengLingkaran.hitungKeliling())).append("\n");
                    break;

                // 3D Shapes
                case "Bola":
                    Bola bola = new Bola(values[0]);
                    result.append("Luas Permukaan: ").append(String.format("%.2f", bola.hitungLuasPermukaan())).append("\n");
                    result.append("Volume: ").append(String.format("%.2f", bola.hitungVolume())).append("\n");
                    break;
                case "Tabung":
                    Tabung tabung = new Tabung(values[0], values[1]);
                    result.append("Luas Permukaan: ").append(String.format("%.2f", tabung.hitungLuasPermukaan())).append("\n");
                    result.append("Volume: ").append(String.format("%.2f", tabung.hitungVolume())).append("\n");
                    break;
                case "Kerucut":
                    Kerucut kerucut = new Kerucut(values[0], values[1]);
                    result.append("Luas Permukaan: ").append(String.format("%.2f", kerucut.hitungLuasPermukaan())).append("\n");
                    result.append("Volume: ").append(String.format("%.2f", kerucut.hitungVolume())).append("\n");
                    break;
                case "Cincin Bola":
                    CincinBola cincinBola = new CincinBola(values[0], values[1]);
                    result.append("Luas Permukaan: ").append(String.format("%.2f", cincinBola.hitungLuasPermukaan())).append("\n");
                    result.append("Volume: ").append(String.format("%.2f", cincinBola.hitungVolume())).append("\n");
                    break;
                case "Juring Bola":
                    JuringBola juringBola = new JuringBola(values[0], values[1]);
                    result.append("Luas Permukaan: ").append(String.format("%.2f", juringBola.hitungLuasPermukaan())).append("\n");
                    result.append("Volume: ").append(String.format("%.2f", juringBola.hitungVolume())).append("\n");
                    break;
                case "Tembereng Bola":
                    TemberengBola temberengBola = new TemberengBola(values[0], values[1]);
                    result.append("Luas Permukaan: ").append(String.format("%.2f", temberengBola.hitungLuasPermukaan())).append("\n");
                    result.append("Volume: ").append(String.format("%.2f", temberengBola.hitungVolume())).append("\n");
                    break;
                case "Kerucut Terpancung":
                    KerucutTerpancung kerucutTerpancung = new KerucutTerpancung(values[0], values[1], values[2]);
                    result.append("Luas Permukaan: ").append(String.format("%.2f", kerucutTerpancung.hitungLuasPermukaan())).append("\n");
                    result.append("Volume: ").append(String.format("%.2f", kerucutTerpancung.hitungVolume())).append("\n");
                    break;

                // Limas
                case "Limas Persegi":
                    LimasPersegi limasPersegi = new LimasPersegi(values[0], values[1]);
                    result.append("Luas Permukaan: ").append(String.format("%.2f", limasPersegi.hitungLuasPermukaan())).append("\n");
                    result.append("Volume: ").append(String.format("%.2f", limasPersegi.hitungVolume())).append("\n");
                    break;
                case "Limas Persegi Panjang":
                    LimasPersegiPanjang limasPersegiPanjang = new LimasPersegiPanjang(values[0], values[1], values[2]);
                    result.append("Luas Permukaan: ").append(String.format("%.2f", limasPersegiPanjang.hitungLuasPermukaan())).append("\n");
                    result.append("Volume: ").append(String.format("%.2f", limasPersegiPanjang.hitungVolume())).append("\n");
                    break;
                case "Limas Segitiga":
                    LimasSegitiga limasSegitiga = new LimasSegitiga(values[0], values[1], values[2]);
                    result.append("Luas Permukaan: ").append(String.format("%.2f", limasSegitiga.hitungLuasPermukaan())).append("\n");
                    result.append("Volume: ").append(String.format("%.2f", limasSegitiga.hitungVolume())).append("\n");
                    break;
                case "Limas Trapesium":
                    LimasTrapesium limasTrapesium = new LimasTrapesium(values[0], values[1], values[2], values[3]);
                    result.append("Luas Permukaan: ").append(String.format("%.2f", limasTrapesium.hitungLuasPermukaan())).append("\n");
                    result.append("Volume: ").append(String.format("%.2f", limasTrapesium.hitungVolume())).append("\n");
                    break;
                case "Limas Belah Ketupat":
                    LimasBelahKetupat limasBelahKetupat = new LimasBelahKetupat(values[0], values[1], values[2], values[3]);
                    result.append("Luas Permukaan: ").append(String.format("%.2f", limasBelahKetupat.hitungLuasPermukaan())).append("\n");
                    result.append("Volume: ").append(String.format("%.2f", limasBelahKetupat.hitungVolume())).append("\n");
                    break;
                case "Limas Jajar Genjang":
                    LimasJajaranGenjang limasJajaranGenjang = new LimasJajaranGenjang(values[0], values[1], values[2], values[3], values[4]);
                    result.append("Luas Permukaan: ").append(String.format("%.2f", limasJajaranGenjang.hitungLuasPermukaan())).append("\n");
                    result.append("Volume: ").append(String.format("%.2f", limasJajaranGenjang.hitungVolume())).append("\n");
                    break;
                case "Limas Layang-Layang":
                    LimasLayangLayang limasLayangLayang = new LimasLayangLayang(values[0], values[1], values[2]);
                    result.append("Luas Permukaan: ").append(String.format("%.2f", limasLayangLayang.hitungLuasPermukaan())).append("\n");
                    result.append("Volume: ").append(String.format("%.2f", limasLayangLayang.hitungVolume())).append("\n");
                    break;

                // Prisma
                case "Prisma Persegi":
                    PrismaPersegi prismaPersegi = new PrismaPersegi(values[0], values[1]);
                    result.append("Luas Permukaan: ").append(String.format("%.2f", prismaPersegi.hitungLuasPermukaan())).append("\n");
                    result.append("Volume: ").append(String.format("%.2f", prismaPersegi.hitungVolume())).append("\n");
                    break;
                case "Prisma Persegi Panjang":
                    PrismaPersegiPanjang prismaPersegiPanjang = new PrismaPersegiPanjang(values[0], values[1], values[2]);
                    result.append("Luas Permukaan: ").append(String.format("%.2f", prismaPersegiPanjang.hitungLuasPermukaan())).append("\n");
                    result.append("Volume: ").append(String.format("%.2f", prismaPersegiPanjang.hitungVolume())).append("\n");
                    break;
                case "Prisma Segitiga":
                    PrismaSegitiga prismaSegitiga = new PrismaSegitiga(values[0], values[1], values[2]);
                    result.append("Luas Permukaan: ").append(String.format("%.2f", prismaSegitiga.hitungLuasPermukaan())).append("\n");
                    result.append("Volume: ").append(String.format("%.2f", prismaSegitiga.hitungVolume())).append("\n");
                    break;
                case "Prisma Trapesium":
                    PrismaTrapesium prismaTrapesium = new PrismaTrapesium(values[0], values[1], values[2], values[3]);
                    result.append("Luas Permukaan: ").append(String.format("%.2f", prismaTrapesium.hitungLuasPermukaan())).append("\n");
                    result.append("Volume: ").append(String.format("%.2f", prismaTrapesium.hitungVolume())).append("\n");
                    break;
                case "Prisma Belah Ketupat":
                    PrismaBelahKetupat prismaBelahKetupat = new PrismaBelahKetupat(values[0], values[1], values[2], values[3]);
                    result.append("Luas Permukaan: ").append(String.format("%.2f", prismaBelahKetupat.hitungLuasPermukaan())).append("\n");
                    result.append("Volume: ").append(String.format("%.2f", prismaBelahKetupat.hitungVolume())).append("\n");
                    break;
                case "Prisma Jajar Genjang":
                    PrismaJajaranGenjang prismaJajaranGenjang = new PrismaJajaranGenjang(values[0], values[1], values[2], values[3], values[4]);
                    result.append("Luas Permukaan: ").append(String.format("%.2f", prismaJajaranGenjang.hitungLuasPermukaan())).append("\n");
                    result.append("Volume: ").append(String.format("%.2f", prismaJajaranGenjang.hitungVolume())).append("\n");
                    break;
                case "Prisma Layang-Layang":
                    PrismaLayangLayang prismaLayangLayang = new PrismaLayangLayang(values[0], values[1], values[2]);
                    result.append("Luas Permukaan: ").append(String.format("%.2f", prismaLayangLayang.hitungLuasPermukaan())).append("\n");
                    result.append("Volume: ").append(String.format("%.2f", prismaLayangLayang.hitungVolume())).append("\n");
                    break;

                default:
                    result.append("Bentuk geometri belum diimplementasikan.");
            }
        } catch (Exception e) {
            result.append("Error dalam perhitungan: ").append(e.getMessage());
        }

        return result.toString();
    }

    private void clearAll() {
        resultArea.setText("");
        Component[] components = inputPanel.getComponents();
        for (Component comp : components) {
            if (comp instanceof JTextField) {
                ((JTextField) comp).setText("");
            }
        }
    }

    public static class GeometryTask implements Runnable {
        private final String shape;
        private final double[] values;
        private String result;

        public GeometryTask(String shape, double[] values) {
            this.shape = shape;
            this.values = values;
        }

        @Override
        public void run() {
            StringBuilder sb = new StringBuilder();
            try {
                switch (shape) {
                    case "Segitiga":
                        Segitiga segitiga = new Segitiga(values[0], values[1]);
                        sb.append("Luas: ").append(String.format("%.2f", segitiga.hitungLuas())).append("\n");
                        sb.append("Keliling: ").append(String.format("%.2f", segitiga.hitungKeliling())).append("\n");
                        break;
                    case "Persegi":
                        Persegi persegi = new Persegi(values[0]);
                        sb.append("Luas: ").append(String.format("%.2f", persegi.hitungLuas())).append("\n");
                        sb.append("Keliling: ").append(String.format("%.2f", persegi.hitungKeliling())).append("\n");
                        break;
                    case "Persegi Panjang":
                        PersegiPanjang persegiPanjang = new PersegiPanjang(values[0], values[1]);
                        sb.append("Luas: ").append(String.format("%.2f", persegiPanjang.hitungLuas())).append("\n");
                        sb.append("Keliling: ").append(String.format("%.2f", persegiPanjang.hitungKeliling())).append("\n");
                        break;
                    case "Lingkaran":
                        Lingkaran lingkaran = new Lingkaran(values[0]);
                        sb.append("Luas: ").append(String.format("%.2f", lingkaran.hitungLuas())).append("\n");
                        sb.append("Keliling: ").append(String.format("%.2f", lingkaran.hitungKeliling())).append("\n");
                        break;
                    case "Jajar Genjang":
                        JajaranGenjang jajaranGenjang = new JajaranGenjang(values[0], values[1], values[2]);
                        sb.append("Luas: ").append(String.format("%.2f", jajaranGenjang.hitungLuas())).append("\n");
                        sb.append("Keliling: ").append(String.format("%.2f", jajaranGenjang.hitungKeliling())).append("\n");
                        break;
                    case "Belah Ketupat":
                        BelahKetupat belahKetupat = new BelahKetupat(values[0], values[1], values[2]);
                        sb.append("Luas: ").append(String.format("%.2f", belahKetupat.hitungLuas())).append("\n");
                        sb.append("Keliling: ").append(String.format("%.2f", belahKetupat.hitungKeliling())).append("\n");
                        break;
                    case "Layang-Layang":
                        LayangLayang layangLayang = new LayangLayang(values[0], values[1]);
                        sb.append("Luas: ").append(String.format("%.2f", layangLayang.hitungLuas())).append("\n");
                        sb.append("Keliling: ").append(String.format("%.2f", layangLayang.hitungKeliling())).append("\n");
                        break;
                    case "Trapesium":
                        Trapesium trapesium = new Trapesium(values[0], values[1], values[2]);
                        sb.append("Luas: ").append(String.format("%.2f", trapesium.hitungLuas())).append("\n");
                        sb.append("Keliling: ").append(String.format("%.2f", trapesium.hitungKeliling())).append("\n");
                        break;
                    case "Juring Lingkaran":
                        JuringLingkaran juringLingkaran = new JuringLingkaran(values[0], values[1]);
                        sb.append("Luas: ").append(String.format("%.2f", juringLingkaran.hitungLuas())).append("\n");
                        sb.append("Keliling: ").append(String.format("%.2f", juringLingkaran.hitungKeliling())).append("\n");
                        break;
                    case "Tembereng Lingkaran":
                        TemberengLingkaran temberengLingkaran = new TemberengLingkaran(values[0], values[1]);
                        sb.append("Luas: ").append(String.format("%.2f", temberengLingkaran.hitungLuas())).append("\n");
                        sb.append("Keliling: ").append(String.format("%.2f", temberengLingkaran.hitungKeliling())).append("\n");
                        break;
                    // 3D
                    case "Bola":
                        Bola bola = new Bola(values[0]);
                        sb.append("Luas Permukaan: ").append(String.format("%.2f", bola.hitungLuasPermukaan())).append("\n");
                        sb.append("Volume: ").append(String.format("%.2f", bola.hitungVolume())).append("\n");
                        break;
                    case "Tabung":
                        Tabung tabung = new Tabung(values[0], values[1]);
                        sb.append("Luas Permukaan: ").append(String.format("%.2f", tabung.hitungLuasPermukaan())).append("\n");
                        sb.append("Volume: ").append(String.format("%.2f", tabung.hitungVolume())).append("\n");
                        break;
                    case "Kerucut":
                        Kerucut kerucut = new Kerucut(values[0], values[1]);
                        sb.append("Luas Permukaan: ").append(String.format("%.2f", kerucut.hitungLuasPermukaan())).append("\n");
                        sb.append("Volume: ").append(String.format("%.2f", kerucut.hitungVolume())).append("\n");
                        break;
                    case "Kubus":
                        Persegi kubus = new Persegi(values[0]);
                        double luasKubus = 6 * kubus.hitungLuas();
                        double volumeKubus = Math.pow(values[0], 3);
                        sb.append("Luas Permukaan: ").append(String.format("%.2f", luasKubus)).append("\n");
                        sb.append("Volume: ").append(String.format("%.2f", volumeKubus)).append("\n");
                        break;
                    case "Balok":
                        double p = values[0], l = values[1], t = values[2];
                        double luasBalok = 2 * (p*l + p*t + l*t);
                        double volumeBalok = p * l * t;
                        sb.append("Luas Permukaan: ").append(String.format("%.2f", luasBalok)).append("\n");
                        sb.append("Volume: ").append(String.format("%.2f", volumeBalok)).append("\n");
                        break;
                    case "Cincin Bola":
                        CincinBola cincinBola = new CincinBola(values[0], values[1]);
                        sb.append("Luas Permukaan: ").append(String.format("%.2f", cincinBola.hitungLuasPermukaan())).append("\n");
                        sb.append("Volume: ").append(String.format("%.2f", cincinBola.hitungVolume())).append("\n");
                        break;
                    case "Juring Bola":
                        JuringBola juringBola = new JuringBola(values[0], values[1]);
                        sb.append("Luas Permukaan: ").append(String.format("%.2f", juringBola.hitungLuasPermukaan())).append("\n");
                        sb.append("Volume: ").append(String.format("%.2f", juringBola.hitungVolume())).append("\n");
                        break;
                    case "Tembereng Bola":
                        TemberengBola temberengBola = new TemberengBola(values[0], values[1]);
                        sb.append("Luas Permukaan: ").append(String.format("%.2f", temberengBola.hitungLuasPermukaan())).append("\n");
                        sb.append("Volume: ").append(String.format("%.2f", temberengBola.hitungVolume())).append("\n");
                        break;
                    case "Kerucut Terpancung":
                        KerucutTerpancung kerucutTerpancung = new KerucutTerpancung(values[0], values[1], values[2]);
                        sb.append("Luas Permukaan: ").append(String.format("%.2f", kerucutTerpancung.hitungLuasPermukaan())).append("\n");
                        sb.append("Volume: ").append(String.format("%.2f", kerucutTerpancung.hitungVolume())).append("\n");
                        break;
                    // Limas
                    case "Limas Persegi":
                        LimasPersegi limasPersegi = new LimasPersegi(values[0], values[1]);
                        sb.append("Luas Permukaan: ").append(String.format("%.2f", limasPersegi.hitungLuasPermukaan())).append("\n");
                        sb.append("Volume: ").append(String.format("%.2f", limasPersegi.hitungVolume())).append("\n");
                        break;
                    case "Limas Persegi Panjang":
                        LimasPersegiPanjang limasPersegiPanjang = new LimasPersegiPanjang(values[0], values[1], values[2]);
                        sb.append("Luas Permukaan: ").append(String.format("%.2f", limasPersegiPanjang.hitungLuasPermukaan())).append("\n");
                        sb.append("Volume: ").append(String.format("%.2f", limasPersegiPanjang.hitungVolume())).append("\n");
                        break;
                    case "Limas Segitiga":
                        LimasSegitiga limasSegitiga = new LimasSegitiga(values[0], values[1], values[2]);
                        sb.append("Luas Permukaan: ").append(String.format("%.2f", limasSegitiga.hitungLuasPermukaan())).append("\n");
                        sb.append("Volume: ").append(String.format("%.2f", limasSegitiga.hitungVolume())).append("\n");
                        break;
                    case "Limas Trapesium":
                        LimasTrapesium limasTrapesium = new LimasTrapesium(values[0], values[1], values[2], values[3]);
                        sb.append("Luas Permukaan: ").append(String.format("%.2f", limasTrapesium.hitungLuasPermukaan())).append("\n");
                        sb.append("Volume: ").append(String.format("%.2f", limasTrapesium.hitungVolume())).append("\n");
                        break;
                    case "Limas Belah Ketupat":
                        LimasBelahKetupat limasBelahKetupat = new LimasBelahKetupat(values[0], values[1], values[2], values[3]);
                        sb.append("Luas Permukaan: ").append(String.format("%.2f", limasBelahKetupat.hitungLuasPermukaan())).append("\n");
                        sb.append("Volume: ").append(String.format("%.2f", limasBelahKetupat.hitungVolume())).append("\n");
                        break;
                    case "Limas Jajar Genjang":
                        LimasJajaranGenjang limasJajaranGenjang = new LimasJajaranGenjang(values[0], values[1], values[2], values[3], values[4]);
                        sb.append("Luas Permukaan: ").append(String.format("%.2f", limasJajaranGenjang.hitungLuasPermukaan())).append("\n");
                        sb.append("Volume: ").append(String.format("%.2f", limasJajaranGenjang.hitungVolume())).append("\n");
                        break;
                    case "Limas Layang-Layang":
                        LimasLayangLayang limasLayangLayang = new LimasLayangLayang(values[0], values[1], values[2]);
                        sb.append("Luas Permukaan: ").append(String.format("%.2f", limasLayangLayang.hitungLuasPermukaan())).append("\n");
                        sb.append("Volume: ").append(String.format("%.2f", limasLayangLayang.hitungVolume())).append("\n");
                        break;
                    // Prisma
                    case "Prisma Persegi":
                        PrismaPersegi prismaPersegi = new PrismaPersegi(values[0], values[1]);
                        sb.append("Luas Permukaan: ").append(String.format("%.2f", prismaPersegi.hitungLuasPermukaan())).append("\n");
                        sb.append("Volume: ").append(String.format("%.2f", prismaPersegi.hitungVolume())).append("\n");
                        break;
                    case "Prisma Persegi Panjang":
                        PrismaPersegiPanjang prismaPersegiPanjang = new PrismaPersegiPanjang(values[0], values[1], values[2]);
                        sb.append("Luas Permukaan: ").append(String.format("%.2f", prismaPersegiPanjang.hitungLuasPermukaan())).append("\n");
                        sb.append("Volume: ").append(String.format("%.2f", prismaPersegiPanjang.hitungVolume())).append("\n");
                        break;
                    case "Prisma Segitiga":
                        PrismaSegitiga prismaSegitiga = new PrismaSegitiga(values[0], values[1], values[2]);
                        sb.append("Luas Permukaan: ").append(String.format("%.2f", prismaSegitiga.hitungLuasPermukaan())).append("\n");
                        sb.append("Volume: ").append(String.format("%.2f", prismaSegitiga.hitungVolume())).append("\n");
                        break;
                    case "Prisma Trapesium":
                        PrismaTrapesium prismaTrapesium = new PrismaTrapesium(values[0], values[1], values[2], values[3]);
                        sb.append("Luas Permukaan: ").append(String.format("%.2f", prismaTrapesium.hitungLuasPermukaan())).append("\n");
                        sb.append("Volume: ").append(String.format("%.2f", prismaTrapesium.hitungVolume())).append("\n");
                        break;
                    case "Prisma Belah Ketupat":
                        PrismaBelahKetupat prismaBelahKetupat = new PrismaBelahKetupat(values[0], values[1], values[2], values[3]);
                        sb.append("Luas Permukaan: ").append(String.format("%.2f", prismaBelahKetupat.hitungLuasPermukaan())).append("\n");
                        sb.append("Volume: ").append(String.format("%.2f", prismaBelahKetupat.hitungVolume())).append("\n");
                        break;
                    case "Prisma Jajar Genjang":
                        PrismaJajaranGenjang prismaJajaranGenjang = new PrismaJajaranGenjang(values[0], values[1], values[2], values[3], values[4]);
                        sb.append("Luas Permukaan: ").append(String.format("%.2f", prismaJajaranGenjang.hitungLuasPermukaan())).append("\n");
                        sb.append("Volume: ").append(String.format("%.2f", prismaJajaranGenjang.hitungVolume())).append("\n");
                        break;
                    case "Prisma Layang-Layang":
                        PrismaLayangLayang prismaLayangLayang = new PrismaLayangLayang(values[0], values[1], values[2]);
                        sb.append("Luas Permukaan: ").append(String.format("%.2f", prismaLayangLayang.hitungLuasPermukaan())).append("\n");
                        sb.append("Volume: ").append(String.format("%.2f", prismaLayangLayang.hitungVolume())).append("\n");
                        break;
                    default:
                        sb.append("Bentuk geometri belum diimplementasikan di Thread.");
                }
            } catch (Exception e) {
                sb.append("Error: ").append(e.getMessage());
            }
            result = sb.toString();
        }

        public String getResult() {
            return result;
        }
    }

    public class ThreadedCalculationFrame extends JFrame {
        private JComboBox<String> comboShape;
        private JPanel inputPanel;
        private JTextArea resultArea;
        private JButton hitungButton;

        public ThreadedCalculationFrame() {
            setTitle("Perhitungan Geometri (Thread)");
            setSize(400, 350);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout(10, 10));

            comboShape = new JComboBox<>(getAllShapes());
            inputPanel = new JPanel();
            inputPanel.setBorder(BorderFactory.createTitledBorder("Input Parameter"));
            resultArea = new JTextArea(6, 30);
            resultArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(resultArea);
            hitungButton = new JButton("Hitung (Thread)");

            JPanel topPanel = new JPanel(new FlowLayout());
            topPanel.add(new JLabel("Bentuk:"));
            topPanel.add(comboShape);

            JPanel bottomPanel = new JPanel(new BorderLayout());
            bottomPanel.add(hitungButton, BorderLayout.NORTH);
            bottomPanel.add(scrollPane, BorderLayout.CENTER);

            add(topPanel, BorderLayout.NORTH);
            add(inputPanel, BorderLayout.CENTER);
            add(bottomPanel, BorderLayout.SOUTH);

            comboShape.addActionListener(e -> updateInputPanel());
            hitungButton.addActionListener(e -> runThreadedCalculation());

            updateInputPanel();
        }

        private String[] getAllShapes() {
            java.util.List<String> shapes = new java.util.ArrayList<>();
            for (String s : SUBCLASS.get("2D")) shapes.add(s);
            for (String s : SUBCLASS.get("3D")) shapes.add(s);
            for (String s : SUBCLASS_3D.getOrDefault("Limas", new String[0])) shapes.add(s);
            for (String s : SUBCLASS_3D.getOrDefault("Prisma", new String[0])) shapes.add(s);
            return shapes.toArray(new String[0]);
        }

        private void updateInputPanel() {
            inputPanel.removeAll();
            String shape = (String) comboShape.getSelectedItem();
            if (shape == null) {
                inputPanel.revalidate();
                inputPanel.repaint();
                return;
            }
            JTextField[] inputFields = createInputFields(shape);
            inputPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.anchor = GridBagConstraints.WEST;
            for (int i = 0; i < inputFields.length; i++) {
                gbc.gridx = 0;
                gbc.gridy = i;
                inputPanel.add(new JLabel(getInputLabel(shape, i) + ":"), gbc);
                gbc.gridx = 1;
                inputPanel.add(inputFields[i], gbc);
            }
            inputPanel.revalidate();
            inputPanel.repaint();
        }

        private double[] getInputValues() {
            java.util.List<Double> values = new java.util.ArrayList<>();
            for (Component comp : inputPanel.getComponents()) {
                if (comp instanceof JTextField) {
                    String text = ((JTextField) comp).getText().trim();
                    if (text.isEmpty()) return null;
                    try {
                        values.add(Double.parseDouble(text));
                    } catch (NumberFormatException e) {
                        return null;
                    }
                }
            }
            return values.stream().mapToDouble(Double::doubleValue).toArray();
        }

        private void runThreadedCalculation() {
            String shape = (String) comboShape.getSelectedItem();
            double[] values = getInputValues();
            if (values == null) {
                JOptionPane.showMessageDialog(this, "Mohon isi semua parameter dengan nilai yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            GeometryTask task = new GeometryTask(shape, values);
            ThreadExecutor executor = ThreadExecutor.getInstance();
            Future<?> future = executor.submitTask(task);
            new Thread(() -> {
                try {
                    future.get();
                    SwingUtilities.invokeLater(() -> resultArea.setText("[ThreadExecutor]\n" + task.getResult()));
                } catch (Exception e) {
                    SwingUtilities.invokeLater(() -> resultArea.setText("Thread error: " + e.getMessage()));
                }
            }).start();
        }
    }

    private void runThreadedCalculation() {
        ThreadedCalculationFrame frame = new ThreadedCalculationFrame();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main gui = new Main();
            gui.setVisible(true);
        });
    }
} 
