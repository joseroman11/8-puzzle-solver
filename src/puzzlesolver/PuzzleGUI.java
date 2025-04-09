package puzzlesolver;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PuzzleGUI {
    private static final int TILE_SIZE = 100;
    private JFrame frame;
    private JPanel boardPanel;
    private List<PuzzleState> path;
    private int currentStep = 0;

    public PuzzleGUI(List<PuzzleState> path) {
        this.path = path;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        frame = new JFrame("Solución del 8-Puzzle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel del tablero
        boardPanel = new JPanel(new GridLayout(3, 3));
        updateBoard();

        // Panel de controles
        JPanel controlPanel = new JPanel();
        JButton prevButton = new JButton("Anterior");
        JButton nextButton = new JButton("Siguiente");

        prevButton.addActionListener(e -> {
            if (currentStep > 0) {
                currentStep--;
                updateBoard();
            }
        });

        nextButton.addActionListener(e -> {
            if (currentStep < path.size() - 1) {
                currentStep++;
                updateBoard();
            }
        });

        controlPanel.add(prevButton);
        controlPanel.add(nextButton);

        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void updateBoard() {
        boardPanel.removeAll();
        PuzzleState state = path.get(currentStep);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JLabel label = new JLabel(state.board[i][j] == 0 ? "" : String.valueOf(state.board[i][j]));
                label.setFont(new Font("Arial", Font.BOLD, 36));
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                label.setOpaque(true);
                label.setBackground(state.board[i][j] == 0 ? Color.LIGHT_GRAY : Color.WHITE);
                boardPanel.add(label);
            }
        }

        frame.setTitle("Movimiento " + (currentStep + 1) + "/" + path.size() + " - " + state.move);
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    public static void showSolution(List<PuzzleState> path) {
        SwingUtilities.invokeLater(() -> new PuzzleGUI(path));
    }
}