import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private JButton play;
    private JButton restart;
    private GameOfLife gameOfLife;

    public Main() {
        gameOfLife = new GameOfLife(10, 10);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setTitle("Game of Life");
        frame.setLayout(new BorderLayout());

        initializeUI();

        frame.add(gameOfLife.getPanel(), BorderLayout.CENTER);
        frame.add(createButtonPanel(), BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void initializeUI() {
        JPanel mainPanel = gameOfLife.getPanel();
        for (int x = 0; x < gameOfLife.getGrille().getNbLig(); x++) {
            for (int y = 0; y < gameOfLife.getGrille().getNbCol(); y++) {
                JButton b = new JButton("" + gameOfLife.getGrille().getTab()[x][y]);

                if (gameOfLife.getGrille().getTab()[x][y] == -1) {
                    b.setBackground(Color.BLUE);
                }
                b.setEnabled(false);
                mainPanel.add(b);
            }
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(gameOfLife.getPlay());
        buttonPanel.add(gameOfLife.getRestart());
        Handler handler=new Handler();
        gameOfLife.getPlay().addActionListener(handler);
        gameOfLife.getRestart().addActionListener(handler);
        gameOfLife.getRestart().setText("ss");
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(gameOfLife.getPlay());
        buttonPanel.add(gameOfLife.getRestart());
        return buttonPanel;
    }

    private class Handler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == gameOfLife.getPlay()) {
                System.out.println("Play button clicked");
            } else if (e.getSource() == gameOfLife.getRestart()) {
                System.out.println("Restart button clicked");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}
