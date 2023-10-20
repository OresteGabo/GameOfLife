import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private GameOfLife gameOfLife;
    private JFrame frame;

    public Main() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Get the screen's dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setTitle("Game of Life");
        frame.setLayout(new BorderLayout());

        gameOfLife = new GameOfLife(180,120, 1000);

        frame.add(createButtonPanel(), BorderLayout.SOUTH);
        frame.setVisible(true);

        Handler handler = new Handler();
        gameOfLife.getPlay().addActionListener(handler);
        gameOfLife.getRestart().addActionListener(handler);
        playGameLoop();
    }

    private void playGameLoop() {
        //for (int x = 0; x < 100; x++) {
            updateUI();
            frame.add(gameOfLife.getPanel(), BorderLayout.CENTER);
            frame.revalidate();

            /*try {
                Thread.sleep(1000); // 1 second delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            gameOfLife.play();*/
        //}
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        //gameOfLife = new GameOfLife(20, 50); // Create a new game for each frame
        buttonPanel.add(gameOfLife.getPlay());
        buttonPanel.add(gameOfLife.getRestart());
        return buttonPanel;
    }

    private void updateUI() {
        JPanel mainPanel = gameOfLife.getPanel();
        mainPanel.removeAll(); // Clear the panel

        for (int x = 0; x < gameOfLife.getGrille().getNbLig(); x++) {
            for (int y = 0; y < gameOfLife.getGrille().getNbCol(); y++) {
                //JButton b = new JButton("" + gameOfLife.getGrille().getTab()[x][y]);
                JButton b = new JButton("");

                if (gameOfLife.getGrille().getTab()[x][y] == -1) {
                    b.setBackground(Color.BLUE);
                }
                b.setEnabled(false);
                mainPanel.add(b);
            }
        }

        mainPanel.revalidate(); // Refresh the panel
        mainPanel.repaint();
    }

    private class Handler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == gameOfLife.getPlay()) {
                System.out.println("Play button clicked");
                gameOfLife.getGrille().play();
                updateUI(); // Refresh the UI
            } else if (e.getSource() == gameOfLife.getRestart()) {
                System.out.println("Restart button clicked");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}
