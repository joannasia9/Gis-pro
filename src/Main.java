import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main {
    public static void main(String[] args){
        JFrame frame = new JFrame("Projekt GIS - Generator grafu");

        JPanel mainPanel = new JPanel(new GridBagLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Generator grafu spójnego"));
        mainPanel.setPreferredSize(new Dimension());
        mainPanel.setBounds(10,10,400,200);

        JLabel provideNodesNum  = new JLabel("Podaj ilość wierzchołków grafu", SwingConstants.CENTER);
        provideNodesNum.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField textField = new JTextField(10);

        textField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton generateBtn = new JButton("Generuj graf dowolny");
        JButton generateCycleBtn = new JButton("Generuj graf cykliczny");
        JButton generateCompleteBtn = new JButton("Generuj graf zupełny");



        generateBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        generateCycleBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        generateCompleteBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        final int[] iterator = {0};
        generateBtn.addActionListener((ActionEvent e) -> {
            if(!textField.getText().equals("")) {
                iterator[0] += 1;
                int n = Integer.parseInt(textField.getText());
                GraphGenerator graphGenerator = new GraphGenerator(n, iterator[0]);
                graphGenerator.generateGraph(0);
            }
        });
        generateCompleteBtn.addActionListener((ActionEvent e) -> {
            if(!textField.getText().equals("")) {
                iterator[0] += 1;
                int n = Integer.parseInt(textField.getText());
                GraphGenerator graphGenerator = new GraphGenerator(n, iterator[0]);
                graphGenerator.generateGraph(2);
            }
        });
        generateCycleBtn.addActionListener((ActionEvent e) -> {
            if(!textField.getText().equals("")) {
                iterator[0] += 1;
                int n = Integer.parseInt(textField.getText());
                GraphGenerator graphGenerator = new GraphGenerator(n, iterator[0]);
                graphGenerator.generateGraph(1);
            }
        });

        panel.add(provideNodesNum);
        panel.add(textField);
        panel.add(generateBtn);
        panel.add(generateCycleBtn);
        panel.add(generateCompleteBtn);

        mainPanel.add(panel);
        frame.add(mainPanel);
        frame.setSize(420,250);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
